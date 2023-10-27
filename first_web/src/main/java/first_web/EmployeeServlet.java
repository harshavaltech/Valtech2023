package first_web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

public class EmployeeServlet extends HttpServlet{
private EmployeeDAO dao = new EmployeeDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession sess=req.getSession(true);
			sess.setAttribute("current",dao.FirstId());
			req.setAttribute("emp",dao.getEmployee(dao.FirstId()));
			req.getRequestDispatcher("employee.jsp").forward(req,resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession sess=req.getSession();
		int current =(Integer)sess.getAttribute("current");
		String submit=req.getParameter("submit");
		try {
			
		
		if("First".equals(submit)) {
			current=dao.FirstId();
		}
		else if("Last".equals(submit)) {
			current=dao.LastId();
		}
		else if("Next".equals(submit)) {
			current=dao.NextId(current);
					}
		else if("Previous".equals(submit)){
			current=dao.PreviousId(current);
		}
		
		sess.setAttribute("current", current);
		sess.setAttribute("emp",dao.getEmployee(current));
		
		req.setAttribute("emp", dao.getEmployee(current));
		//req.getRequestDispatcher("employee.jsp").forward(req, resp);
		resp.sendRedirect("employee.jsp");
	}
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}

