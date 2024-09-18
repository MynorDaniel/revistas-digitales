/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.editor;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mynordma
 */
public class Revista {

    private final String nombre;
    private final String editor;
    private final String bloqueo;
    private final LocalDate fecha;
    private final double costo;
    private final String descripcion;
    private final String categoria;

    public Revista(String nombre, String editor, String bloqueo, LocalDate fecha, double costo, String descripcion, String categoria) {
        this.nombre = nombre;
        this.editor = editor;
        this.bloqueo = bloqueo;
        this.fecha = fecha;
        this.costo = costo;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEditor() {
        return editor;
    }

    public String getBloqueo() {
        return bloqueo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getCosto() {
        return costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCategoria() {
        return categoria;
    }
    
    public static Revista recuperarRevista(String nombre) {
        Revista revista = null;
        String query = "SELECT * FROM revista WHERE nombre = ?";

        try (Connection connection = ConexionBD.obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, nombre);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                
                if (resultSet.next()) {
                    String nombreRevista = resultSet.getString("nombre");
                    String editor = resultSet.getString("editor");
                    String bloqueo = resultSet.getString("bloqueo");
                    Date fecha = resultSet.getDate("fecha");
                    double costo = resultSet.getDouble("costo");
                    String descripcion = resultSet.getString("descripcion");
                    String categoria = resultSet.getString("categoria");

                    revista = new Revista(nombreRevista, editor, bloqueo, fecha.toLocalDate(), costo, descripcion, categoria);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error sql en recuperar revista");
        }
        return revista;
    }

    public boolean guardarEnDB() {
        String sql = "INSERT INTO revista (nombre, editor, bloqueo, fecha, costo, descripcion, categoria) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setString(2, editor);
            stmt.setString(3, bloqueo);
            stmt.setDate(4, java.sql.Date.valueOf(fecha));
            stmt.setDouble(5, costo);
            stmt.setString(6, descripcion);
            stmt.setString(7, categoria);

            int filasAfectadas = stmt.executeUpdate();

            // Si se insertó correctamente, devuelve true
            return filasAfectadas > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    public void guardarTags(List<String> tags) {
        try (Connection con = ConexionBD.obtenerConexion()) {
            String sql = "INSERT INTO revista_tag (revista_nombre, tag_nombre) VALUES (?, ?)";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                for (String tag : tags) {
                    
                    pstmt.setString(1, this.nombre);
                    pstmt.setString(2, tag);
                    
                    pstmt.addBatch();
                }
                
                pstmt.executeBatch();
            } catch (SQLException e) {
                System.out.println("Error en el statement, guardar tags");
            }
        } catch (SQLException e) {
            System.out.println("Error en guardar tags");
        }
    }

    public static List<String> recuperarTags(String nombreRevista) {
        List<String> tags = new ArrayList<>();
        
        try (Connection con = ConexionBD.obtenerConexion()) {
            String sql = "SELECT tag_nombre FROM revista_tag WHERE revista_nombre = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                
                pstmt.setString(1, nombreRevista);
                
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        tags.add(rs.getString("tag_nombre"));
                    }
                } catch (SQLException e) {
                    System.out.println("Error en recuperar tags");
                }
            } catch (SQLException e) {
                System.out.println("Error en recuperar tags");
            }
        } catch (SQLException e) {
            System.out.println("Error en recuperar tags");
        }
        
        return tags;
    }
    
    public static void cambiarBloqueo(String nombreRevista, String bloqueoRevista) {
        String sql = "UPDATE revista SET bloqueo = ? WHERE nombre = ?";

        try (Connection conn = ConexionBD.obtenerConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bloqueoRevista);
            stmt.setString(2, nombreRevista);

            int filasActualizadas = stmt.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("El atributo 'bloqueo' de la revista fue actualizado exitosamente.");
            } else {
                System.out.println("No se encontró una revista con el nombre indicado.");
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar el bloqueo de la revista: " + e.getMessage());
        }
    }
    
    public static int getNumeroVersiones(String nombreRevista) {
        int numeroVersiones = 0;

        String sql = "SELECT COUNT(*) FROM publicacion WHERE revista = ?";

        try (Connection conn = ConexionBD.obtenerConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreRevista);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    numeroVersiones = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en get numero versiones");
        }

        return numeroVersiones;
    }

    public static InputStream getPDF(String nombre, String version) {
        String query = "SELECT pdf FROM publicacion WHERE revista = ? AND version = ?";
        InputStream pdfStream = null;

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Asigna los valores a los parámetros de la consulta
            stmt.setString(1, nombre);
            stmt.setInt(2, Integer.parseInt(version));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    pdfStream = rs.getBinaryStream("pdf");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en get pdf");
        }

        return pdfStream;
    }

}
