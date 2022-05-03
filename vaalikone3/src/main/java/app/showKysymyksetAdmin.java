package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.kyselydao;
import data.kysymys;

@WebServlet("/showKysymysAdmin")
public class showKysymyksetAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private kyselydao dao = null;

	@Override
	public void init() {
		dao = new kyselydao("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
	}

	public showKysymyksetAdmin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<kysymys> lista = null;
		if (dao.getConnection()) {
			lista = dao.readAllKysymykset();
		} else {
			System.out.println("No connection to database");
		}
		
		request.setAttribute("kysymyslista", lista);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/KysymyksetFileAdmin.jsp");
		rd.forward(request, response);
	}
}
