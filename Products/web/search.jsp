

<%--
    Document   : insert
    Created on : 4 Ιαν 2019, 1:32:43 μμ
    Author     : Nikos
--%>

<!---Εισαγωγή βιβλιοθηκών----------------->
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>

<%@page import="java.sql.Connection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html><!-------ΑΡΧΗ ΣΕΛΙΔΑΣ ΕΙΣΑΓΩΓΗΣ ΠΡΟΪΟΝΤΟΣ-------->
    <head>
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Σελίδα αναζήτησης</title>
        <style> /*******Κανόνες CSS για το styling των στοιχείων*******/
          
        table,th,td{
           
        margin-top:-5px;
    font-family: sans-serif;
    margin-left:5px;
    border: 2px solid black;
    border-collapse:collapse;
    padding:10px;
    text-align:center;
    border-spacing:10px;
   }  

   th {  background-color: beige;}
   td {  background-color: ghostwhite;}
   
  
  #t{width:70%}
    
  input { margin-bottom:10px;
         padding-bottom:5px;}
  
#error { color: red; }          
          
</style>
    </head>
    <body style="background-color: tan">
        
        <h3>Αποτέλεσματα:</h3>
    
                 
         <% //--------ΑΡΧΗ-------------//
             
             request.setCharacterEncoding("UTF-8");//Encoding to UTF_8 για να διαβάζει και τα ελληνικά
             response.setCharacterEncoding("UTF-8");//Το ίδιο και στην απόκριση
