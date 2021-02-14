package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public final class search_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("<!---Εισαγωγή βιβλιοθηκών----------------->\n");
      out.write("\n");
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
      out.write("       <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <title>Σελίδα αναζήτησης</title>\n");
      out.write("        <style> /*******Κανόνες CSS για το styling των στοιχείων*******/\n");
      out.write("          \n");
      out.write("        table,th,td{\n");
      out.write("           \n");
      out.write("        margin-top:-5px;\n");
      out.write("    font-family: sans-serif;\n");
      out.write("    margin-left:5px;\n");
      out.write("    border: 2px solid black;\n");
      out.write("    border-collapse:collapse;\n");
      out.write("    padding:10px;\n");
      out.write("    text-align:center;\n");
      out.write("    border-spacing:10px;\n");
      out.write("   }  \n");
      out.write("\n");
      out.write("   th {  background-color: beige;}\n");
      out.write("   td {  background-color: ghostwhite;}\n");
      out.write("   \n");
      out.write("  \n");
      out.write("  #t{width:70%}\n");
      out.write("    \n");
      out.write("  input { margin-bottom:10px;\n");
      out.write("         padding-bottom:5px;}\n");
      out.write("  \n");
      out.write("#error { color: red; }          \n");
      out.write("          \n");
      out.write("</style>\n");
      out.write("    </head>\n");
      out.write("    <body style=\"background-color: tan\">\n");
      out.write("        \n");
      out.write("        <h3>Αποτέλεσματα:</h3>\n");
      out.write("    \n");
      out.write("                 \n");
      out.write("         ");
 //--------ΑΡΧΗ-------------//
             
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
    if(rows>0){  
      out.write(" \n");
      out.write("  \n");
      out.write("   <table id=\"t\"><!---Αρχή Πίνακα----->\n");
      out.write("           <thead>\n");
      out.write("         <tr>\n");
      out.write("                <th>ID</th>\n");
      out.write("                <th>Barcode</th>\n");
      out.write("                <th>Όνομα</th>\n");
      out.write("                <th>Τιμή&nbsp;(&euro;)</th>\n");
      out.write("                <th>ΦΠΑ&nbsp;(&euro;)</th>\n");
      out.write("                <th>Περιγραφή</th>\n");
      out.write("\n");
      out.write("          </tr><!--Τέλος αρχικής γραμμής-->\n");
      out.write("           </thead>\n");
      out.write("           <tbody>\n");
      out.write("        ");
 //Όσο υπάρχει επόμενη γραμμή στο αποτέλεσμα με την μετακίνηση του δείκτη στην επόμενη γραμμή
            while(rs.next()){ 
      out.write("\n");
      out.write("           \n");
      out.write("               <tr> \n");
      out.write("               ");

             //Ανάκτηση τιμών από κάθε γραμμή του αποτελέσματος
             int id= rs.getInt("id");
             String bar= rs.getString("prodcode");
             String pname= rs.getString("prodname");
             String price= String.format("%.2f",rs.getFloat("price"));
             String vat= String.format("%.2f",rs.getFloat("vat"));
             String desc = rs.getString("description");

              
      out.write("\n");
      out.write("              <!---Δημιουργία κελιών με τις αντίστοιχες τιμές------>\n");
      out.write("             <td>");
      out.print(id);
      out.write("</td>\n");
      out.write("             <td>");
      out.print(bar);
      out.write("</td>\n");
      out.write("             <td>");
      out.print(pname);
      out.write("</td>\n");
      out.write("             <td>");
      out.print(price);
      out.write("</td>\n");
      out.write("             <td>");
      out.print(vat);
      out.write("</td>\n");
      out.write("             <td>");
      out.print(desc);
      out.write("</td>\n");
      out.write("             </tr>\n");
      out.write("          \n");
      out.write("          ");
 }//end-while   
      out.write("\n");
      out.write("          </tbody>\n");
      out.write("        \n");
      out.write("        </table><!----Τέλος Πίνακα------>\n");
      out.write("    \n");
     //------------ΤΕΛΟΣ ΕΝΤΟΛΩΝ ΓΙΑ ΕΜΦΑΝΙΣΗ ΤΟΥ ΑΠΟΤΕΛΕΣΜΑΤΟΣ ΣΕ ΠΙΝΑΚΑ------------//
    
          }//-----end-if-rows----//

else { 
      out.write("\n");
      out.write("\n");
      out.write("<h3 id=\"error\">No matching record found!</h3>\n");
      out.write("\n");

}//end-else
      //-----Κλείσιμο σύνδεσης-------//
       conn.close();
      dbstate.close();

}//end-----if-----conn----//
}//------end-try-----//

           catch(SQLException e){
               conn=null;
               dbstate=null;

      out.write(" <!--Σε περίπτωση εξαίρεσης εμφανίζεται ειδικό μήνυμα -->\n");
      out.write("            <h3>ERROR IN DATABASE!</h3>\n");
      out.write("\n");
      out.write("           ");
 }//end-catch

        catch(ClassNotFoundException e){ 
      out.write("\n");
      out.write("        \n");
      out.write("        <!--Αν δεν φορτωθεί επιτυχώς ο driver τότε εμφανίζεται το μήνυμα -->\n");
      out.write("        <h3>Unable to load the Driver!</h3>\n");
      out.write("        \n");
      out.write("        ");
 }//end-catch

}//END-FIRST-TRY

catch(NullPointerException e){ 
      out.write("\n");
      out.write("<h3>ERROR!</h3> ");
 }


  
      out.write("<!---------ΤΕΛΟΣ---------->\n");
      out.write("  <br>\n");
      out.write("<input type=\"submit\" style=\"font-size:14px\" onclick=\"location.href='search.html'\" value=\"Νέα αναζήτηση\">&nbsp;\n");
      out.write("   &nbsp;<input type=\"submit\" style=\"font-size:14px\" onclick=\"location.href='index.html'\" value=\"Επιστροφή στην αρχική σελίδα\">\n");
      out.write("        </body>\n");
      out.write("</html><!--ΤΕΛΟΣ ΣΕΛΙΔΑΣ ΑΝΑΖΗΤΗΣΗΣ ΠΡΟΪΟΝΤΟΣ--------->\n");
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
