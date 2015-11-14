import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class FormLogin extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        response.setContentType("text/html");
	PrintWriter out = response.getWriter();
String usuariotext="";
String passwordtext="";
      
      Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        out.println("<h1>Si hay cookies</h1>");
      for (int i = 0; i < cookies.length; i++) {
        if (cookies[i].getName().equals("usuario")) {
          usuariotext=cookies[i].getValue();        
        }
	  if (cookies[i].getName().equals("password")) {
          passwordtext=cookies[i].getValue();        
        }
      }
    }
else
{
    out.println("No pos no hay ");
}
       
        
//imprimiendo el form

out.println("<form action='http://localhost:8080/agregaUsuarios/Login' method='get'> <label for='nombre'>Usuario: </label> <br/><input type='text' id='usuario' name='usuario' value='"+usuariotext+"' /><br> <label for='pass1'>Password: </label> <br/><input type='password' id='pass1' name='pass1' value='"+passwordtext+"' /><br><label for='remember'>Recuerdame: </label> <br/><input type='checkbox' id='remember' name='remember'/><br><input type='submit' name='Enviar' value='Enviar'></form>");


    }

}
