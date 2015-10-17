import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.IOException;

public class EditarProductos extends HttpServlet{
	
	public void doGet(HttpServletRequest request,
	HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String descripcion = request.getParameter("descripcion");
		out.println("descripcion"+descripcion);
		
		
	}
	
	
	
} 