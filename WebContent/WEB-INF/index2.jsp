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
<c:if test="${empty emptyFilm }">
<h2> Your query returned no results.</h2>
</c:if>
<c:if test="${not empty filmbyID }">
<h3>${filmbyID.title }</h3>
<ul>
<li>Film Id: ${filmbyID.id}</li>
<li>Description: ${filmbyID.description}</li>
<li>Release Year: ${filmbyID.release_year}</li>
<li>Language: ${filmbyID.language}</li>
<li>Rental Duration: ${filmbyID.rental_duration}</li>
<li>Rental Rate: ${filmbyID.rental_rate}</li>
<li>Length: ${filmbyID.length}</li>
<li>Replacement Cost: ${filmbyID.replacemnt_cost}</li>
<li>Rating: ${filmbyID.rating}</li>
<li>Special Features: ${filmbyID.special_features}</li>
<li>Category: ${filmbyID.category}</li>
<c:forEach var="actor" items="${actorList }">
<li>Actor: ${actor}</li>
</c:forEach>
</ul>
</c:if>

</html>