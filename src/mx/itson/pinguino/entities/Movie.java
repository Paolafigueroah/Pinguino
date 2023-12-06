/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pinguino.entities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pinguino.persistance.MySQLConnection;
/**
 *
 * @author Paola Figueroa
 */
public class Movie {

     private int id_movie;
    private String name;
    private String category;
    private String sipnosis;

 public static List<Movie> getAllMovies(String filtro) {
    List<Movie> movies = new ArrayList<>();

    try {
        Connection connection = MySQLConnection.get();

        String query = "SELECT id_movie, name, sipnosis, category FROM movie WHERE name LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + filtro + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId_movie(resultSet.getInt(1));
                movie.setName(resultSet.getString(2));
                movie.setSipnosis(resultSet.getString(3));
                movie.setCategory(resultSet.getString(4));
                movies.add(movie);
            }
        }

    } catch (SQLException ex) {
        System.err.println("Ocurrió un error: " + ex.getMessage());
    }

    return movies;
}

     public static void addMovie(String name, String category, String sipnosis) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "INSERT INTO movie (name, category, sipnosis) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                 statement.setString(1, name);
                 statement.setString(2, category);
                 statement.setString(3, sipnosis);
                 statement.executeUpdate();
}
          
        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al agregar una película: " + ex.getMessage());
        }
    }

    public static void deleteMovie(int id) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "DELETE FROM movie WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al eliminar una película: " + ex.getMessage());
        }
    }

     public static void updateMovie(int id, String name, String category) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "UPDATE Movie SET name = ?, category = ? WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, name);
                statement.setString(2, category);
                statement.setInt(3, id);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al actualizar una película: " + ex.getMessage());
        }
    }

    /**
     * @return the id_movie
     */
    public int getId_movie() {
        return id_movie;
    }

    /**
     * @param id_movie the id to set
     */
    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the sipnosis
     */
    public String getSipnosis() {
        return sipnosis;
    }

    /**
     * @param sipnosis the sipnosis to set
     */
    public void setSipnosis(String sipnosis) {
        this.sipnosis = sipnosis;
    }
     
}