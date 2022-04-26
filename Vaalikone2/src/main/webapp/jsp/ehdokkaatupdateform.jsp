<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ehdokkaatupdate</title>
</head>
<body>
<form action='../updateehdokkaat' method='post'>
<input type='text' name='id' value='${requestScope.ehdokkaat.id }'>
<input type='text' name='nimi' value='${requestScope.ehdokkaat.nimi }'>

<input type='submit' name='ok' value='OK'>
</form>
</body>
</html>