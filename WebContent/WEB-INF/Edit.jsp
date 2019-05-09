<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@taglib  uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Please Fill Out The Film Form</title>
</head>
<body>
	<form:form action="update.do" method="POST" modelAttribute="film">
		<form:input path="id" type="hidden" value="${film.id }"/> 
		Film Title:<form:input path="title" value="${film.title }"/><br> 
		Film Description:<form:input path="description" value="${film.description }"/><br>
		Film Release Year:<form:input path="release_year" value="${film.release_year }"/><br> 
		Film Language ID:<form:input path="language_id" value="${film.language_id }"/><br> 
		Film Rental Duration:<form:input path="rental_duration" value="${film.rental_duration }"/><br> 
		Film Rental Rate:<form:input path="rental_rate" value="${film.rental_rate }"/><br>
	    Film Length:<form:input path="length" value="${film.length }"/><br /> 
	    Film Replacement Cost:<form:input path="replacement_cost" value="${film.replacement_cost }"/><br> 
	    Film Rating:<form:select path="rating">
			<form:option value="G">G</form:option>
			<form:option value="PG">PG</form:option>
			<form:option value="PG-13">PG-13</form:option>
			<form:option value="R">R</form:option>
			<form:option value="NC-17">NC-17</form:option>
		</form:select> <br> 
		Film Special Features:<form:select path="special_features">
			<form:option value="Deleted Scenes">Deleted Scenes</form:option>
			<form:option value="Behind the Scenes">Behind The Scenes</form:option>
			<form:option value="Trailers">Trailers</form:option>
			<form:option value="Commentaries">Commentaries</form:option>
		</form:select><br /> <input type="submit" value="Submit"/>
		</form:form>
	
</body>

</html>