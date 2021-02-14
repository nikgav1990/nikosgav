package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLException;
import java.sql.DriverManager;
import com.mysql.jdbc.Statement;
import java.sql.Connection;

public final class insert_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!---Εισαγωγή βιβλιοθηκών----------------->\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html><!-------ΑΡΧΗ ΣΕΛΙΔΑΣ ΕΙΣΑΓΩΓΗΣ ΠΡΟΪΟΝΤΟΣ-------->\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Σελίδα εισαγωγής</title>\n");
      out.write("    </head>\n");
      out.write("    <body style=\"background-color:honeydew\">\n");
      out.write("\n");
      out.write("        ");
 //--------ΑΡΧΗ-------------//
            
           
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
 
 if(count>0){ 
      out.write("\n");
      out.write(" \n");
      out.write(" <!----Αν το query εκτελεστεί επιτυχώς τότε εμφανίζεται κατάλληλο μήνυμα---->\n");
      out.write(" <h3>A new record inserted in Products table!</h3>\n");
      out.write(" \n");
      out.write(" ");
 }//end-if

else { 
      out.write(" <!---Σε περίπτωση σφάλματος στην εγγραφή εμφανίζει το παρακάτω μήνυμα-->\n");
      out.write("  <h3>Record did not added!</h3>\n");
      out.write("  \n");
      out.write(" ");
 }//end-else

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
catch(SQLIntegrityConstraintViolationException e){ 
      out.write("\n");
      out.write("       <h3>Barcode must be unique!</h3>\n");
      out.write("    ");
}

         catch(SQLException e){
               conn=null;
               dbstate=null;
               

      out.write(" <!--Σε περίπτωση εξαίρεσης εμφανίζεται το μήνυμα παρακάτω-->\n");
      out.write("            <h3>ERROR IN CONNECTION!</h3>\n");
      out.write("   \n");
      out.write("           ");
 }//end-catch


        catch(ClassNotFoundException e){ 
      out.write("\n");
      out.write("        <!--Αν δεν φορτωθεί επιτυχώς ο driver τότε εμφανίζεται το μήνυμα -->\n");
      out.write("        <h3>Unable to load the Driver!</h3> ");
  }
   }//end-else
}//FIRST-TRY
        
        catch(NullPointerException e){ 
      out.write("\n");
      out.write("        <h3>ERROR!Please insert a product!</h3>  ");
}
        
        


//---------------------------------------END OF HANDLING EXCEPTIONS---------------------------------//
      
      out.write("<!---------ΤΕΛΟΣ---------->\n");
      out.write("  <input type=\"submit\" style=\"font-size:13px\" onclick=\"location.href='insert.html'\" value=\"Εισαγωγή νέου προϊόντος\">&nbsp;\n");
      out.write("   &nbsp;<input type=\"submit\" style=\"font-size:13px\" onclick=\"location.href='index.html'\" value=\"Επιστροφή στην αρχική σελίδα\">\n");
      out.write("</body>\n");
      out.write("</html><!--ΤΕΛΟΣ ΣΕΛΙΔΑΣ ΕΙΣΑΓΩΓΗΣ ΠΡΟΪΟΝΤΟΣ--------->\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
