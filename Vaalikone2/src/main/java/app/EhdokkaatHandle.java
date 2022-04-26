package app;

import java.io.*;
import java.util.*;

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

@WebServlet(urlPatterns = { "/readehdokkaat", "/readtoupdateehdokkaat", "/addehdokkaat" })
public class EhdokkaatHandle extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
	}

	@Override
	  public void doGet(HttpServletRequest request, HttpServletResponse response) 
		      throws IOException, ServletException {
			  String action = request.getServletPath();
			  List<ehdokkaat> list= null;
			  switch (action) {
			  case "/readehdokkaat":
				  list=readehdokkaat(request);break;
			  case "/addehdokkaat":
				  list=addehdokkaat(request);break;
			  case "/readtoupdatehdokkkaat":
				  ehdokkaat e=readtoupdateehdokkaat(request);
				  request.setAttribute("ehdokkaat", e);
				  RequestDispatcher rd=request.getRequestDispatcher("./jsp/MuokkaaEhdokkaita.jsp");
				  rd.forward(request, response);
				  return;
			  }	
			  request.setAttribute("ehdokkaatlist", list);
			  RequestDispatcher rd=request.getRequestDispatcher("./jsp/ehdokkaatupdateform.jsp");
			  rd.forward(request, response);
			  
		  }
		  
		  private ehdokkaat readtoupdateehdokkaat(HttpServletRequest request) {
			  String id=request.getParameter("ehdokas_id");
			  String uri = "http://127.0.0.1:8080/rest/vaalikoneservice/readtoupdateehdokkaat/"+id;
			  Client c=ClientBuilder.newClient();
			  WebTarget wt=c.target(uri);
			  Builder b=wt.request();
			  ehdokkaat ehdokkaat=b.get(ehdokkaat.class);
			  return ehdokkaat;
		  }
		  
			private List<ehdokkaat> readehdokkaat(HttpServletRequest request) {
				String id=request.getParameter("ehdokas_id");
				String uri = "http://127.0.0.1:8080/rest/vaalikoneservice/readehdokkaat";
				Client c=ClientBuilder.newClient();
				WebTarget wt=c.target(uri);
				Builder b=wt.request();
				//Create a GenericType to be able to get List of objects
				//This will be the second parameter of post method
				GenericType<List<ehdokkaat>> genericList = new GenericType<List<ehdokkaat>>() {};
				
				List<ehdokkaat> returnedList=b.get(genericList);
				return returnedList;
			}
		  
			private List<ehdokkaat> addehdokkaat(HttpServletRequest request) {
				//A Fish object to send to our web-service 
				ehdokkaat f=new ehdokkaat(request.getParameter("id"), request.getParameter("nimi"));  //LISÄYS TEHTY ehdokkaat.java - ei toimi muuten
				System.out.println(f);
				String uri = "http://127.0.0.1:8080/rest/vaalikoneservice/addehdokkaat";
				Client c=ClientBuilder.newClient();
				WebTarget wt=c.target(uri);
				Builder b=wt.request();
				//Here we create an Entity of a ehdokkaat object as JSON string format
				Entity<ehdokkaat> e=Entity.entity(f, MediaType.APPLICATION_JSON);
				//Create a GenericType to be able to get List of objects
				//This will be the second parameter of post method
				GenericType<List<ehdokkaat>> genericList = new GenericType<List<ehdokkaat>>() {};
				
				//Posting data (Entity<ArrayList<ehdokkaat>> f) to the given address
				List<ehdokkaat> returnedList=b.post(e, genericList);
				return returnedList;
			}
		  
	  }

