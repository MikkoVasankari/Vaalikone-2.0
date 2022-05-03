<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="data.kysymys"%>
<%@ page import="javax.servlet.RequestDispatcher"%>


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

.radio {
	position: relative;
	display: inline-block;
	padding-bottom: 15px;
	padding-left: 7px;
	padding-right: 7px;
}

.radio input[type="radio"] {
	position: absolute;
	right: 17%; margin : 0;
	bottom: 0;
	margin: 0;
}

.b {
	background-color: powderblue;;
}
.button1 {
	padding: 10px 25px;
}
</style>

<title>Vaalikone kysely</title>
</head>
<body>
	<div class='b'>
		<h1>Vaalikone kysely</h1>

		<h3>
			Tervetuloa vaalikoneen kysymys osion tässä osiossa sinun olisi
			tarkoitus vasta kysymyksiin vaihtoehdoilla 1 - 5. <br>(1 =
			Täysin eri mieltä, 5 = Täysin samaa mieltä)
		</h3>
		
		
		<form method='post' action='/showTulokset'>

			<%
			ArrayList<kysymys> kysymyslista = (ArrayList<kysymys>) request.getAttribute("kysymyslista");

			for (int i = 0; kysymyslista != null && i < kysymyslista.size(); i++) {
				kysymys k = kysymyslista.get(i);
				out.println(" Kysymys." + k.getId());
				out.println("<br>");
				out.println(k.getKysymys());
				out.println("<br>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + k.getId() + "  value='1'> 1 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + k.getId() + "  value='2'> 2 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + k.getId() + "  value='3'> 3 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + k.getId() + "  value='4'> 4 </div>");
				out.println("<div class='radio'> <input type='radio' name=" + 'q' + k.getId() + "  value='5'> 5 </div>");
				out.println("<br>");
				out.println("<br>");
			}
			%>


			<br>
			<br>
			<button class='button1' type="submit" name="kysymyksetSubmit"
				id="kysymyksetSubmit" onclick="location.href='/showTulokset'">Siirry
				tuloksiin</button>

		</form>

		<br> <br>
		<button class='button1' type="submit"
			onclick="location.href='index.html'">Palaa takaisin</button>
		<br> <br>
	</div>
</body>
</html>