import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class agregaUsuarios extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String nombre = request.getParameter("nombre");
        String pass1 = request.getParameter("pass1");
        String pass2 = request.getParameter("pass2");
        String perfil = request.getParameter("perfil");
        String correo = request.getParameter("correo");
        String dir = request.getParameter("dir");
        String tel = request.getParameter("tel");
        out.println("<h1>Confirmando datos</h1><br>Me llamo " + nombre);
        out.println("<br>Mi pass " + pass1);
        out.println("<br>Confiracion " + pass2);
        out.println("<br>Mi perfil " + perfil);
        out.println("<br>Mi mail" + correo);
        out.println("<br>Mi Direccion " + dir);
        out.println("<br>Mi tel " + tel);


        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver encontrado");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda", "root", "root");
            //Statement sentencia= conn.createStatement(); 
            try (PreparedStatement psentencia = conn.prepareStatement("insert into usuarios (nombe,password,perfil,correo,direccion,telefono) values (?, ?, ?, ?, ?, ?)")) {
                psentencia.setString(1, nombre);
                psentencia.setString(2, pass1);
                psentencia.setString(3, nombre);
                psentencia.setString(4, pass1);
                psentencia.setString(5, nombre);
                psentencia.setString(6, pass1);
                psentencia.execute();
            }
            System.out.println("conexion exitosa");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver NO encontrado");
        } catch (SQLException ex) {
            System.out.println("Error " + ex);
        }


    }

}
