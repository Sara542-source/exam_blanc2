package org.example.examblanc_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/examblanc" ;
    private static final String DB_USER = "root" ;
    private static final String DB_PASSWORD = "SaRaslim123?" ;

    Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL , DB_USER , DB_PASSWORD)  ;
    }
    public boolean insertMembre(String nom, String prenom, String email, String phone) {
        String insertSQL = "INSERT INTO membre (nom, prenom, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
