package service;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	public ehdokkaat read1ehdokas(@PathParam("id") int ehdokas_id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		ehdokkaat e = em.find(ehdokkaat.class, ehdokas_id);
		em.getTransaction().commit();
		
		return e;
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
	    return Response.ok("Data uploaded successfully !!").build();
	}
			
			
}
