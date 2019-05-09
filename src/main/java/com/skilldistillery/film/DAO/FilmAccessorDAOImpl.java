package com.skilldistillery.film.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.skilldistillery.film.DAO.FilmAccessorDAO;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Service
public class FilmAccessorDAOImpl implements FilmAccessorDAO {



		private static String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
		static {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.err.println("Error loading mySql driver");
				e.printStackTrace();
			}
		}
		public Scanner kb = new Scanner(System.in);

		@Override
		public Film findFilmById(int filmId) throws SQLException {
			String user = "student";
			String pwd = "student";
			String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features,l.name, c.name FROM film f JOIN film_category fc on fc.film_id = f.id JOIN category c on c.id = fc.category_id JOIN language l ON f.language_id = l.id WHERE f.id = ?";
			String sql1 = "SELECT * from film where id = ?";
			if (filmId < 1001) {

				try (Connection conn = DriverManager.getConnection(URL, user, pwd);
						PreparedStatement pst = conn.prepareStatement(sql);) {
					pst.setInt(1, filmId);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						return new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
								rs.getString("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
								rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
								rs.getString("rating"), rs.getString("special_features"), rs.getString("name"),
								rs.getString(13), (findActorsByFilmId(rs.getInt("id"))));
					}
				}
			} else {
				try (Connection conn = DriverManager.getConnection(URL, user, pwd);
						PreparedStatement pst = conn.prepareStatement(sql1);) {
					pst.setInt(1, filmId);
					ResultSet rs = pst.executeQuery();
					if (rs.next()) {
						return new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
								rs.getString("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
								rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
								rs.getString("rating"), rs.getString("special_features"));
					}

				}

			}
			return null;
		}

		@Override
		public Actor findActorById(int actorId) throws SQLException {
			String user = "student";
			String pwd = "student";
			String sql = "SELECT id, first_name, last_name FROM Actor WHERE id = ?";

			try (Connection conn = DriverManager.getConnection(URL, user, pwd);
					PreparedStatement pst = conn.prepareStatement(sql);) {
				pst.setInt(1, actorId);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
//					count++;
					return new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
				}
			}
			return null;
		}

		@Override
		public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
			List<Actor> actorList = new ArrayList<>();
			String user = "student";
			String pwd = "student";
			String sql = "SELECT * FROM actor JOIN film_actor on actor.id = film_actor.actor_id JOIN film on film_actor.film_id = film.id WHERE film.id = ?";

			try (Connection conn = DriverManager.getConnection(URL, user, pwd);
					PreparedStatement pst = conn.prepareStatement(sql);) {
				pst.setInt(1, filmId);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Actor actor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
					actorList.add(actor);
				}
				if (actorList.equals(null)) {
					return null;
				} else {
					return actorList;
				}
			}
		}

		@Override
		public List<Film> findFilmByKeyword(String keyword) throws SQLException {
			List<Film> films = new ArrayList<>();
			String user = "student";
			String pwd = "student";
			String sql = "SELECT f.id, f.title, f.description, f.release_year, f.language_id, f.rental_duration, f.rental_rate, f.length, f.replacement_cost, f.rating, f.special_features,l.name FROM film f JOIN language l ON f.language_id = l.id WHERE f.title like ? OR f.description like ?";

			try (Connection conn = DriverManager.getConnection(URL, user, pwd);
					PreparedStatement pst = conn.prepareStatement(sql);) {
				pst.setString(1, "%" + keyword + "%");
				pst.setString(2, "%" + keyword + "%");
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					Film film = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
							rs.getString("release_year"), rs.getInt("language_id"), rs.getInt("rental_duration"),
							rs.getDouble("rental_rate"), rs.getInt("length"), rs.getDouble("replacement_cost"),
							rs.getString("rating"), rs.getString("special_features"), rs.getString("name"),
							rs.getString("name"), (findActorsByFilmId(rs.getInt("id"))));
					films.add(film);

				}
				return films;
			}

		}

		public Film createFilm(Film film) {
			String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
			String user = "student";
			String pword = "student";
			String sql = "INSERT INTO film ( title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, user, pword);
				conn.setAutoCommit(false);
				PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				int uc = st.executeUpdate();
				ResultSet keys = st.getGeneratedKeys();
				while (keys.next()) {
					if (uc == 1) {
						int filmId = keys.getInt(1);
//						film.setId(filmId);
						System.out.println("New film ID for : " + film.getTitle() + " " + filmId);
						System.out.println(uc + " film records created.");
						conn.commit();
						film.setId(filmId);
						return film;
					} else {
						conn.rollback();
					}
				}
	
				conn.commit();
			} catch (SQLException e) {
				System.err.println("Error during inserts.");
				e.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						System.err.println("Error rolling back.");
						e1.printStackTrace();
					}
				}
			}
			return null;
		}
