<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<%@ page import="java.util.ArrayList"%>
<%@ page import="data.ehdokkaat"%>
<%@page import="service.servicereadehdokas"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ehdokkaat</title>
</head>
<body>

<h1>Testi</h1><!-- ei toimiva vielä
 --> 
<%
        ehdokkaat ehdokas = (ehdokkaat) request.getAttribute("ehdokas");

        out.println(ehdokas.getEhdokas_Id() + " " + ehdokas.getEtunimi()
                );

%>




</body>
</html>