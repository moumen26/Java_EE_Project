package com.example.appdest.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfiguration {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/appdist";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Failed to load MySQL JDBC driver!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to connect to the database!");
        }
        return con;
    }
}
