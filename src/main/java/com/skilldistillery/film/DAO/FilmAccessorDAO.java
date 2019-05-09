package com.skilldistillery.film.DAO;

import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

public interface FilmAccessorDAO {
	public Film findFilmById(int filmId) throws SQLException;

	public Actor findActorById(int actorId) throws SQLException;

	public List<Actor> findActorsByFilmId(int filmId) throws SQLException;

	public List<Film> findFilmByKeyword(String keyword) throws SQLException;

	public Film createFilm(Film film) throws SQLException;

	public void deleteFilm(Film film) throws SQLException;

	public void updateFilm(Film film) throws SQLException;
}
