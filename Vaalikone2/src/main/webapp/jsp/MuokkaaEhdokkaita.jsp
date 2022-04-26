<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.util.ArrayList"%>
<%@ page import="data.Ehdokas"%>
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
Jotain: <input type="text" name="nimi" value=''>
Jotain: <input type="text" name="id" value=''>
<input type='submit' name='ok' value='OK'>
</form>

<c:forEach var="ehdokkaat" items="${requestScope.ehdokkaatlist}">
	<li>${ehdokkaat} <a href="../deleteEhdokas?id=${ehdokas.id}"> Poista</a> <a href="../updateEhdokas?id=${ehdokas.id}">Muokkaa</a>
</c:forEach>






</body>
</html>