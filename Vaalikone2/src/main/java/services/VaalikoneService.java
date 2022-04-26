package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import data.ehdokkaat;


@Path("/vaalikoneservice")
public class VaalikoneService {
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("vaalikone");
	
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
	@Path("/addehdokkaat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ehdokkaat> addEhdokkaat(ehdokkaat ehdokkaat) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(ehdokkaat);//The actual insertion line
		em.getTransaction().commit();
		//Calling the method readFish() of this service
		List<ehdokkaat> list=readEhdokkaat();		
		return list;
	}	
	
	@GET
	@Path("/readtoupdateehdokkaat/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ehdokkaat readToUpdateEhdokkaat(@PathParam("id") int id) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e=em.find(ehdokkaat.class, id);
		em.getTransaction().commit();
		return e;
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
	
	
 }
