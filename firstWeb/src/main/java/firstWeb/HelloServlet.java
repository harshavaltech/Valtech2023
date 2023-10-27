package firstWeb;



import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class HelloServlet extends HttpServlet {
	
	private int count;
	
	@Override
	public void init() throws ServletException {
		System.out.println("Init of the Hello Servlet");
	}
	
	@Override
	public void destroy() {
		System.out.println("Destory the Hello Servlet Count "+count);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("name");
//		resp.getWriter().print("Hello Servlet "+count++);
		PrintWriter out=resp.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Displaying through Servlet</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("Hi <b>"+name+"</b><br/>");
		out.print("You are visitor no. "+count+" for this website . . .!");
		out.print("</body>");
		out.print("</html>");
	}
}