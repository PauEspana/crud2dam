package me.pauep.crud2dam.util;

import me.pauep.crud2dam.exception.DatabaseConnectionException;

import java.sql.*;

public class DatabaseManager {
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/2dam_crud";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "paue2017!";
    
    public static Connection getDatabaseConnection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("There was an issue while trying to connect to the database." + e.getMessage());
        }
    }

    public static Connection getDatabaseConnection() {
        try {
            return DriverManager.getConnection(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("There was an issue while trying to connect to the database." + e.getMessage());
        }
    }
    
    public static Object getStatementId(Statement statement) {
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                return id;
            } else {
                throw new SQLException();
            }
        } catch (SQLException _) {
            System.out.println("There was an issue while obtaining statement generated id");
            return null;
        }
    }
}
