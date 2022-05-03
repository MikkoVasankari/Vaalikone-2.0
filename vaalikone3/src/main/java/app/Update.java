package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.kysymys;

@WebServlet(
    name = "Update",
    urlPatterns = {"/update"}
)
public class Update extends HttpServlet {
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
	}
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException {
		response.sendRedirect("index.html");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		String id=request.getParameter("id");
		String kysymys=request.getParameter("kysymys");
		
		kysymys f=new kysymys(id, kysymys);
		
		ArrayList<kysymys> lista=null;
		if (dao.getConnection()) {
			lista=dao.updateKysymys(f);
		}
		
		request.setAttribute("kysymyslista", lista);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/KysymyksetFileAdmin.jsp");
		rd.forward(request, response);
	}
}