try{
            //Δήλωση μεταβλητών που αρχικοποιούνται μέσω της μεθόδου post
           String barcode= request.getParameter("code");
           String name= request.getParameter("pname");
           String descr =request.getParameter("description");
          

         //Δήλωση Driver και URL της βάσης
       String JDBC_Driver= "com.mysql.jdbc.Driver";
       String DB_URL="jdbc:mysql://localhost:3306/dbproducts?characterEncoding=UTF-8";

       //Στοιχεία σύνδεσης με την βάση
       String user="prodadmin";
       String pass="78910";

       //Αρχικοποίηση αντικειμένων σύνδεσης σε null
       Connection conn=null;
       java.sql.Statement dbstate=null;

       try {
           //Φόρτωση driver στην μνήμη
         Class.forName(JDBC_Driver);
      //Άνοιγμα σύνδεσης με την βάση δίνοντας το URL της βάσης,όνομα χρήστη και κωδικό
      conn = DriverManager.getConnection(DB_URL, user, pass);

      if(conn!=null){

          //Αν η σύνδεση επιτευχθεί με την βάση
       String sql=""; //Το query που θα δώσουμε στην βάση
      dbstate=conn.createStatement();//Δημιουργία statement
    
  //Αν τα πεδία στην φόρμα αναζήτησης είναι όλα κενά τότε εμφανίζονται ολες οι εγγραφές του πίνακα
  if(barcode.isEmpty() && name.isEmpty() && descr.isEmpty()){

    sql="SELECT * FROM products"; //Εντολή sql για εμφάνιση εγγραφών

      }//end-if

  else {//Αλλιώς αν τα πεδία αναζήτησης δεν είναι όλα κενά εμφανίζονται οι γραμμές που περιέχουν μέρος του πεδίου
      //της φόρμας στα αντίστοιχα πεδία της βάσης
   
String sqlBAR="";//Αρχικοποιούμε τις "προσωρινές" εντολές  sql που αφορούν για κάθε πεδίο της φόρμας αναζήτησης την αντίστοιχη εντολή sql
String sqlNAME="";//για το barcode έχουμε το sqlBAR, για το όνομα προϊόντος το sqlNAME,και για την περιγραφή το sqlDESC 
String sqlDESC="";

//Στο τέλος η ΤΕΛΙΚΗ εντολή sql περιέχει είτε ΕΝΩΣΗ των προηγούμενων εντολών αν παραπάνω από ένα πεδία είναι 
//είναι συμπληρωμένα ή την εντολή LIKE του αντίστοιχου πεδίου της φόρμας

if(!barcode.isEmpty()) {
//Ελέγχουμε ξεχωριστά κάθε πεδίο αν είναι κενό και αν δεν είναι αρχικοποιούμε την αντίστοιχη εντολή sql
  //Μας εμφανίζονται οι εγγραφές που περιέχουν στο πεδίο της βάσης το κομμάτι της φόρμας εισαγωγής
  
  sqlBAR  ="SELECT * FROM products WHERE prodcode LIKE '%"+barcode+"%'";
  
  sql=sqlBAR;//Αρχικοποίηση ΤΕΛΙΚΗΣ sql εντολής
}//end-if-bar 

if(!name.isEmpty()) {
   //Αν το όνομα δεν είναι κενό τότε αρχικοποιούμε και την εντολή με το όνομα όπως φαίνεται εδώ 
   sqlNAME ="SELECT * FROM products WHERE prodname LIKE '%"+name+"%'";
    
      if(sql.isEmpty()){
 //Ελέγχουμε αν το τελικό query είναι κενό μέχρι τώρα.Αν είναι τότε το αρχικοποιούμε με το όνομα όπως φαίνεται στην εντολή    
          sql=sqlNAME;
          
   }//end-if-sql
      
      else{
          
    sql+=" UNION "+sqlNAME;
    
      }
   
}//end-if-name

if(!descr.isEmpty()) {
    
   sqlDESC ="SELECT * FROM products WHERE description LIKE '%"+descr+"%'";
   
     if(sql.isEmpty()){
         
    sql=sqlDESC;
    
 }//end-else-sql
     
     else{
         
       sql+=" UNION "+sqlDESC;  
         
 }//end-else-sql
    
}//end-if-descr---//
}//end-else

      //------------ΑΡΧΗ ΕΝΤΟΛΩΝ ΓΙΑ ΕΜΦΑΝΙΣΗ ΤΟΥ ΑΠΟΤΕΛΕΣΜΑΤΟΣ ΣΕ ΠΙΝΑΚΑ------------//

      ResultSet rs= dbstate.executeQuery(sql); //Το αποτέλεσμα της αναζήτησης μέσω του interface ResultSet
      rs.last();//Μετακίνηση cursor στο τέλος του πίνακα
     int rows= rs.getRow(); //Αποθήκευση του αριθμού της τελευταίας γραμμής του πίνακα
      rs.beforeFirst(); //Μετακίνηση cursor πριν την πρώτη γραμμή



      //Αν υπάρχει αποτέλεσμα και οι γραμμές είναι μεγαλύτερες του 0 τότε εμφάνισε πίνακα με τα αποτελέσματα
    if(rows>0){  %> 
  
   <table id="t"><!---Αρχή Πίνακα----->
           <thead>
         <tr>
                <th>ID</th>
                <th>Barcode</th>
                <th>Όνομα</th>
                <th>Τιμή&nbsp;(&euro;)</th>
                <th>ΦΠΑ&nbsp;(&euro;)</th>
                <th>Περιγραφή</th>

          </tr><!--Τέλος αρχικής γραμμής-->
           </thead>
           <tbody>
        <% //Όσο υπάρχει επόμενη γραμμή στο αποτέλεσμα με την μετακίνηση του δείκτη στην επόμενη γραμμή
            while(rs.next()){ %>
           
               <tr> 
               <%
             //Ανάκτηση τιμών από κάθε γραμμή του αποτελέσματος
             int id= rs.getInt("id");
             String bar= rs.getString("prodcode");
             String pname= rs.getString("prodname");
             String price= String.format("%.2f",rs.getFloat("price"));
             String vat= String.format("%.2f",rs.getFloat("vat"));
             String desc = rs.getString("description");

              %>
              <!---Δημιουργία κελιών με τις αντίστοιχες τιμές------>
             <td><%=id%></td>
             <td><%=bar%></td>
             <td><%=pname%></td>
             <td><%=price%></td>
             <td><%=vat%></td>
             <td><%=desc%></td>
             </tr>
          
          <% }//end-while   %>
          </tbody>
        
        </table><!----Τέλος Πίνακα------>
    
<%     //------------ΤΕΛΟΣ ΕΝΤΟΛΩΝ ΓΙΑ ΕΜΦΑΝΙΣΗ ΤΟΥ ΑΠΟΤΕΛΕΣΜΑΤΟΣ ΣΕ ΠΙΝΑΚΑ------------//
    
          }//-----end-if-rows----//

else { %>

<h3 id="error">No matching record found!</h3>

<%
}//end-else
      //-----Κλείσιμο σύνδεσης-------//
       conn.close();
      dbstate.close();

}//end-----if-----conn----//
}//------end-try-----//

           catch(SQLException e){
               conn=null;
               dbstate=null;
%> <!--Σε περίπτωση εξαίρεσης εμφανίζεται ειδικό μήνυμα -->
            <h3>ERROR IN DATABASE!</h3>

           <% }//end-catch

        catch(ClassNotFoundException e){ %>
        
        <!--Αν δεν φορτωθεί επιτυχώς ο driver τότε εμφανίζεται το μήνυμα -->
        <h3>Unable to load the Driver!</h3>
        
        <% }//end-catch

}//END-FIRST-TRY

catch(NullPointerException e){ %>
<h3>ERROR!</h3> <% }


  %><!---------ΤΕΛΟΣ---------->
  <br>
<input type="submit" style="font-size:14px" onclick="location.href='search.html'" value="Νέα αναζήτηση">&nbsp;
   &nbsp;<input type="submit" style="font-size:14px" onclick="location.href='index.html'" value="Επιστροφή στην αρχική σελίδα">
        </body>
</html><!--ΤΕΛΟΣ ΣΕΛΙΔΑΣ ΑΝΑΖΗΤΗΣΗΣ ΠΡΟΪΟΝΤΟΣ--------->
