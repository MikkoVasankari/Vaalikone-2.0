package dao;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.LoginDao;


@WebServlet("/Login")
public class Login extends HttpServlet {
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		
		LoginDao dao = new LoginDao();
		
		if(dao.check(name, pass)) 
		
		{
			HttpSession session = request.getSession();
			session.setAttribute("name", name);
			response.sendRedirect("welcome.jsp");
			
		}
		
		else
		{
			response.sendRedirect("login.jsp");
		}
		
			
	
		
	}

	
}


