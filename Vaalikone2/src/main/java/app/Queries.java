package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import data.ehdokkaat;



@WebServlet("/queries")
public class Queries extends HttpServlet {
	private static final long serialVersionUID = 1L;
    EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Queries() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		selectAllEhdokkaat(out);
	}
    
	private void selectAllEhdokkaat(PrintWriter out) {
		out.println("<h3>Kaikki ehdokkaat</h3>");
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<ehdokkaat> list=em.createQuery("select e from ehdokkaat e").getResultList();
		em.getTransaction().commit();
		em.close();
		printEhdokkaat(out, list);
	}
    

	
	
	private void printEhdokkaat(PrintWriter out, List<ehdokkaat> list) {
		// TODO Auto-generated method stub
		for (int i=0;list!=null && i<list.size();i++) {
			ehdokkaat e=list.get(i);
			out.println(e+"<br>");
		}
		
		
	}

	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
    
    
}
