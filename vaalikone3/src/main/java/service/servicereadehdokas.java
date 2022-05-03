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

//lassi

@Path("/read1ehdokas")
public class servicereadehdokas {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("vaalikone");

	// Lukee yhden ehdokkaan id:n perusteella

	@GET
	@Path("/getehdokas/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void read1ehdokas(@PathParam("id") int ehdokas_num, @Context HttpServletRequest request, 
			@Context HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e = em.find(ehdokkaat.class, ehdokas_num);
		em.getTransaction().commit();
		
		request.setAttribute("ehdokas", e);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/readehdokas.jsp");
		rd.forward(request,  response);
	}

	@POST
	@Path("/uploadfile")
	@Consumes({MediaType.MULTIPART_FORM_DATA})
	public Response uploadFile( @FormDataParam("file") InputStream fileInputStream,
            @FormDataParam("file") FormDataContentDisposition fileMetaData,
            @FormDataParam("nimi") String nimi,
            //@FormDataParam("camera") String camera,
            @Context ServletContext sc) 
            		throws Exception
	{
		String UPLOAD_PATH = "C:/Users/OMISTAJA/Vaalikone-2.0/Vaalikone2/src/main/java/pictures";
	    try{
	        int read = 0;
	        byte[] bytes = new byte[1024];
	 
	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + "/"+fileMetaData.getFileName()));
	        while ((read = fileInputStream.read(bytes)) != -1) 
	        {
	            out.write(bytes, 0, read);
	        }
	        out.flush();
	        out.close();
	        
	        
	        
	    } 
	    
	    catch (IOException e){
	        throw new WebApplicationException("Error while uploading file. Please try again !!");
	    }
	    return Response.ok("Ladattu !!").build();
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
	
	
	@GET
	@Path("/readehdokkaat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<ehdokkaat> readehdokkaat() {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		List<ehdokkaat> list=em.createQuery("select f from ehdokkaat f").getResultList();		
		em.getTransaction().commit();
		return list;
	}	
	
	
//	@POST
//	@Path("pictodatabase")
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	public void addkuva(@FormDataParam("file") InputStream fileInputStream,
//			@FormDataParam("file") FormDataContentDisposition fileMetaData,
//			@FormDataParam("nimi") String nimi) {
//		
//		kuva Kuva = new kuva(fileMetaData.getFileName(), nimi);
//		EntityManager em = emf.createEntityManager();
//		em.getTransaction().begin();
//		em.persist(Kuva);
//		em.getTransaction().commit();
//	}
			
			
}
