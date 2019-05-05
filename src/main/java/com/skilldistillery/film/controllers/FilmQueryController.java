package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.service.DataMethods;

@Controller
public class FilmQueryController {

	@Autowired
	private DataMethods dm;

	@RequestMapping("index.do")
	public String index() {
		return "WEB-INF/index.jsp";
	}

	@RequestMapping(path = "findFilmByID.do", method = RequestMethod.POST)
	public ModelAndView findFilmById(@RequestParam("filmID") int filmID) {
		List<Actor> actorList = new ArrayList<>();
		ModelAndView mv = new ModelAndView();
		Film film = null;
		try {
			film = dm.findFilmById(filmID);
			dm.deleteFilm(film);
			actorList = film.getActorList();
			mv.addObject("actorList", actorList);
			mv.addObject("newFilm", film);
			mv.setViewName("WEB-INF/index2.jsp");
		} catch (NullPointerException e) {
			mv.addObject("newFilm", film);
			mv.setViewName("WEB-INF/index.jsp");
		} catch (SQLException e) {

		}
//		mv.setViewName("redirect:subMenu.do");
		return mv;
	}

	@RequestMapping(path = "deleteFilm.do", method = RequestMethod.POST)
	public ModelAndView deleteFilm(@RequestParam("yes") String yes, Film newFilm) {
		ModelAndView mv = new ModelAndView();
		if (yes.equalsIgnoreCase("yes")) {
			try {
				dm.deleteFilm(newFilm);
				mv.setViewName("WEB-INF/index2.jsp");
			} catch (SQLException e) {
				System.out.println("No film located by that ID");

			} catch (NullPointerException e) {
			}
		}
		return mv;
	}

//	@RequestMapping(path = "subMenu.do", method = RequestMethod.GET)
//	public ModelAndView created() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/subMenu.jsp");
//		return mv;
//	}

	@RequestMapping("addFilm.do")
	public ModelAndView addFilmForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addFilmForm.jsp");
		return mv;
	}

	@RequestMapping(path = "createFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilmToDatabase(String film_title, String film_description, String film_release_year,
			int film_language_id, int film_rental_duration, double film_rental_rate, int film_length,
			double film_replacement_cost, String film_rating, String film_special_features) {
		Film newFilm = new Film(film_title, film_description, film_release_year, film_language_id,
				film_rental_duration, film_rental_rate, film_length, film_replacement_cost, film_rating,
				film_special_features);
		ModelAndView mv = new ModelAndView();
		dm.createFilm(newFilm);
		mv.addObject("newFilm", newFilm);
		mv.setViewName("WEB-INF/index2.jsp");
		return mv;

	}

}