<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="data.ehdokkaat"%>
<%@ page import="data.vastaukset"%>
<%@ page import="data.kysymykset"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8 />
<title>Ehdokkaan sivu</title>
<style>
body {
	text-align: center;
	font-size: 18px;
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

	<form method='post' action="/handleVastaukset">

		<%
		ehdokkaat ehdokas = (ehdokkaat) request.getAttribute("ehdokas");
		out.println("<p id=ehdokas value=" + ehdokas.getEhdokas_num() + ">" + ehdokas.getEhdokas_num() + ". "
				+ ehdokas.getEtunimi());
		
		out.println("<input type='hidden' name='id' value='"+ ehdokas.getEhdokas_Id() +"'>");
		%>

		<h4>Ehdokkaan antamat vastaukset kysymyksiin</h4>
		
		<%
		@SuppressWarnings("unchecked")
		List<vastaukset> vastauksetlista = (List<vastaukset>) request.getAttribute("ehdokkaanvastaukset");
		@SuppressWarnings("unchecked")
		List<kysymykset> kysymyksetlista = (List<kysymykset>) request.getAttribute("kysymykset");

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
		%>

		<button class='button1' type="submit" onclick="">Tallenna
			vastaukset</button>
	</form>
</body>
</html>