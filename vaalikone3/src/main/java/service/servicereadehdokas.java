package service;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


import data.ehdokkaat;
import data.kysymykset;
import data.vastaukset;

//lassi

@Path("/read1ehdokas")
public class servicereadehdokas {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikone");

	// Lukee yhden ehdokkaan id:n perusteella

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
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/readehdokas.jsp");

		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@POST
	@Path("/uploadfile")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public void uploadFile( @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData,
            @FormDataParam("nimi") String nimi,
            @FormDataParam("id") int id,
            @Context ServletContext sc, @Context HttpServletRequest request, 
			@Context HttpServletResponse response) 
            		throws Exception

            	
	{
		

		EntityManager em1 = emf.createEntityManager(); // hakee id:n perusteella ehdokkaan
        em1.getTransaction().begin();
        ehdokkaat e = em1.find(ehdokkaat.class,id);
        em1.getTransaction().commit();
		
		ehdokkaat e1 = new ehdokkaat(e.getEhdokas_id(), e.getEtunimi(), e.getEhdokas_num(),
		e.getKotipaikkakunta(), e.getIka(), e.getAmmatti(), e.getKommentti(), fileMetaData.getFileName());
        
        
        
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(e1);
		em.getTransaction().commit();
		
		String UPLOAD_PATH=sc.getRealPath("/pictures");// pitäisi keksiä parempi keino
	    try{
	        int read = 0;
	        byte[] bytes = new byte[8192];
	 
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/"+fileMetaData.getFileName()));
	        while ((read = fileInputStream.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	        
	        
	        
	    } 
	    
	    catch (IOException f){
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	  
	    EntityManager em2 = emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e2 = em2.find(ehdokkaat.class, id);
		
		em.getTransaction().commit();
		EntityManager em4 = emf.createEntityManager();
		em2.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<vastaukset> list1 = em4.createQuery("select x from vastaukset x where x.ehdokas_id=?1")
				.setParameter(1, id).getResultList();

		// Haetaan kysymykset
		EntityManager em3 = emf.createEntityManager();
		em3.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<kysymykset> list2 = em3.createQuery("select x from kysymykset x").getResultList();
		em3.getTransaction().commit();
		
		request.setAttribute("ehdokas", e2);
		request.setAttribute("ehdokkaanvastaukset", list1);
		request.setAttribute("kysymykset", list2);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/readehdokas.jsp");
		rd.forward(request,  response);
	}
	
	
//	@PUT
//	@Path("/deletepicture/{kuva}")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public void deletePicture(@PathParam("kuva") String kuva,
//			@Context HttpServletRequest request,
//			@Context HttpServletResponse response
//			) {
//		EntityManager em=emf.createEntityManager();
//		em.getTransaction().begin();
//		ehdokkaat f=em.find(ehdokkaat.class, kuva);
//		if (f!=null) {
//			em.remove(f);//The actual delete line
//		}
//		em.getTransaction().commit();
//		
//		
//	}
	
	
//	@GET
//	@Path("/readehdokkaat")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
//	public List<ehdokkaat> readehdokkaat() {
//		EntityManager em=emf.createEntityManager();
//		em.getTransaction().begin();
//		List<ehdokkaat> list=em.createQuery("select f from ehdokkaat f").getResultList();		
//		em.getTransaction().commit();
//		return list;
//	}	
	
	
	@POST
	@Path("pictodatabase")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public void addkuva(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData,
			@FormDataParam("nimi") String nimi, @FormDataParam("id") int id ) {
		
	

		EntityManager em1 = emf.createEntityManager(); // hakee id:n perusteella ehdokkaan
        em1.getTransaction().begin();
        ehdokkaat e = em1.find(ehdokkaat.class,id);
        em1.getTransaction().commit();
		
		ehdokkaat e1 = new ehdokkaat(e.getEhdokas_id(), e.getEtunimi(), e.getEhdokas_num(),
		e.getKotipaikkakunta(), e.getIka(), e.getAmmatti(), e.getKommentti(), fileMetaData.getFileName());
        
        
        
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(e1);
		em.getTransaction().commit();
		
		
	}
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
