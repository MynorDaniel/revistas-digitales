/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.usuarios;

import com.mycompany.revistas.digitales.backend.anuncios.Anuncio;
import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class Anunciante extends Usuario {
    ArrayList<Anuncio> anuncios = new ArrayList<>();

    // Constructor
    public Anunciante(String nombre) {
        super(nombre, "ANUNCIANTE");
    }
    
    public void recuperarAnunciosEnLaBD() {
        String sql = "SELECT * FROM anuncio WHERE anunciante = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, getNombre()); 

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    java.sql.Date fecha = rs.getDate("fecha");
                    String tipo = rs.getString("tipo");
                    String vigencia = rs.getString("vigencia");
                    String texto = rs.getString("texto");

                    // Recuperar imagen como InputStream
                    InputStream imagenStream = rs.getBinaryStream("imagen");

                    // Recuperar video como InputStream
                    InputStream videoStream = rs.getBinaryStream("video");

                    String estado = rs.getString("estado");
                    double precio = rs.getDouble("precio");

                    Anuncio anuncio = new Anuncio(id, fecha.toLocalDate(), tipo, vigencia, texto, imagenStream, videoStream, estado, precio);
                    anuncios.add(anuncio);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al recuperar los anuncios: " + e.getMessage());
        }
    }


    public ArrayList<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(ArrayList<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    

    
    
}
