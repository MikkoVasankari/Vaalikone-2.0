package service;

import java.io.IOException;
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
import javax.ws.rs.PUT;
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


//	Haetaan kaikki ehdokkaat tietokannasta
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
//	Haetaan kaikki vastaukset tietokannasta
	@GET
	@Path("/getvastaukset")
	@Produces(MediaType.APPLICATION_JSON)
	public List<vastaukset> getVastaukset() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<vastaukset> list = em.createQuery("select b from vastaukset b").getResultList();
		em.getTransaction().commit();
		return list;
	}
//	Haetaan kaikki kysymykset tietokannasta
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

	
// Tallennetaan vastaukset tietokantaa
	@PUT
	@Path("/updatevastaus")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<vastaukset> updateVastaukset(vastaukset vastaus) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		vastaukset v=em.find(vastaukset.class, vastaus.getVastaus());
		if (v!=null) {
			em.merge(vastaus);//The actual update line
		}
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<vastaukset> list=getVastaukset();		
		return list;
	}
	

}
