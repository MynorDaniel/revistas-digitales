/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mynordma
 */
public class Login {
    private String nombre;
    private String clave;
    private String rol;
    
    public boolean loguear(HttpServletRequest req){
        
        leerParametros(req);
        return existeUsuario();
    }
    
    private void leerParametros(HttpServletRequest req){
        nombre = req.getParameter("nombreLogin");
        
        Encriptacion encriptacion = new Encriptacion(req.getParameter("claveLogin"));
        clave = encriptacion.encriptar();
        
    }
    
    private boolean existeUsuario() {
        // Modificar la consulta SQL para también seleccionar el rol
        String sql = "SELECT rol FROM usuario WHERE nombre = ? AND clave = ?";

        try (Connection conexion = ConexionBD.obtenerConexion();
            PreparedStatement stmt = conexion.prepareStatement(sql);) {
            

            // Establecer los valores para la consulta
            stmt.setString(1, nombre);
            stmt.setString(2, clave);

            // Ejecutar la consulta y obtener el resultado
            try (ResultSet rs = stmt.executeQuery()) {
                // Si hay un resultado, el usuario existe
                if (rs.next()) {
                    // Obtener el rol del usuario desde el resultado de la consulta
                    rol = rs.getString("rol");
                    return true;
                } else {
                    return false;  // No hay coincidencias
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar la base de datos.");
            return false;
        }
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getRol() {
        return rol;
    }

}
