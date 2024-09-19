/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author mynordma
 */
public class Pago {

    public boolean pagarAnuncio(String nombreUsuario, double precio, LocalDate fechaPago) {
        
        String sql = "UPDATE carteraDigital SET saldo = saldo - ? WHERE usuario = ?";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            
            ps.setDouble(1, precio);
            ps.setString(2, nombreUsuario);
            ps.executeUpdate();
            crearPago(nombreUsuario, precio, fechaPago);
            return true;
            
        } catch (SQLException e) {
            System.out.println("Error al realizar pago");
            return false;
        }
    }
    
    private void crearPago(String nombreUsuario, double precio, LocalDate fechaPago){
        String sql = "INSERT INTO pago (usuario, fecha, monto) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setDate(2, java.sql.Date.valueOf(fechaPago));
            pstmt.setDouble(3, precio);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al registrar el pago: " + e.getMessage());
        }
    }
    
}
