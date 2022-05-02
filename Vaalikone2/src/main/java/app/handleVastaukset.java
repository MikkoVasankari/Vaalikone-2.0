package app;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import data.ehdokkaat;
import data.kysymykset;
import data.vastaukset;

/**
 * Servlet implementation class handleVastaukset
 */
@WebServlet("/handleVastaukset")
public class handleVastaukset extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikone");
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String ehdokasid = request.getParameter("id");
		int integerehdokasid = Integer.parseInt(ehdokasid);
		
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();
		ehdokkaat e = em1.find(ehdokkaat.class, integerehdokasid);
		em1.getTransaction().commit();
		
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<vastaukset> list1 = em2.createQuery("select x from vastaukset x where x.ehdokas_id=?1")
				.setParameter(1, integerehdokasid).getResultList();
		
		EntityManager em3 = emf.createEntityManager();
		em3.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<kysymykset> list2 = em3.createQuery("select x from kysymykset x").getResultList();
		em3.getTransaction().commit();
		System.out.println(list2.size());
		
		List<vastaukset> list = updateVastaukset(request);
		
		request.setAttribute("ehdokas", e);
		request.setAttribute("ehdokkaanvastaukset", list1);
		request.setAttribute("kysymykset", list2);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/ehdokas.jsp");
		rd.forward(request, response);
		
		
	}
	
	private List<vastaukset> updateVastaukset(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		int integerID = Integer.parseInt(id);
		
//		Haetaan kaikki kysymykset tietokannasta
		EntityManager em3 = emf.createEntityManager();
		em3.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<kysymykset> list2 = em3.createQuery("select x from kysymykset x").getResultList();
		em3.getTransaction().commit();
		
//		Haetaan kaikki vastaukset tietokannasta
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<vastaukset> list = em.createQuery("select x from vastaukset x").getResultList();
		em.getTransaction().commit();
		
		
//		Vissii lisää tällä hetkellä vain yhden vastauksen
//		Loopataan kaikki vastaukset lista läpi haetaan uusi vastaus kyselystä
		for (int i = 0; list2 != null && i < list2.size(); i++) {
			int j = i + 1;
			String q = request.getParameter("q"+j);
			int integerQ = Integer.parseInt(q);
			
			vastaukset vastaukset = list.get(i);
			kysymykset kysymys = list2.get(i);
			System.out.println(j +" "+q);
			vastaukset vastaus = new vastaukset(integerID,integerQ,kysymys.getKysymys_id(),vastaukset.getEhdokas_num());
			
			String uri = "http://127.0.0.1:8080/rest/ehdokasservice/updatevastaus";
			Client c=ClientBuilder.newClient();
			WebTarget wt=c.target(uri);
			Builder b=wt.request();
			
			Entity<vastaukset> e=Entity.entity(vastaus,MediaType.APPLICATION_JSON);
			GenericType<List<vastaukset>> genericList = new GenericType<List<vastaukset>>() {};
			
			List<vastaukset> returnedList=b.put(e, genericList);
			return returnedList;
		}
		return null;
	

	}

}
