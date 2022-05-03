<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList"%>
<%@ page import="data.Ehdokas"%>
<%@ page import="data.ehdokasVastaukset"%>
<%@ page import="data.Tulos"%>
<%@ page import="data.kysymys"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ehdokkaat</title>

<link rel="stylesheet" type="text/css" href="tyyli.css">
<style>
* {
	box-sizing: border-box;
}

header {
	text-align: center;
}

article {
	float: left;
	padding: 20px;
	width: 50%;
}

.container {
	width: 50%;
	border: none;
	float: left;
}

.container2 {
	width: 50%;
	border: none;
	float: right;
}

footer {
	padding: 10px;
	text-align: center;
}

@media ( max-width : 600px) {
	nav, article {
		width: 100%;
		height: auto;
	}
}

table {
	width: 100%;
	border: red;
	background-color: powderblue;
}
</style>


</head>
<body>

	<br>
	<a href="/index.html" class="button1">Takaisin</a>
	<header>
		<h2>2022 Meemi vaali ehdokkaat</h2>
	</header>
	<h4>
		Vaaliehdokkaat ovat tarkkaan ammattilaisten avulla valittuja
		yksilöitä,<br> joilla kaikkilla on ainutlaatuinen persoona <br>
		Tällä sivulla voit katsoa ehdokkaiden tietoja.


	</h4>

	<h3>Kysymykset</h3>

	<%
	ArrayList<kysymys> KysymysList = (ArrayList<kysymys>) request.getAttribute("kysymykset");

	for (int i = 0; KysymysList != null && i < KysymysList.size(); i++) {
		kysymys k = KysymysList.get(i);

		int id = k.getId();
		String kysymys = k.getKysymys();

		out.println("<h7>" + k.getId() + ". " + k.getKysymys() + "</h7>");
	}
	%>
	<h4>Ehdokkaiden vastaukset kysymyksiin</h4>
	<h4>(1 = Täysin eri mieltä, 5 = Täysin samaa mieltä)</h4>

	<div class="container">

		<%
		ArrayList<Ehdokas> EhdokkaatList = (ArrayList<Ehdokas>) request.getAttribute("ehdokkaat");

		for (int i = 0; EhdokkaatList != null && i < EhdokkaatList.size(); i++) {
			Ehdokas f = EhdokkaatList.get(i);

			int id = f.getId();
			String etunimi = f.getEtunimi();
			String paikkakunta = f.getKotipaikkakunta();
			int ika = f.getIka();
			String ammatti = f.getAmmatti();
			String kommentti = f.getKommentti();

			out.println("<br>" + "<br>" + "<br>" +

			"<table>" + "<th>" + "<h2>" + f.getEhdokas_num() + ". " + f.getEtunimi() + "</h2>" + "</th>" + "</table>" +

			"<table>" + "<tr>" + "<th>" + "Kotipaikkakunta: " + "</th>" + "<td>" + f.getKotipaikkakunta() + "</td>"
			+ "</tr>" +

			"<tr>" + "<th>" + "Ikä: " + "</th>" + "<td>" + f.getIka() + "</td>" + "</tr>" +

			"<tr>" + "<th>" + "Ammatti: " + "</th>" + "<td>" + f.getAmmatti() + "</td>" + "</tr>" +

			"<tr>" + "<th>" + "Kommentti: " + "</th>" + "<td>" + f.getKommentti() + "</td>" + "</tr>" +

			"</table>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>" + "<br>"
			+ "<br>" + "<br>" + "<br>" + "<br>"

			);

			//out.println(f.getId()+f.getEtunimi()+f.getKotipaikkakunta()+f.getIka()+f.getAmmatti()+f.getKommentti());

		}
		%>
	</div>

	<div class="container2">
		<!-- siirtää tekstin oikealle -->


		<%
		ArrayList<ehdokasVastaukset> ehdokasvastauslista = (ArrayList<ehdokasVastaukset>) request
				.getAttribute("ehdokasvastaukset");
		for (int i = 0; ehdokasvastauslista != null && i < ehdokasvastauslista.size(); i++) {
			ehdokasVastaukset t = ehdokasvastauslista.get(i);

			int id = t.getEhdokas_id();
			int kysymys = t.getKysymys_id();
			String vastaus = t.getVastaus();
			int ehdokas_num = t.getEhdokas_num();

			out.println(

			"<h5>" + " " + "Ehdokas numero. " + t.getEhdokas_num() + " " +

					"Kysymyksen numero: " + t.getKysymys_id() + " Ehdokkaan vastaus on: " + t.getVastaus() + "</h5>"

			);

		}
		%>

	</div>
	<!-- tässä on div float-containerin loppu -->
</body>
</html>