package first_web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login1_Servlet
 */
public class Login1_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login1_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //if name and pass is wrong show thr login page again with msg...
    //if name is less than 4 chars then add msg and login page..
    //if pass is less than 8 then add msg and login page
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().print("User name="+request.getParameter("name") +"pass word="+request.getParameter("pass"));
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//super.doPost(request, response);
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		if("root".equals(name) && "root".equals(pass)){
			request.setAttribute("message","Hi Scott.. how r u");
		}
		else {
			request.setAttribute("message","only Scot is a valid user ");
		}
		if(name.length()<4) {
			request.setAttribute("Message1","Name is less than 3");
		}
		if(pass.length()<4) {
			request.setAttribute("Message2","pass is less than 3");
			
		}
		request.getRequestDispatcher("loginresults.jsp").forward(request, response);
			
		}
	
	}


