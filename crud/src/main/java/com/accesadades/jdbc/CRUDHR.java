package com.accesadades.jdbc;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;

public class CRUDHR {

    // Crear base de datos, tablas y datos iniciales
    public static void inicializarBaseDatos(InputStream input) {
        try (Connection conn = GestioDBHR.connect();
             BufferedReader br = new BufferedReader(new InputStreamReader(input));
             Statement stmt = conn.createStatement()) {

            StringBuilder sqlStatement = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("--") || line.startsWith("/*")) {
                    continue;
                }

                sqlStatement.append(line);

                if (line.endsWith(";")) {
                    String sql = sqlStatement.toString().replace(";", "").trim();
                    stmt.execute(sql);
                    sqlStatement.setLength(0);
                }
            }

            System.out.println("Base de datos y tablas creadas con éxito.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Insertar registros en la tabla
    public static void insertarRegistro(String descripcion, int idDepartamento) {
        String sql = "INSERT INTO tascas (descripcion, id_departamento) VALUES (?, ?);";
        try (Connection conn = GestioDBHR.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, descripcion);
            pstmt.setInt(2, idDepartamento);
            pstmt.executeUpdate();
            System.out.println("Registro insertado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Mostrar registros con paginación
    public static void mostrarRegistros(int limit, int offset) {
        String sql = "SELECT * FROM tascas LIMIT ? OFFSET ?;";
        try (Connection conn = GestioDBHR.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, limit);
            pstmt.setInt(2, offset);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d, Descripción: %s, ID Departamento: %d\n",
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getInt("id_departamento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Crear XML con los registros
    public static void crearXML() {
        String sql = "SELECT * FROM tascas;";
        try (Connection conn = GestioDBHR.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
             FileWriter writer = new FileWriter("tascas.xml")) {

            writer.write("<tascas>\n");
            while (rs.next()) {
                writer.write(String.format("  <tasca>\n    <id>%d</id>\n    <descripcion>%s</descripcion>\n    <idDepartamento>%d</idDepartamento>\n  </tasca>\n",
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getInt("id_departamento")));
            }
            writer.write("</tascas>");
            System.out.println("XML creado correctamente.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // Consultar por ID
    public static void consultarPorID(int id) {
        String sql = "SELECT * FROM tascas WHERE id = ?;";
        try (Connection conn = GestioDBHR.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.printf("ID: %d, Descripción: %s, ID Departamento: %d\n",
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getInt("id_departamento"));
            } else {
                System.out.println("No se encontró ningún registro con el ID especificado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Consultar por campo con LIKE
    public static void consultarPorDescripcion(String descripcion) {
        String sql = "SELECT * FROM tascas WHERE descripcion LIKE ?;";
        try (Connection conn = GestioDBHR.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + descripcion + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.printf("ID: %d, Descripción: %s, ID Departamento: %d\n",
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getInt("id_departamento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Modificar un campo
    public static void modificarDescripcion(int id, String nuevaDescripcion) {
        String sql = "UPDATE tascas SET descripcion = ? WHERE id = ?;";
        try (Connection conn = GestioDBHR.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevaDescripcion);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Registro actualizado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar registro
    public static void eliminarRegistro(int id) {
        String sql = "DELETE FROM tascas WHERE id = ?;";
        try (Connection conn = GestioDBHR.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Registro eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
