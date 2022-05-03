<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kysymysten editointi</title>
<style>
.wrapper1 {
	text-align: center;
	background-color: powderblue;
}

body {
	background-color: powderblue;
}
</style>



</head>
<link rel="stylesheet" href="tyyli.css">
<body>

	<div class="wrapper1">
		<h2>Muokkaa kysymyksiä</h2>
		<form action='update' method='post'>
			Kysymys id: <input type='text' name='id' size='2'
				value='${requestScope.kysymys.id}' readonly><br>
			Kysymys: <input type='text' name='kysymys' size='70'
				value='${requestScope.kysymys.kysymys}'><br> <input
				type='submit' name='ok' value='Send'>
		</form>
	</div>

</body>
</html>