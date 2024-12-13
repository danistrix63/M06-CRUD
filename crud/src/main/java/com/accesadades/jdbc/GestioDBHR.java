package com.accesadades.jdbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class GestioDBHR {

    private static Connection connection;

    // Establecer conexión
    public static Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Properties properties = new Properties();
                InputStream inputStream = GestioDBHR.class.getClassLoader().getResourceAsStream("config.properties");
                properties.load(inputStream);

                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Cerrar conexión
    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
