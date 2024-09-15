/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mynordma
 */
public class CarteraDigital {
    
    // Método para obtener el saldo del usuario
    public String obtenerSaldo(String nombre) {
        
        String saldo = "0"; // Valor por defecto si el usuario no está en la tabla

        String sqlSelect = "SELECT saldo FROM carteraDigital WHERE usuario = ?";
        String sqlInsert = "INSERT INTO carteraDigital (usuario, saldo) VALUES (?, 0)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement pstmtSelect = con.prepareStatement(sqlSelect);
             PreparedStatement pstmtInsert = con.prepareStatement(sqlInsert)) {

            // Verificar si el usuario ya existe en la tabla
            pstmtSelect.setString(1, nombre);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                // Si el usuario existe, obtener el saldo
                saldo = rs.getString("saldo");
            } else {
                // Si el usuario no existe, insertarlo con saldo de cero
                pstmtInsert.setString(1, nombre);
                pstmtInsert.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error sql");
        }

        return saldo;
    }
    
    
    public void recargar(String nombre, double monto) {
        String sqlUpdate = "UPDATE carteraDigital SET saldo = saldo + ? WHERE usuario = ?";

        try {
            Connection connection = ConexionBD.obtenerConexion();
            PreparedStatement pstmtUpdate = connection.prepareStatement(sqlUpdate);
            pstmtUpdate.setDouble(1, monto);
            pstmtUpdate.setString(2, nombre);
            pstmtUpdate.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al recargar");
        }
    }
}
