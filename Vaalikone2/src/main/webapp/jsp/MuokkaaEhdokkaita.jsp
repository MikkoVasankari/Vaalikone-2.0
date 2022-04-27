<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.List"%>


<%@ page import="data.ehdokkaat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	text-align: center;
}

table {
	margin-left: auto;
	margin-right: auto;
}
</style>
<title>Muokkaa ehdokkaita</title>
</head>
<body>
<form action='../addehdokkaat' method='Post'>
Jotain: <input type="text" name="etunimi" value=''>
Jotain: <input type="text" name="id" value=''>
<input type='submit' name='ok' value='OK'>
</form>

	<div class="container">
		<%
		
		List<ehdokkaat> ehdokkaatList = (List<ehdokkaat>) request.getAttribute("ehdokkaat");
		
		for (int i = 0; ehdokkaatList != null && i < ehdokkaatList.size(); i++) {
			ehdokkaat e = ehdokkaatList.get(i);
			
			int ehdokas_id = e.getEhdokas_id();
			String etunimi = e.getEtunimi();
			int ehdokas_num = e.getEhdokas_num();
			String kotipaikkakunta = e.getKotipaikkakunta();
			int ika = e.getIka();
			String ammatti = e.getAmmatti();
			String kommentti = e.getKommentti();
			
			out.println("<table>" + "<th>" + "<h2>" + e.getEhdokas_num() + ". " + e.getEtunimi() + "</h2>" + "</th>" + "</table>"
					+ "Muokkaa ehdokasta" + "<br>" +  "Poista ehdokas"
					
					
					
					);
			
		
		}
			
		
		%>

</div>



<c:forEach var="ehdokkaat" items="${requestScope.ehdokkaat}">
	<li>${ehdokkaat} <a href="../deleteEhdokas?id=${ehdokas.id}"> Poista</a> <a href="../updateEhdokas?id=${ehdokas.id}">Muokkaa</a>
</c:forEach>


</body>
</html>