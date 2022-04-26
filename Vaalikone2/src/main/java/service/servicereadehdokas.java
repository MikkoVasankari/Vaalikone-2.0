package service;

import java.awt.List;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import data.ehdokkaat;

//lassi

@Path("/read1ehdokas")
public class servicereadehdokas {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikone");

	// Lukee yhden ehdokkaan id:n perusteella

	@GET
	@Path("/getehdokas/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ehdokkaat read1ehdokas(@PathParam("id") int ehdokas_id, @Context HttpServletRequest request,
			@Context HttpServletResponse response) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e = em.find(ehdokkaat.class, ehdokas_id);
		em.getTransaction().commit();
		
		request.setAttribute("ehdokas", e);
		RequestDispatcher rd = request.getRequestDispatcher("/readehdokas.jsp");
		try {
			rd.forward(request, response);
		}catch (ServletException | IOException e1) {
			e1.printStackTrace();
		}

		return e;
	}

}
