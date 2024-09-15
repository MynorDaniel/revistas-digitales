/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.anuncios;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mynordma
 */
public class TipoAnuncio {
    private final String tipo;

    public TipoAnuncio(String tipo) {
        this.tipo = tipo;
    }
    
    // Método para obtener el precio del tipo de anuncio
    public double obtenerPrecio() {
        String query = "SELECT precio FROM tipoAnuncio WHERE nombre = ?";
        double precio = 0.0;
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            // Configurar el parámetro de la consulta
            stmt.setString(1, tipo);
            
            try (ResultSet rs = stmt.executeQuery()) {
                // Verificar si se obtuvo un resultado
                if (rs.next()) {
                    precio = rs.getDouble("precio");
                } else {
                    System.out.println("Tipo de anuncio no encontrado.");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return precio;
    }
}