//		String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
//		String user = "student";
//		String pword = "student";
//		// Employees at store 4 were in a movie, now they're actors!
////		String sql = "INSERT INTO film (title, language_id) VALUE (?, 1) ";
//		Connection conn = null;
//		try {
//			conn = DriverManager.getConnection(url, user, pword);
//			conn.setAutoCommit(false); // Start transaction
//			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//			st.setString(1, film.getTitle());
//			st.setString(2, film.getDescription());
//			st.setString(3, film.getRelease_year());
//			st.setInt(4, film.getLanguage_id());
//			st.setInt(5, film.getRental_duration());
//			st.setDouble(6, film.getRental_rate());
//			st.setInt(7, film.getLength());
//			st.setDouble(8, film.getReplacemnt_cost());
//			st.setString(9, film.getRating());
//			st.setString(10, film.getSpecial_features());
//			int uc = st.executeUpdate();
//			ResultSet keys = st.getGeneratedKeys();
//			while (keys.next()) {
//				if (uc == 1) {
//					int filmId = keys.getInt(1);
//					System.out.println("New film ID for : " + film.getTitle() + " " + filmId);
//					System.out.println(uc + " film records created.");
//					conn.commit();
//					film.setId(filmId);
//				} else {
//					conn.rollback();
//				}
//			}
//
//			conn.commit();
//		} catch (SQLException e) {
//			System.err.println("Error during inserts.");
//			e.printStackTrace();
//			// Need to rollback, which also throws SQLException.
//			if (conn != null) {
//				try {
//					conn.rollback();
//				} catch (SQLException e1) {
//					System.err.println("Error rolling back.");
//					e1.printStackTrace();
//				}
//			}
//		}
//		return null;
//	}

		@Override
		public void deleteFilm(Film film) throws SQLException {
			String url = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
			String user = "student";
			String pword = "student";
			String sql = "DELETE FROM film WHERE id = ?";
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, user, pword);
				conn.setAutoCommit(false);
				PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				st.setInt(1, film.getId());
				int uc = st.executeUpdate();
				ResultSet keys = st.getGeneratedKeys();
				while (keys.next()) {
					if (uc == 1) {
						int filmId = keys.getInt(1);
						System.out.println("Deleted : " + film.getTitle() + " " + filmId);
						System.out.println(uc + " film records deleted.");
						conn.commit();
						film.setId(filmId);
					} else {
						conn.rollback();
					}
				}
				conn.commit();
			} catch (SQLException e) {
				System.err.println("Error during inserts.");
				e.printStackTrace();
				if (conn != null) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						System.err.println("Error rolling back.");
						e1.printStackTrace();
					}
				}
			}
		}

		public void subMenu(Film film) throws SQLException {
			System.out.println("Press 1 to delete the film.");
			System.out.println("Press 2 to go to main menu.");
			int choice = kb.nextInt();
			if (choice == 1) {
				deleteFilm(film);
			}

		}
	}


