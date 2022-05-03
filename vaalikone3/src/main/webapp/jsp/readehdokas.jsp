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
table, th, td {
	border: 1px solid black;
	margin-left: auto;
	margin-right: auto;
}

* {
	box-sizing: border-box;
	text-align: center;
}

body {
	background-color: powderblue;
	margin: 20px 150px 20px 150px;
}

table {
	background-color: white;
}
</style>


</head>
<body>

	<h2>Ehdokkaan tiedot</h2>
	<table>
		<tr>
			<c:forEach var="ehdokas" items="${requestScope.ehdokas}">
				<h1>
					<u>N. ${ehdokas.ehdokas_num}</u>
				</h1>
			</c:forEach>
			<td>
				<%
					ehdokkaat ehdokas = (ehdokkaat) request.getAttribute("ehdokas");
					out.println("<h3>" + "Nimi: " + ehdokas.getEtunimi() + " <br>" + "Kotipaikkakunta: "
							+ ehdokas.getKotipaikkakunta() + " <br>" + "Ammatti: " + ehdokas.getAmmatti() + " <br>"
							+ "Kommentti: " + ehdokas.getKommentti() + "<br>" + "<br>"  + "<img src='/pictures/"
							+ ehdokas.getKuva() + "'>" + "</h3>");
				%>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<h3>Lisää kuva</h3>

			<form action="/rest/read1ehdokas/uploadfile" method="post"
				enctype="multipart/form-data">
				Valitse kuvatiedosto : <input type="file" name="file" accept=".jpg" /><br>
				Nimi : <input type="text" name="text" /> <input type="submit"
					value="Lataa" /><br>


<!--  -->

				<!--  <c:forEach var="ehdokas" items="${requestScope.ehdokas}">
					<li> ${ehdokas} <a href='/rest/read1ehdokas/deletepicture/${ehdokas.kuva }'>Delete</a> </li></c:forEach>	
		-->
			</form>
		</tr>
	</table>





</body>
</html>