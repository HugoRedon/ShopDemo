import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
public class Login extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
         HttpSession sesion = request.getSession();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String usuario = request.getParameter("usuario");
        String pass1 = request.getParameter("pass1");
        String remember = request.getParameter("remember");

        if("on".equals(remember))
        {
        Cookie miCookie = new Cookie("usuario",usuario);
  	response.addCookie(miCookie);
	Cookie miCookie2 = new Cookie("password",pass1);
  	response.addCookie(miCookie2);
	out.println("Y se crearon las cookies");
       }
        out.println("<h1>Verificando Login</h1><br>Tu usuario fue " + usuario);
        out.println("<br>Mi pass " + pass1);
        out.println("<br>Confirmacion " + remember);
        //response.SendRedirect("");
  
       

       	try {
            Class.forName("com.mysql.jdbc.Driver");
            //out.println("Driver encontrado");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "root");
            //Statement sentencia= conn.createStatement(); 
            PreparedStatement psentencia = conn.prepareStatement("select * from usuarios where nombe = ? and password = ?");
                psentencia.setString(1,usuario); 
		psentencia.setString(2,pass1);              
                ResultSet rs = psentencia.executeQuery();
				
				if (rs.next()){
				sesion.setAttribute("usuario", rs.getString("nombe"));
				sesion.setAttribute("password", rs.getString("password"));
out.println("Esto ya viene de la sesion " + sesion.getAttribute("usuario"));
				//response.sendRedirect("http://localhost:8080/agregaUsuarios/formServlet.html");
//cerrar sesión
//sesion.invalidate();
					
				}
				else
{
out.println("<h1>Usuario no registrado</h1>");
}
				rs.close();
				psentencia.close();
				conn.close();
            
            //out.println("conexion exitosa");
			
			
			

        } catch (ClassNotFoundException ex) {
            out.println("Driver NO encontrado");
        } catch (SQLException ex) {
            out.println("Error " + ex);
        }


    }

}
