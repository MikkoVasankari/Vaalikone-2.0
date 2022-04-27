<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.ArrayList"%>
<%@ page import="data.ehdokkaat"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ehdokkaat</title>
</head>
<body>

	<h1>Ehdokas tulisi tän alle</h1>
	<!-- ei toimiva vielä
 -->
	

	<h3>Lisää kuva</h3>
<!--  
	<form action="/rest/read1ehdokas/uploadfile" method="post" enctype="multipart/from-data">
		Valitse kuvatiedosto : <input type="file" name="file"  /><br>
		 : <input type="text" name="nimi" /><br>
		<input type="submit" value="Upload" />
		
		
	</form>
-->	
	
	<form action="/rest/read1ehdokas/uploadfile" method="post" enctype="multipart/form-data">
    Valitse kuvatiedosto : <input type="file" name="file" accept=".jpg" /><br>
    Nimi : <input type="text" name="nimi" /><br>
    <input type="submit" value="Upload" />
   </form>
	
	
	
	
	


</body>
</html>