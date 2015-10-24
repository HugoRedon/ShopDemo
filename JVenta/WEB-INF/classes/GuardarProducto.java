import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.*;


public class GuardarProducto extends HttpServlet{
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		//out.println("id"+ id);	
        		
		
		String nombre = request.getParameter("nombre");
		//out.println("nombre"+ nombre);
		
		String usuario = request.getParameter("usuario");
		//out.println("usuario"+ usuario);
		
		String password = request.getParameter("password");
		//out.println("password"+ password);
		
		String descripcion = request.getParameter("descripcion");
		//out.println("descripcion"+ descripcion);
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            //out.println("Driver encontrado");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina", "root", "tresct");
            //Statement sentencia= conn.createStatement(); 
            Statement psentencia = conn.createStatement();
                //psentencia.setInt(1, Integer.parseInt(id));               
				String query = "update productos set nombre='"+nombre+"', usuario='"+usuario+"', password='"+password+"', descripcion='"+descripcion+"' where id = "+ id;
				out.println(query);
                psentencia.execute(query);
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

