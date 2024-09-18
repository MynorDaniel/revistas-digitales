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
    private InputStream pdf;
    private int version;

    public Publicacion(String nombre, int version) {
        this.nombreRevista = nombre;
        this.version = version;
    }
    
    public static ArrayList<Publicacion> recuperarPublicaciones(String nombreRevista) {
        ArrayList<Publicacion> publicaciones = new ArrayList<>();

        String sql = "SELECT version FROM publicacion WHERE revista = ?";

        try (Connection conn = ConexionBD.obtenerConexion(); 
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreRevista);

            try (ResultSet rs = stmt.executeQuery()) {
                // Recorrer el ResultSet y crear instancias de Publicacion
                while (rs.next()) {
                    int version = rs.getInt("version");
                    publicaciones.add(new Publicacion(nombreRevista, version));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en recuperar publicaciones");
        }

        return publicaciones;
    }

    public void guardarPublicacionBD() {
        String insertVersionSQL = "INSERT INTO version (numero) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM version WHERE numero = ?)";
        String insertPublicacionSQL = "INSERT INTO publicacion (revista, version, pdf) VALUES (?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement versionStmt = conn.prepareStatement(insertVersionSQL);
             PreparedStatement publicacionStmt = conn.prepareStatement(insertPublicacionSQL)) {

            versionStmt.setInt(1, this.version);
            versionStmt.setInt(2, this.version);
            versionStmt.executeUpdate();

            publicacionStmt.setString(1, this.nombreRevista); 
            publicacionStmt.setInt(2, this.version);       
            publicacionStmt.setBlob(3, this.pdf);     

            int filasInsertadas = publicacionStmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Publicación guardada exitosamente.");
            } else {
                System.out.println("No se pudo guardar la publicación.");
            }

        } catch (SQLException e) {
            System.out.println("Error en guardar publicacion");
        }
    }

    public InputStream getPDF() {
        return pdf;
    }

    public void setFileContent(InputStream pdf) {
        this.pdf = pdf;
    }

    public int getVersion() {
        return version;
    }
    
    
}
