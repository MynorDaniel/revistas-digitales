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

/**
 *
 * @author mynordma
 */
public class MeGusta {
    private final String revistaNombre;
    private final String usuarioNombre;

    public MeGusta(String revistaNombre, String usuarioNombre) {
        this.revistaNombre = revistaNombre;
        this.usuarioNombre = usuarioNombre;
    }
    
    public void guardar() {
        
        String sql = "INSERT INTO megusta (revista_nombre, usuario_nombre) VALUES (?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, revistaNombre);
            stmt.setString(2, usuarioNombre);
            
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(revistaNombre);
            System.out.println(usuarioNombre);
            System.out.println("Error en guardar me gusta");
        }
    }
         
    public static int numeroMegusta(String revistaNombre) {
        
        String sql = "SELECT COUNT(*) FROM megusta WHERE revista_nombre = ?";
        int numeroMegusta = 0;

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, revistaNombre);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    numeroMegusta = rs.getInt(1);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error en numero megusta");
        }
        
        return numeroMegusta;
    }
}
