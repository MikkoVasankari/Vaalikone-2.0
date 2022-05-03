<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	text-align: center;
    background-color: powderblue;
}


table {
	margin-left: auto;
	margin-right: auto;
}
.form {
	margin:30px auto;
	max-width: 600px;
	padding: 20px 12px 10px 20px;
	font: 13px "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}
.form li {
	padding: 0;
	display: block;
	list-style: none;
	margin: 10px 0 0 0;
}
.form label{
	margin:0 0 3px 0;
	padding:0px;
	display:block;
	font-weight: bold;
}
textarea {
  resize: none;
}

</style>

<title>LisaaEhdokas</title>
</head>
<body>
<h1>Lisää ehdokas</h1>

<form action='/addehdokas' method='Post'>
<ul class="form">

<li><label>Etunimi:</label> <input type='text' name='etunimi' value='${requestScope.ehdokkaat.etunimi }'> </li>
<li><label>Kotipaikkakunta:</label> <input type='text' name='kotipaikkakunta' value='${requestScope.ehdokkaat.kotipaikkakunta }'></li>
<li><label>Ammatti: </label><input type='text' name='ammatti' value='${requestScope.ehdokkaat.ammatti }'></li>
<li><label>Ikä: </label><input type='text' name='ika' size='5' value='${requestScope.ehdokkaat.ika }'></li>
<li><label>Ehdokas Numero: </label><input type='text' name='ehdokas_num' size='5' value='${requestScope.ehdokkaat.ehdokas_num }'></li>
<li><label>Kommentti: </label><input type='text' name='kommentti' size='60' value='${requestScope.ehdokkaat.kommentti }'></li>
<li><input type='submit' name='ok' value='Lisää ehdokas' onclick="return confirm('Tarkista onko tiedot oikein')"></li>


</ul>
</form>


</body>
</html>