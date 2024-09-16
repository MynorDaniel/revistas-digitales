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
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class TipoAnuncio {

    private final String tipo;
    private double precio = 0;

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }
    
    public TipoAnuncio(String tipo, double precio){
        this.tipo = tipo;
        this.precio = precio;
    }

    public TipoAnuncio(String tipo) {
        this.tipo = tipo;
    }
    
    // Método para obtener el precio del tipo de anuncio
    public double obtenerPrecio() {
        String query = "SELECT precio FROM tipoAnuncio WHERE nombre = ?";
        double precio0 = 0.0;
        
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
            System.out.println("Error sql en tipo anuncio");
        }
        
        return precio0;
    }
    
    public static ArrayList<TipoAnuncio> obtenerTipos() {
        ArrayList<TipoAnuncio> tipos = new ArrayList<>();

        String query = "SELECT nombre, precio FROM tipoAnuncio";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tipo = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");

                TipoAnuncio tipoAnuncio = new TipoAnuncio(tipo, precio);
                tipos.add(tipoAnuncio);
            }

        } catch (SQLException e) {
            System.out.println("Error sql en tipo anuncio 2");
        }

        return tipos;
    }
    
    public static void cambiarPrecioDB(String tipo, double precio) {
        String query = "UPDATE tipoAnuncio SET precio = ? WHERE nombre = ?";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setDouble(1, precio);
            stmt.setString(2, tipo);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro sql en cambiarPrecioDB");
        }
    }


}
