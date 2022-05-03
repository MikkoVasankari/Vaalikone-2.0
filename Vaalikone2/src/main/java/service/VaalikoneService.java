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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import data.ehdokkaat;


@Path("/vaalikoneservice")
public class VaalikoneService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	

	@GET
	@Path("/readtoupdateehdokkaat/{ehdokas_id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ehdokkaat readToUpdateEhdokkaat(@PathParam("ehdokas_id") int ehdokas_id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e=em.find(ehdokkaat.class, ehdokas_id);
		em.getTransaction().commit();
		return e;
	}	
	
	@GET
	@Path("/readehdokkaat")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ehdokkaat> readEhdokkaat() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<ehdokkaat> list=em.createQuery("select e from ehdokkaat e").getResultList();
		em.getTransaction().commit();
		return list;		
		}
	
	
	@POST
	@Path("/addehdokas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ehdokkaat> addEhdokas(ehdokkaat ehdokkaat) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(ehdokkaat);
		em.getTransaction().commit();
		List<ehdokkaat> list=readEhdokkaat();		
		return list;
	}	
	
	@PUT
	@Path("/updateehdokas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ehdokkaat> updateEhdokas(ehdokkaat ehdokkaat) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e=em.find(ehdokkaat.class, ehdokkaat.getEhdokas_id());
		if (e!=null) {
			em.merge(ehdokkaat);
		}
		List<ehdokkaat> list=readEhdokkaat();
		return list;
	}
	
	
	@GET
    @Path("/getehdokkaat")
    @Produces(MediaType.APPLICATION_JSON)
    public void getehdokkaat(@Context HttpServletRequest request,
    	    @Context HttpServletResponse response)
	{
    	
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<ehdokkaat> list = em.createQuery("select x from ehdokkaat x").getResultList();
        em.getTransaction().commit();
        request.setAttribute("ehdokkaat", list);
        RequestDispatcher rd=request.getRequestDispatcher("/jsp/MuokkaaEhdokkaita.jsp");
        try {
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	@DELETE
	@Path("/deleteehdokas/{ehdokas_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ehdokkaat> deleteehdokas(@PathParam("ehdokas_id") int ehdokas_id ) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e=em.find(ehdokkaat.class, ehdokas_id);
		if (e!=null) {
			em.remove(e);//The actual insertion line
		}
		em.getTransaction().commit();
		//Calling the method readEhdokkaat() of this service
		
		List<ehdokkaat> list=readEhdokkaat();		
		return list;
	}	
	
	
	
 }
