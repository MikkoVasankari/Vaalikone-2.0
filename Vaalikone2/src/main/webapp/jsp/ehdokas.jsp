<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="data.ehdokkaat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<%
		ehdokkaat ehdokas = (ehdokkaat) request.getAttribute("ehdokas");
		
		out.println(
				
				ehdokas.getEhdokas_id() + " " + ehdokas.getEtunimi()
				
				
				);
	%>
	
	
</body>
</html>