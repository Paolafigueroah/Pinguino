/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pinguino.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mx.itson.pinguino.persistance.MySQLConnection;
/**
/**
 *
 * @author Paola Figueroa
 */
public class Funcion {

    private int id;
    private int id_sala;
    private int id_movie;
    private Time schedule;
    private Date date;
    
   public static List<Funcion> getAllFunciones(String filtro) {
        List<Funcion> funciones = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.get();

            String query = "SELECT id, id_sala, id_movie, schedule, date FROM Funcion WHERE id LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + filtro + "%");

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Funcion funcion = new Funcion();
                    funcion.setId(resultSet.getInt("id"));
                    funcion.setId_sala(resultSet.getInt("id_sala"));
                    funcion.setId_movie(resultSet.getInt("id_movie"));
                    funcion.setSchedule(resultSet.getTime("schedule"));
                    funcion.setDate(resultSet.getDate("date"));
                    funciones.add(funcion);
                }
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }

        return funciones;
    }

    public static void addFuncion(int idSala, int idMovie, Time schedule, Date date) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "INSERT INTO Funcion (id_sala, id_movie, schedule, date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idSala);
                statement.setInt(2, idMovie);
                statement.setTime(3, schedule);
                statement.setDate(4, (java.sql.Date) date);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al agregar una función: " + ex.getMessage());
        }
    }

    public static void deleteFuncion(int idFuncion) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "DELETE FROM Funcion WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idFuncion);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al eliminar una función: " + ex.getMessage());
        }
    }

    public static void updateFuncion(int idFuncion, int newIdSala, int newIdMovie, Time newSchedule, Date newDate) {
    try {
        Connection connection = MySQLConnection.get();

        String query = "UPDATE Funcion SET id_sala = ?, id_movie = ?, schedule = ?, date = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, newIdSala);
            statement.setInt(2, newIdMovie);
            statement.setTime(3, newSchedule);
            statement.setTimestamp(4, new java.sql.Timestamp(newDate.getTime()));
            statement.setInt(5, idFuncion);
            statement.executeUpdate();
        }

    } catch (SQLException ex) {
        System.err.println("Ocurrió un error al actualizar una función: " + ex.getMessage());
    }
}
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the id_sala
     */
    public int getId_sala() {
        return id_sala;
    }

    /**
     * @param id_sala the id_sala to set
     */
    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    /**
     * @return the id_movie
     */
    public int getId_movie() {
        return id_movie;
    }

    /**
     * @param id_movie the id_movie to set
     */
    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    /**
     * @return the schedule
     */
    public Time getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Time schedule) {
        this.schedule = schedule;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

}