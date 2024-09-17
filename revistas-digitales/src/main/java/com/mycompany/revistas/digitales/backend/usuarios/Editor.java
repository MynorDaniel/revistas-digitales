/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.usuarios;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import com.mycompany.revistas.digitales.backend.editor.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class Editor extends Usuario{
    
    ArrayList<Revista> revistas = new ArrayList<>();

    // Constructor
    public Editor(String nombre) {
        super(nombre, "EDITOR");
    }
    
    public void recuperarRevistasDB() {
        String sql = "SELECT * FROM revista WHERE editor = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql);
            ){
            
            stmt.setString(1, getNombre());
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    
                    String nombreR = rs.getString("nombre");
                    String editor = rs.getString("editor");
                    String bloqueo = rs.getString("bloqueo");
                    java.sql.Date fecha = rs.getDate("fecha");
                    double costo = rs.getDouble("costo");
                    String descripcion = rs.getString("descripcion");
                    String categoria = rs.getString("categoria");

                    Revista revista = new Revista(nombreR, editor, bloqueo, fecha.toLocalDate(), costo, descripcion, categoria);
                    revistas.add(revista);
                    
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al recuperar las revistas");
        }
    }

    public ArrayList<Revista> getRevistas() {
        return revistas;
    }
    
}
