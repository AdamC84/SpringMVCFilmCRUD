<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Film Query</title>
</head>
<body>
	<h2>Enter the ID of the film you'd like to see.</h2>
	<form action="findFilmByID.do" method="POST">
		<label for="filmID">Film ID:</label><input type="text" name="filmID"><br />
		<input type="submit" value="Submit">
	</form>
	<br>
	<h2>Would you like to add a film to the database?</h2>
	<form action = "addFilm.do" method="POST">
	<label for="addFilm"> Add Film:</label>
	<input type="submit" value="ADD FILM">
	</form>
</body>

</html>