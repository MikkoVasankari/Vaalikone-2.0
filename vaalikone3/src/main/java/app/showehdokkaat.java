package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import dao.Dao;
import dao.Daor;
import data.ehdokasVastaukset;
import data.kysymys;
import dao.showEvastaus;
import data.Ehdokas;
import dao.kyselydao;

@WebServlet("/sEhdokkaat")
public class showehdokkaat extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Daor daor = null;
	private showEvastaus showEvastaus = null;
	private kyselydao dao = null;

	@Override
	public void init() {
		daor = new Daor("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
		showEvastaus = new showEvastaus("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
		dao = new kyselydao("jdbc:mysql://localhost:3306/vaalikone", "root", "root");
	}

	public showehdokkaat() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Ehdokas> list = null;
		if (daor.getConnection()) {
			// 
			list = daor.readAllEhdokkaat();
			request.setAttribute("ehdokkaat", list);
		} else {
			System.out.println("No connection to database");
		}

		///////////// EHDOKKAAN VASTAUKSET
		ArrayList<ehdokasVastaukset> list2 = null;
		if (showEvastaus.getConnection()) {
			list2 = showEvastaus.readAllVastaukset();
			request.setAttribute("ehdokasvastaukset", list2);

		} else {
			System.out.println("No connection to database");
		}
		///////////// KYSYMYKSET
		ArrayList<kysymys> list3 = null;
		if (dao.getConnection()) {
			list3 = dao.readAllKysymykset();
			request.setAttribute("kysymykset", list3);
		} else {
			System.out.println("No connection to database");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/showehdokas.jsp"); // lukee jsp tiedoston
																						// kansiosta
		rd.forward(request, response);
	}
}