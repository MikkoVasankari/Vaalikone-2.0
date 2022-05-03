<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="data.kysymys"%>
<%@ page import="javax.servlet.RequestDispatcher"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	text-align: center;
}

table {
	margin-left: auto;
	margin-right: auto;
}
</style>


<title>Vaalikone kysely</title>
</head>
<link rel="stylesheet" href="tyyli.css">
<link href="css/style.css" rel="stylesheet">
<body>
	<h1>Muokkaa kysymyksiä</h1>
	<br>
	<a href="jsp/lisaaKysymys.jsp" class="button1">Lisää kysymys</a>

	<br>
	<br>

	<%
		ArrayList<kysymys> kysymyslista = (ArrayList<kysymys>) request.getAttribute("kysymyslista");

		for (int i = 0; kysymyslista != null && i < kysymyslista.size(); i++) {
			kysymys k = kysymyslista.get(i);
			out.println("<br>");
			out.println(" Kysymys." + k.getId());
			out.println("<br>");
			out.println(k.getKysymys());
			out.println("<br>");
			out.println("<a href='/readtoupdate?id=" + k.getId() + "'>Update question</a>");
			out.println("<br>");
			out.println("<a href='/delete?id=" + k.getId() + "'>Delete question</a>");
			out.println("<br>");
		}
	%>


	<br></br>
	<br></br>



	<button type="submit" onclick="location.href='welcome.jsp'">Palaa
		takaisin</button>
</body>
</html>