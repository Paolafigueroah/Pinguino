/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.pinguino.entities;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mx.itson.pinguino.persistance.MySQLConnection;
import java.sql.PreparedStatement;
/**
 *
 * @author Paola Figueroa
 */
public class Sala {

    private int id_sala;
    private int number;
    private String status;
    
   public static List<Sala> getAllSalas(String filtro) {
        List<Sala> salas = new ArrayList<>();

        try {
            Connection connection = MySQLConnection.get();

            String query = "SELECT id_sala, number, status FROM sala WHERE number LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, "%" + filtro + "%");

                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Sala sala = new Sala();
                sala.setId_sala(resultSet.getInt(1));
                sala.setNumber(resultSet.getInt(2));
                sala.setStatus(resultSet.getString(3));
                salas.add(sala);
                }
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }

        return salas;
    }

    public static void addSala(int number, String status) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "INSERT INTO sala (number, status) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, number);
                statement.setString(2, status);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al agregar una sala: " + ex.getMessage());
        }
    }

    public static void deleteSala(int id_sala) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "DELETE FROM sala WHERE id_sala = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id_sala);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al eliminar una sala: " + ex.getMessage());
        }
    }

    public static void updateSala(int id_sala, int number, String status) {
        try {
            Connection connection = MySQLConnection.get();

            String query = "UPDATE sala SET number = ?, status = ? WHERE id_sala = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, number);
                statement.setString(2, status);
                statement.setInt(3, id_sala);
                statement.executeUpdate();
            }

        } catch (SQLException ex) {
            System.err.println("Ocurrió un error al actualizar una sala: " + ex.getMessage());
        }
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
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
  
}