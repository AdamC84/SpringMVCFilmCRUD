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
</body>
<c:if test="${empty newFilm }">
<h2> Your query returned no results.</h2>
</c:if>
<c:if test="${not empty newFilm }">
<h3>${newFilm.title }</h3>
<ul>
<li>Film Id: ${newFilm.id}</li>
<li>Description: ${newFilm.description}</li>
<li>Release Year: ${newFilm.release_year}</li>
<li>Language: ${newFilm.language}</li>
<li>Rental Duration: ${newFilm.rental_duration}</li>
<li>Rental Rate: ${newFilm.rental_rate}</li>
<li>Length: ${newFilm.length}</li>
<li>Replacement Cost: ${newFilm.replacemnt_cost}</li>
<li>Rating: ${newFilm.rating}</li>
<li>Special Features: ${newFilm.special_features}</li>
<li>Category: ${newFilm.category}</li>
<c:forEach var="actor" items="${actorList }">
<li>Actor: ${actor}</li>
</c:forEach>
</ul>
</c:if>
<h2>Would you like to delete this film?</h2>
<form action="deleteFilm.do" method="POST">
		<label for="deleteFilm">Yes</label><input type="submit" value="${newFilm }"value="Yes"><br />
		<input type="submit" value="Submit">
	</form>


</html>