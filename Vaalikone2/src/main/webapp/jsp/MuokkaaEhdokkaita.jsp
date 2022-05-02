<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>


<%@ page import="data.ehdokkaat"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style>
body {
	text-align: center;
    background-color: powderblue;
}


table {
	margin-left: auto;
	margin-right: auto;
}
.wrapper2 {
    text-align: center;
    
}
.button1 {
	text-decoration: none;
	font-size: 25px;
	color: black;
	padding: 10px 25px;
	border: 3px solid green;
	position: relative;
}

.button1::before, .button1::after {
	content: "";
	position: absolute;
	width: 40px;
	height: 40px;
	border: inherit;
	transition: all 1s;
}

.button1::before {
	top: -15px;
	left: -15px;
	border-width: 3px 0 0 3px;
}

.button1::after {
	bottom: -15px;
	right: -15px;
	border-width: 0 3px 3px 0;
}

.button1:hover::before, .button1:hover::after {
	width: calc(100% + 27px);
	height: calc(100% + 27px);
}
</style>
<title>Muokkaa ehdokkaita</title>
</head>
<link href="../css/style.css" rel="stylesheet">
<link href="../tyyli.css" rel="stylesheet">
<body>
<br></br> <br></br>
	<div class="wrapper2">

		<a href='/jsp/addehdokasform.jsp' class='button1'>LISÄÄ EHDOKAS</a>

	
<br></br>




	<c:forEach var="ehdokkaat" items="${requestScope.ehdokkaat}">
		<h3>Ehdokas</h3>
		
		<h4>${ehdokkaat.etunimi}</h4>
		<a>Ehdokkaan numero ${ehdokkaat.ehdokas_num}</a><br>
		<a href="/deleteehdokas?ehdokas_id=${ehdokkaat.ehdokas_id}" onclick="return confirm('Oletko varma, että haluat poistaa ehdokkaan')">Poista ehdokas </a>
		<br>
		<a href="/readtoupdateehdokkaat?ehdokas_id=${ehdokkaat.ehdokas_id}">Muokkaa ehdokasta</a>
		<br><br><br><br>
	</c:forEach>






</div>

</body>
</html>