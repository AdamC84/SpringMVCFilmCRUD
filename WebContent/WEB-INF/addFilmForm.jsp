<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Please Fill Out The Film Form</title>
</head>
<body>
	<form action="createFilm.do" method="POST">
		<label for="film_title">Film Title:</label><input type="text" name="film_title"><br />
		<label for="film_description">Film Description:</label><input type="text" name="film_description"><br />
		<label for="film_release_year">Film Release Year:</label><input type="text" name="film_release_year"><br />
		<label for="film_language_id">Film Language ID:</label><input type="text" name="film_language_id"><br />
		<label for="film_rental_duration">Film Rental Duration:</label><input type="text" name="film_rental_duration"><br />
		<label for="film_rental_rate">Film Rental Rate:</label><input type="text" name="film_rental_rate"><br />
		<label for="film_length">Film Length:</label><input type="text" name="film_length"><br />
		<label for="film_replacement_cost">Film Replacement Cost:</label><input type="text" name="film_replacement_cost"><br />
		<label for="film_rating">Film Rating:</label><input type="text" name="film_rating"><br />
		<label for="film_special_features">Film Special Features:</label><input type="text" name="film_special_features"><br />
		<!-- <label for="film_category">Film Category:</label><input type="text" name="film_category"><br />
		<lab --><!-- el for="film_language">Film Language:</label><input type="text" name="film_language"><br /> -->
		<!-- <label for="film_actors">Film Actors:</label>
		<input type="text" name="film_actors"><br />
		<input type="text" name="film_actors"><br />
		<input type="text" name="film_actors"><br />
		<input type="text" name="film_actors"><br /> -->
		<input type="submit" value="Submit">
	</form>
</body>

</html>