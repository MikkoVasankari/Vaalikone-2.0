<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<style>
.wrapper1 {
	text-align: center;
	background-color: powderblue;	
}
body {
	background-color: powderblue;	
}

</style>


<meta charset="UTF-8">
<title>Lisää kysymys</title>
</head>
<link rel="stylesheet" href="tyyli.css">
<body>

	<div class="wrapper1">
		<h2>Lisää kysymys</h2>
		<form action='/lisaaKysymys' method='post'>
			Kysymys numero: <input type='text' name='id' size='2'><br>
			Kysymys: <input type='text' name='kysymys' size='70'><br> <input
				type='submit' name='ok' value='Send'>
		</form>

	</div>

</body>
</html>