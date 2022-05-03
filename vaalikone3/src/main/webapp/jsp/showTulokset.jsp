<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="data.Ehdokas"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="tyyli.css">
<style>
body {
	text-align: center;
	font-size: 18px;
}

table {
	width: 200px;
	margin-left: auto;
	margin-right: auto;
	border:none;
}
.button1 {
	padding: 10px 25px;
	margin-left: 14px;
	margin-right: 14px;
}

</style>
<title>Tulokset</title>
</head>
<body>
	<h3>Ehdokas suurimmilla pisteillä on meidän suosittelema</h3>
	<h5>Ehdokas sai aina pisteen kun vastasit saman mielipiteen hänen kanssa.</h5>
	<table>
		<tr>
			<td>Ehdokas</td>
			<td>Pisteet</td>
		</tr>

		<%
		ArrayList<Ehdokas> ehdokasJaPisteet = (ArrayList<Ehdokas>) request.getAttribute("vastaukset");
		ArrayList<Ehdokas> ehdokasLista = (ArrayList<Ehdokas>) request.getAttribute("ehdokkaat");

		for (int i = 0; ehdokasLista != null && i < ehdokasLista.size(); i++) {

			Ehdokas vastaus = ehdokasJaPisteet.get(i);
			Ehdokas e = ehdokasLista.get(i);
			out.println("<tr> <td>" + e.getEtunimi() + "</td>" + "<td>" 
			+ vastaus.getTulos() + "</td>" + "</tr>");

		}
		%>

	</table>
	
	<br>
	<button class='button1' type="submit"
		onclick="location.href='/showKysymys'">Palaa Kyselyyn</button>

	<button class='button1' type="submit"
		onclick="location.href='/sEhdokkaat'">Katso ehdokkaita</button>


</body>
</html>