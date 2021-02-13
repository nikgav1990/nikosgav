<%--
    Document   : insert
    Created on : 4 Ιαν 2019, 1:32:43 μμ
    Author     : Nikos
--%>


<%@page import="java.sql.SQLIntegrityConstraintViolationException"%>
<!---Εισαγωγή βιβλιοθηκών----------------->
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="com.mysql.jdbc.Statement"%>
<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html><!-------ΑΡΧΗ ΣΕΛΙΔΑΣ ΕΙΣΑΓΩΓΗΣ ΠΡΟΪΟΝΤΟΣ-------->
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Σελίδα εισαγωγής</title>
    </head>
    <body style="background-color:honeydew">

        <% //--------ΑΡΧΗ-------------//
            
           
          request.setCharacterEncoding("UTF-8");//Encoding to UTF_8
          response.setCharacterEncoding("UTF-8");
     
          try{
            //Δήλωση μεταβλητών που αρχικοποιούνται μέσω της μεθόδου post
           String bar= request.getParameter("barcode");
           String name= request.getParameter("pname");
           String price= request.getParameter("price");
           String vat=request.getParameter("vat");
           String desc=request.getParameter("description");
           
           //Μεταβλητές που θα δεχθούν την τιμή και το ΦΠΑ αντίστοιχα
           float pr=0;
           float fpa=0;

           //Σελίδα ανακατεύθυνσης σε περίπτωση που όλα τα πεδία είναι κενά
           String misspage="http://localhost:8080/Products/missingdata.html";

        //Αν κάποιο από τα πεδία της φόρμας εισαγωγής είναι κενό τότε γίνεται ανακατεύθυνση
           //στη σελίδα missingdata
           if (bar.isEmpty()||name.isEmpty()||
              price.isEmpty()||vat.isEmpty()||desc.isEmpty()){

               //Εντολή ανακατεύθυνσης
               response.sendRedirect(misspage);

               }//end-if

   //Αν τα πεδία είναι ΟΛΑ συμπληρωμένα τότε γίνεται
     //μετατροπή των πεδίων της τιμής και ΦΠΑ σε πραγματικούς αριθμούς
           
           else {
               
       //Αρχικοποίηση αντικειμένων σύνδεσης σε null
          Connection conn=null;
       java.sql.Statement dbstate=null;

            try{
                
       //Δήλωση μεταβλητών για την μετατροπή των πεδίων της φόρμας σε αριθμούς   
            pr= Float.parseFloat(price);
            fpa= Float.parseFloat(vat);
           
            
        //Δήλωση Driver και URL της βάσης
       String JDBC_Driver= "com.mysql.jdbc.Driver";
       String DB_URL="jdbc:mysql://localhost:3306/dbproducts?characterEncoding=UTF-8";

       //Στοιχεία σύνδεσης με την βάση
       String user="prodadmin";
       String pass="78910";

  
           //Φόρτωση driver στην μνήμη
         Class.forName(JDBC_Driver);
      //Άνοιγμα σύνδεσης με την βάση δίνοντας το URL της βάσης,όνομα χρήστη και κωδικό
      conn = DriverManager.getConnection(DB_URL, user, pass);

      if(conn!=null){
          //Αν η σύνδεση επιτευχθεί με την βάση
        String sql=""; //Το query που θα δώσουμε στην βάση
      dbstate=conn.createStatement();//Δημιουργία statement
   
    

    //Εισαγωγή εγγραφής στον πίνακα products     
 sql= "INSERT INTO products(prodcode,prodname,price,vat,description) VALUES('"+bar+"' , '"+name+"',"
        +" '"+pr+"' ,'"+fpa+"' , '"+desc+"')";
 
 //Μεταβλητή που αυξάνεται αν υπάρχουν γραμμές που επηρεάστηκαν από το ερώτημα
    int count= dbstate.executeUpdate(sql);
 
 if(count>0){ %>
 
 <!----Αν το query εκτελεστεί επιτυχώς τότε εμφανίζεται κατάλληλο μήνυμα---->
 <h3>A new record inserted in Products table!</h3>
 
 <% }//end-if

else { %> <!---Σε περίπτωση σφάλματος στην εγγραφή εμφανίζει το παρακάτω μήνυμα-->
  <h3>Record did not added!</h3>
  
 <% }//end-else

//Κλείσιμο σύνδεσης
conn.close();
dbstate.close();

   }//end-if-conn
}//end-try

//--------------------------HANDLING EXCEPTIONS----------------------//

     //Σε περίπτωση που δημιουργηθεί ένα αντικείμενο εξαίρεσης τότε γίνεται ανακατεύθυνση
            catch(NumberFormatException n){

    //Σελίδα ανακατεύθυνσης σε περίπτωση που ο ΦΠΑ ή η τιμή που εισάγονται δεν είναι αριθμοί
            String errornum="http://localhost:8080/Products/errornumber.html";

                 //Εντολή ανακατεύθυνσης
               response.sendRedirect(errornum);

            }//end-catch

//Αν εισαχθεί barcode ίδιο με κάποιο που έχει εισαχθεί τότε εμφανίζεται το παρακάτω μήνυμα
catch(SQLIntegrityConstraintViolationException e){ %>
       <h3>Barcode must be unique!</h3>
    <%}

         catch(SQLException e){
               conn=null;
               dbstate=null;
               
%> <!--Σε περίπτωση εξαίρεσης εμφανίζεται το μήνυμα παρακάτω-->
            <h3>ERROR IN CONNECTION!</h3>
   
           <% }//end-catch


        catch(ClassNotFoundException e){ %>
        <!--Αν δεν φορτωθεί επιτυχώς ο driver τότε εμφανίζεται το μήνυμα -->
        <h3>Unable to load the Driver!</h3> <%  }
   }//end-else
}//FIRST-TRY
        
        catch(NullPointerException e){ %>
        <h3>ERROR!</h3>  <%}
        
        


//---------------------------------------END OF HANDLING EXCEPTIONS---------------------------------//
      %><!---------ΤΕΛΟΣ---------->
  <input type="submit" style="font-size:13px" onclick="location.href='insert.html'" value="Εισαγωγή νέου προϊόντος">&nbsp;
   &nbsp;<input type="submit" style="font-size:13px" onclick="location.href='index.html'" value="Επιστροφή στην αρχική σελίδα">
</body>
</html><!--ΤΕΛΟΣ ΣΕΛΙΔΑΣ ΕΙΣΑΓΩΓΗΣ ΠΡΟΪΟΝΤΟΣ--------->
