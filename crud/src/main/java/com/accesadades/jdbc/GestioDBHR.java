package com.accesadades.jdbc;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class GestioDBHR {

    private static Connection connection;

    // Establecer conexión
    public static Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try (InputStream inputStream = GestioDBHR.class.getClassLoader().getResourceAsStream("db_scripts/config.properties")) {
                if (inputStream == null) {
                    throw new FileNotFoundException("Archivo config.properties no encontrado.");
                }

                Properties properties = new Properties();
                properties.load(inputStream);

                String url = properties.getProperty("db.url", "jdbc:mariadb://localhost:3306/defaultDB");
                String username = properties.getProperty("db.username", "root");
                String password = properties.getProperty("db.password", "");

                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Conexión establecida con éxito.");

            } catch (IOException e) {
                System.err.println("Error al leer el archivo de configuración: " + e.getMessage());
                throw new SQLException("No se pudo cargar la configuración de la base de datos.", e);
            } catch (SQLException e) {
                System.err.println("Error al conectar a la base de datos: " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }

    // Cerrar conexión
    public static void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Conexión cerrada con éxito.");
        }
    }
}
