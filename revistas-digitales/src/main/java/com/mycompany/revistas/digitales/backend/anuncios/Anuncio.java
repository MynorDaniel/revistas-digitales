package com.mycompany.revistas.digitales.backend.anuncios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author mynordma
 */
public class Anuncio {

    
    private final int id;
    private final LocalDate fecha;
    private final String tipo;
    private final String vigencia;
    private final String texto;
    private final byte[] imagen; 
    private final byte[] video;  
    private final String estado;
    private final double precio;

    // Constructor
    public Anuncio(int id, LocalDate fecha, String tipo, String vigencia, String texto, 
        byte[] imagen, byte[] video, String estado, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.vigencia = vigencia;
        this.texto = texto;
        this.imagen = imagen;
        this.video = video;
        this.estado = estado;
        this.precio = precio;
    }
    
    public int getId(String criterio) {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getVigencia() {
        return vigencia;
    }

    public String getTexto() {
        return texto;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public byte[] getVideo() {
        return video;
    }

    public String getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public boolean guardarEnLaBD(String nombreUsuario) {
        String sql = "INSERT INTO anuncio (anunciante, fecha, tipo, vigencia, texto, imagen, video, estado, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Usar try-with-resources para garantizar que los recursos se cierren
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            pstmt.setString(3, tipo);
            pstmt.setString(4, vigencia);
            pstmt.setString(5, texto);

            if (imagen != null) {
                pstmt.setBytes(6, imagen); // Guardar imagen si existe
            } else {
                pstmt.setNull(6, java.sql.Types.BLOB);
            }

            if (video != null) {
                pstmt.setBytes(7, video); // Guardar video si existe
            } else {
                pstmt.setNull(7, java.sql.Types.BLOB);
            }

            pstmt.setString(8, estado);
            pstmt.setDouble(9, precio);

            pstmt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar anuncio");
            return false;
        }
    }
    
    public static int getTotal() {
        String query = "SELECT COUNT(*) AS total FROM anuncio";
        int total = 0;
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return total;
    }
    
    public static void desactivar(String id) {
        String sql = "DELETE FROM anuncio WHERE id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Anuncio con ID " + id + " eliminado con éxito.");
            } else {
                System.out.println("No se encontró el anuncio con ID " + id + ".");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar el anuncio: " + e.getMessage());
        }
    }
}
