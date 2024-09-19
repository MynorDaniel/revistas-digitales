/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.suscriptor;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author mynordma
 */
public class Suscripcion {
    private final String revistaNombre;
    private final String usuarioNombre;
    private final LocalDate fechaSuscripcion;

    public Suscripcion(String revistaNombre, String usuarioNombre, LocalDate fechaSuscripcion) {
        this.revistaNombre = revistaNombre;
        this.usuarioNombre = usuarioNombre;
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    public void realizarSuscripcion() {
        String sql = "INSERT INTO suscripcion (revista_nombre, usuario_nombre, fecha) VALUES (?, ?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, revistaNombre);
            stmt.setString(2, usuarioNombre);
            stmt.setDate(3, java.sql.Date.valueOf(fechaSuscripcion));

            int rowsInserted = stmt.executeUpdate();
            
            if (rowsInserted > 0) {
                System.out.println("Suscripción realizada con éxito.");
            }

        } catch (SQLException e) {
            System.err.println("Error al realizar la suscripción: " + e.getMessage());
        }
    }
    
    public static boolean usuarioSuscrito(String revistaNombre, String usuarioNombre) {
        String sql = "SELECT 1 FROM suscripcion WHERE revista_nombre = ? AND usuario_nombre = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, revistaNombre);
            stmt.setString(2, usuarioNombre);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar la suscripción: " + e.getMessage());
            return false;
        }
    }
}
