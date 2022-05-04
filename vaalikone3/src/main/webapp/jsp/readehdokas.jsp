<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.ArrayList"%>
<%@ page import="data.ehdokkaat"%>
<%@ page import="data.vastaukset"%>
<%@ page import="data.kysymykset"%>
<%@ page import="java.util.List"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ehdokas</title>
<link rel="stylesheet" type="text/css" href="tyyli.css">
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

.radio {
	position: relative;
	display: inline-block;
	padding-bottom: 15px;
	padding-left: 7px;
	padding-right: 7px;
}

.radio input[type="radio"] {
	position: absolute;
	right: 17%;
	margin: 0;
	bottom: 0;
	margin: 0;
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
				out.println("<h3>" + "Nimi: " + ehdokas.getEtunimi() + " <br>" + "Kotipaikkakunta: " + ehdokas.getKotipaikkakunta()
						+ " <br>" + "Ammatti: " + ehdokas.getAmmatti() + " <br>" + "Kommentti: " + ehdokas.getKommentti() + "<br>"
						+ "<br>" + "<img src='/pictures/" + ehdokas.getKuva() + "'>" + "</h3>");
				%>
			</td>
		</tr>
	</table>

	<h3>Lisää kuva</h3>
	<!--  luodaan näkymätön input elementti jonka avulla saadaan ehdokkaan id rest palveluun lähetettyä-->
	<form action="/rest/read1ehdokas/uploadfile" method="post"
		enctype="multipart/form-data">
		<%
		out.println("<input type='hidden' name='id' value='" + ehdokas.getEhdokas_Id() + "'>");
		%>
		Valitse kuvatiedosto : <input type="file" name="file" accept=".jpg" /><br>
		<input type="submit" value="Lataa" /><br>

	</form>

	
	<form method='post' action='/lisaaVastaukset'>
		<%
		out.println("<input type='hidden' name='id' value='" + ehdokas.getEhdokas_Id() + "'>");
		%>

		<h4>Ehdokkaan antamat vastaukset kysymyksiin</h4>

		<%
		@SuppressWarnings("unchecked")
		List<vastaukset> vastauksetlista = (List<vastaukset>) request.getAttribute("ehdokkaanvastaukset");
		@SuppressWarnings("unchecked")
		List<kysymykset> kysymyksetlista = (List<kysymykset>) request.getAttribute("kysymykset");

		if (vastauksetlista.isEmpty() == true) {

			out.println("<form method='post' action='/lisaaVastaukset'>");
			for (int i = 0; kysymyksetlista != null && i < kysymyksetlista.size(); i++) {
				kysymykset kysymys = kysymyksetlista.get(i);

				out.println("<p name=kysymys" + kysymys.getKysymys_id() + " value=" + kysymys.getKysymys_id() + ">"
				+ kysymys.getKysymys_id() + ". " + kysymys.getKysymys() + "<br>");

				out.println("<div class='radio'> <input type='radio' name=" + 'q' + kysymys.getKysymys_id()
				+ "  value='1'> 1 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + kysymys.getKysymys_id()
				+ "  value='2'> 2 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + kysymys.getKysymys_id()
				+ "  value='3'> 3 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + kysymys.getKysymys_id()
				+ "  value='4'> 4 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + kysymys.getKysymys_id()
				+ "  value='5'> 5 </div>");

				out.println("<br>");
				out.println("<br>");
			}
			
			
		} else {
			
			for (int i = 0; vastauksetlista != null && i < vastauksetlista.size(); i++) {
				vastaukset vastaus = vastauksetlista.get(i);
				kysymykset kysymys = kysymyksetlista.get(i);

				out.println("<p name=kysymys" + kysymys.getKysymys_id() + " value=" + kysymys.getKysymys_id() + ">"
				+ kysymys.getKysymys_id() + ". " + kysymys.getKysymys() + "<br>");

				if (vastaus.getVastaus() == 1) {
			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='1' checked = 'checked'> 1 </div>");
				} else {

			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='1'> 1 </div>");
				}

				if (vastaus.getVastaus() == 2) {
			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='2' checked = 'checked'> 2 </div>");
				} else {

			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='2'> 2 </div>");
				}

				if (vastaus.getVastaus() == 3) {
			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='3' checked = 'checked'> 3 </div>");
				} else {

			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='3'> 3 </div>");
				}

				if (vastaus.getVastaus() == 4) {
			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='4' checked = 'checked'> 4 </div>");
				} else {

			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='4'> 4 </div>");
				}

				if (vastaus.getVastaus() == 5) {
			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='5' checked = 'checked'> 5 </div>");
				} else {

			out.println("<div class='radio'> <input type='radio' name=" + 'q' + vastaus.getKysymys_id()
					+ "  value='5'> 5 </div>");
				}

				out.println("<br>");
				out.println("<br>");
			}
		}
		%>

		<button class='button1' type="submit" onclick="">Tallenna
			vastaukset</button>
	</form>
	<br>
	<br>
	<br>
	<a type="submit" class='button1'
		href='/rest/vaalikoneservice/getehdokkaat'>Palaa takaisin
		ehdokkaisiin</a>


</body>
</html>