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

	<c:if test="${empty results }">
		<h2>Your query returned no results.</h2>
	</c:if>
	<c:forEach var="newFilm" items="${results }">
		<h3>${newFilm.title }</h3>
		<ul>
			<li>Film Id: ${newFilm.id}</li>
			<li>Description: ${newFilm.description}</li>
			<li>Release Year: ${newFilm.release_year}</li>
			<li>Language: ${newFilm.language}</li>
			<li>Rental Duration: ${newFilm.rental_duration}</li>
			<li>Rental Rate: ${newFilm.rental_rate}</li>
			<li>Length: ${newFilm.length}</li>
			<li>Replacement Cost: ${newFilm.replacement_cost}</li>
			<li>Rating: ${newFilm.rating}</li>
			<li>Special Features: ${newFilm.special_features}</li>
			<li>Category: ${newFilm.category}</li>
			<c:forEach var="actor" items="${actorList }">
				<li>Actor: ${actor}</li>
			</c:forEach>
	<form action="deleteFilm.do" method="POST">
		<label for="deleteFilm">Type yes to Delete.</label><input type="text" name="yes"><br />
		<input type="submit" value="Delete">
	</form>
	<form action="editFilm.do?value=${newFilm.id }" method="post">
		<input type="submit" value="Edit">
	</form>

		</ul>
	</c:forEach>
	<h2>Would you like to delete this film?</h2>
	<form action="home.do" method="post">
		<label for="home"></label><input type="submit" value="Home" />
	</form>

</body>
</html>