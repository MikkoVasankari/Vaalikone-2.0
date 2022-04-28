<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.ArrayList"%>
<%@ page import="data.ehdokkaat"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ehdokas</title>
<link rel="stylesheet" type="text/css" href="../tyyli.css">
<style>
table, th, td{
border:1px solid black;
margin-left: auto;
margin-right: auto;
}
* {
	box-sizing: border-box;
	text-align: center;
}

header {
	
}
</style>


</head>
<body>
	
		<h2>Ehdokkaan tiedot</h2>
		<table>
			<tr>
				<c:forEach var="ehdokas" items="${requestScope.ehdokas}">
					<h1>N. ${ehdokas.ehdokas_num}</h1>
				</c:forEach>
				<td>
				<%
					ehdokkaat ehdokas = (ehdokkaat) request.getAttribute("ehdokas");
					out.println("<h4>" + "Nimi: " + ehdokas.getEtunimi() + " <br>" + "Kotipaikkakunta: "
							+ ehdokas.getKotipaikkakunta() + " <br>" + "Ammatti: " + ehdokas.getAmmatti() + " <br>"
							+ "Kommentti: " + ehdokas.getKommentti() + "<br>" + "<br>" + "<br>" + "<img src='/pictures/" + ehdokas.getKuva()
							+ "'>" + "</h4>");
				%>
				</td>
			</tr>
		</table>
	
	<h3>Lisää kuva</h3>

	<form action="/rest/read1ehdokas/uploadfile" method="post"
		enctype="multipart/form-data">
		Valitse kuvatiedosto : <input type="file" name="file" accept=".jpg" /><br>
		NImi : <input type="text" name="text" /> <input type="submit"
			value="Lataa" />
	</form>







</body>
</html>