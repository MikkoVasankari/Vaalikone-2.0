package service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import data.ehdokkaat;
import data.vastaukset;
import data.kysymykset;

@Path("/ehdokasservice")
public class ehdokasService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikone");

	@GET
	@Path("/getehdokas/{ehdokas_num}")
	@Produces(MediaType.APPLICATION_JSON)
	public void getEhdokas(@PathParam("ehdokas_num") int ehdokas_num, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {

		// Haetaan tietty ehdokas
		EntityManager em1 = emf.createEntityManager();
		em1.getTransaction().begin();
		ehdokkaat e = em1.find(ehdokkaat.class, ehdokas_num);
		em1.getTransaction().commit();

		// Haetaan tietyn ehdokkaan vastaukset
		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<vastaukset> list1 = em2.createQuery("select x from vastaukset x where x.ehdokas_id=?1")
				.setParameter(1, ehdokas_num).getResultList();

		// Haetaan kysymykset
		EntityManager em3 = emf.createEntityManager();
		em3.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<kysymykset> list2 = em3.createQuery("select x from kysymykset x").getResultList();
		em3.getTransaction().commit();

		request.setAttribute("ehdokas", e);
		request.setAttribute("ehdokkaanvastaukset", list1);
		request.setAttribute("kysymykset", list2);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/ehdokas.jsp");

		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}



	@GET
	@Path("/getehdokkaat")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ehdokkaat> getehdokkaat() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<ehdokkaat> list = em.createQuery("select x from ehdokkaat x").getResultList();
		em.getTransaction().commit();
		return list;
	}

	@GET
	@Path("/getvastaukset")
	@Produces(MediaType.APPLICATION_JSON)
	public List<vastaukset> getVastaukset() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<vastaukset> list = em.createQuery("select x from vastaukset x").getResultList();
		em.getTransaction().commit();
		return list;
	}

	@GET
	@Path("/getkysymykset")
	@Produces(MediaType.APPLICATION_JSON)
	public List<kysymykset> getkysymykset() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<kysymykset> list = em.createQuery("select x from kysymykset x").getResultList();
		em.getTransaction().commit();
		return list;
	}

	@GET
	@Path("/getehdokkaanvastaukset/{ehdokas_num}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<vastaukset> getehdokkaanvastaukset(@PathParam("ehdokas_num") int ehdokas_num) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<vastaukset> list = em.createQuery("select x from vastaukset x where x.ehdokas_num=?2")
				.setParameter(2, ehdokas_num).getResultList();
		em.getTransaction().commit();
		em.close();
		return list;
	}

	// Tallennetaan päivitetyt ehdokkaan vastaukset tietokantaa
//	@POST
//	@Path("/savevastaukset")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public void savevastaukset(vastaukset ehdokkaanVastaukset) {
//
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		em.persist(ehdokkaanVastaukset);
//		em.getTransaction().commit();
//	}
	
	@POST
	@Path("/savevastaukset")
	public String printtaavastaukset() {
//		String id = request.getParameter("ehdokas");
//		System.out.print(id);
//		return id;
		
		return "xd";
	}
	

}
