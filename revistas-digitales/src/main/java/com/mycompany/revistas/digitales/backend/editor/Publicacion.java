/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.editor;

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
public class Publicacion {

    private final String nombreRevista;
    private final InputStream fileContent;
    private int version;

    public Publicacion(String nombre, InputStream fileContent) {
        this.nombreRevista = nombre;
        this.fileContent = fileContent;
    }

    public void guardarPublicacionBD() {
        String query = "INSERT INTO publicacion (revista, pdf) VALUES (?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, nombreRevista);
            stmt.setBlob(2, fileContent);
            
            stmt.executeUpdate();
            System.out.println("Publicación guardada con éxito.");
            
        } catch (SQLException e) {
            System.out.println("Error al guardar la publicación en la base de datos.");
        }
    }
    
    public static ArrayList<Publicacion> recuperarPublicaciones(String nombreRevista) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();
        String query = "SELECT * FROM publicacion WHERE revista = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, nombreRevista);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int version = rs.getInt("version");
                    String revista = rs.getString("revista");
                    InputStream pdf = rs.getBlob("pdf").getBinaryStream();
                    Publicacion publicacion = new Publicacion(revista, pdf);
                    publicacion.setVersion(version);
                    publicaciones.add(publicacion);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error al recuperar las publicaciones de la base de datos.");
        }
        
        return publicaciones;
    }

    public InputStream getPDF() {
        return fileContent;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    
    
}
