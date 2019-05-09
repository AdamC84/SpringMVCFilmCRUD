package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.skilldistillery.film.DAO.FilmAccessorDAO;
import com.skilldistillery.film.DAO.FilmAccessorDAOImpl;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmQueryController {

	@Autowired
	private FilmAccessorDAO dm;

	@RequestMapping("index.do")
	public String index() {
		return "WEB-INF/homePage.jsp";
	}

	@RequestMapping("home.do")
	public String home() {
		return "WEB-INF/homePage.jsp";
	}

	@RequestMapping(path = "findFilmByID.do", method = RequestMethod.POST)
	public ModelAndView findFilmById(@RequestParam("filmID") int filmID) {
		List<Actor> actorList = new ArrayList<>();
		ModelAndView mv = new ModelAndView();
		Film film = null;
		try {
			film = dm.findFilmById(filmID);
			actorList = film.getActorList();
			mv.addObject("actorList", actorList);
			mv.addObject("newFilm", film);
			mv.setViewName("WEB-INF/results.jsp");
		} catch (NullPointerException e) {
			mv.addObject("newFilm", film);
			mv.setViewName("WEB-INF/results.jsp");
		} catch (SQLException e) {

		}
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("yes") String yes, Film newFilm) {
		ModelAndView mv = new ModelAndView();
		if (yes.equalsIgnoreCase("yes")) {

			try {
				dm.deleteFilm(newFilm);
			} catch (MySQLIntegrityConstraintViolationException e) {
				mv.setViewName("WEB-INF/error.jsp");
			} catch (Exception e) {
				mv.setViewName("WEB-INF/error.jsp");
			}
			mv.setViewName("WEB-INF/homePage.jsp");
		}
		return mv;
	}

	@RequestMapping("addFilm.do")
	public ModelAndView addFilmForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addFilmForm.jsp");
		return mv;
	}

	@RequestMapping("editFilm.do")
	public ModelAndView editFilmForm(String value) throws SQLException {
		ModelAndView mv = new ModelAndView();
		int id = Integer.parseInt(value);
		Film film = dm.findFilmById(id);
		mv.addObject("film", film);
		mv.setViewName("WEB-INF/Edit.jsp");
		return mv;
	}

	@RequestMapping("update.do")
	public ModelAndView updateFilmForm(@ModelAttribute("film") Film film) throws SQLException {
		ModelAndView mv = new ModelAndView();
		try {
			dm.updateFilm(film);
			mv.setViewName("WEB-INF/update.jsp");
		} catch (Exception e) {
			mv.setViewName("WEB-INF/error.jsp");
		}

		return mv;
	}
	@RequestMapping("keyword.do")
	public ModelAndView searchByKeyword(@ModelAttribute("keyword") String keyword) throws SQLException {
		ModelAndView mv = new ModelAndView();
		try {
			List<Film> film = dm.findFilmByKeyword(keyword);
			mv.addObject("results", film);
			mv.setViewName("WEB-INF/keyword.jsp");
		} catch (Exception e) {
			mv.setViewName("WEB-INF/error.jsp");
		}
		
		return mv;
	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilmToDatabase(String film_title, String film_description, int film_release_year,
			int film_language_id, int film_rental_duration, double film_rental_rate, int film_length,
			double film_replacement_cost, String film_rating, String film_special_features) throws SQLException {
		Film newFilm = new Film(film_title, film_description, film_release_year, film_language_id, film_rental_duration,
				film_rental_rate, film_length, film_replacement_cost, film_rating, film_special_features);
		ModelAndView mv = new ModelAndView();
		dm.createFilm(newFilm);
		mv.addObject("newFilm", newFilm);
		mv.setViewName("WEB-INF/results.jsp");
		return mv;

	}

}