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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import data.ehdokkaat;
import data.vastaukset;

@Path("/ehdokasservice")
public class ehdokasService {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikone");

//	@GET
//	@Path("/getehdokas/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public ehdokkaat getEhdokas(@PathParam("id") int ehdokas_id) {
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		ehdokkaat e = em.find(ehdokkaat.class, ehdokas_id);
//		em.getTransaction().commit();
//		return e;
//	}
	
	@GET
    @Path("/getehdokas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void getEhdokas(@PathParam("id") int ehdokas_id,
            @Context HttpServletRequest request,
            @Context HttpServletResponse response) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ehdokkaat e = em.find(ehdokkaat.class, ehdokas_id);
        em.getTransaction().commit();

        request.setAttribute("ehdokas", e);
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
		List<vastaukset> list = em.createQuery("select x from vastaukset x").getResultList();
		em.getTransaction().commit();
		return list;
	}

	@GET
	@Path("/getehdokkaanvastaukset/{ehdokas_num}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<vastaukset> getehdokkaanvastaukset(@PathParam("ehdokas_num") int ehdokas_num) {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		List<vastaukset> list = em.createQuery("select x from vastaukset x where x.ehdokas_num=?1")
				.setParameter(1, ehdokas_num).getResultList();
		em.getTransaction().commit();
		em.close();
		return list;
	}

}
