import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.*;

public class BuscarProductos extends HttpServlet{
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//String id = request.getParameter("id");
		//out.println("id"+ id);	
        		
		
		String nombre = request.getParameter("nombre");
		//out.println("nombre"+ nombre);
		
		//String usuario = request.getParameter("usuario");
		//out.println("usuario"+ usuario);
		
		//String password = request.getParameter("password");
		//out.println("password"+ password);
		
		//String descripcion = request.getParameter("descripcion");
		//out.println("descripcion"+ descripcion);
		
		try {
            Class.forName("com.mysql.jdbc.Driver");
            //out.println("Driver encontrado");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nomina", "root", "tresct");
            //Statement sentencia= conn.createStatement(); 
            PreparedStatement psentencia = conn.prepareStatement("select * from producto where nombre like ?");
                psentencia.setString(1,"%"+ nombre+ "%");               
                ResultSet rs = psentencia.executeQuery();
				String html = "";
				html = "<html><body><table>";
				while (rs.next()){
					String elnombre=rs.getString("nombre");
					String description = rs.getString("descripcion");
					String precio = rs.getString("precio");
					Integer id = rs.getInt ("id");
					//Integer id=rs.getInt("id");
					//out.println(HTMLCode(elnombre.toString(),rs.getString("nombre"),rs.getString("descripcion"),rs.getString("precio"),rs.getString("id")));
					html =html+ generateTable(elnombre,description,precio,id);
				}
				html = html+ "</table></body></html>";
				
				out.println(html);
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
	public String generateTable(String name, String description, String precio, int id){
		return "<tr><td>"+name+ "</td><td>" + description+"</td><td>" + precio+"</td><td><form action='EliminarProductos' method='get'><input type='hidden' name='id' value='"+ id +"'/><input type='submit' value='Eliminar'/></form></td><td><form action='EditarProductos' method='get'><input type='hidden' name='id' value='"+ id +"'/><input type='submit' value='Editar'/></form></td></tr>";
	}
	
	//public String HTMLCode(String id, String nombre,String usuario,String password, String descripcion){
		
		//String str =  "<html><header></header><body><h1>Lista de Productos</h1><form action='GuardarProducto' method='get'><table><td></td></tr><tr><tr><td><label>Nombre del articulo</label></td><td><input type='hidden' name='id' value='"+ id +"'/><input type='text' name='nombre' value='"+ nombre +"'/></br><td></tr><tr><td><label>Usuario</label></td><td><input type='text' name='usuario' value='"+ usuario +"'/></br></td><tr><td><label>Password</label></td><td><input type='text' name='password' value='"+ password +"'/></td></tr><tr><td><label>Descripcion</label></td><td><input type='text' name='descripcion' value='"+ descripcion +"'/></td></tr><tr><td></td><td></td><td><input type='submit' value='Editar'/></td></tr></table></form></body></html>";
		//return str;
	//}
	
} 

