/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.usuarios;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mynordma
 */
public class Usuario {
    protected String nombre;
    protected String rol;

    // Constructor
    public Usuario(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public static String[] getAtributos(String nombreUser) {
        String[] atributos = new String[3];
        atributos[0] = "No disponible"; 
        atributos[1] = "No disponible"; 
        atributos[2] = "No disponible"; 

        String sql = "SELECT hobbies, intereses, descripcion FROM usuario WHERE nombre = ?";

        System.out.println(sql);
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombreUser);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    atributos[0] = rs.getString("hobbies");
                    atributos[1] = rs.getString("intereses");
                    atributos[2] = rs.getString("descripcion");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en get atributos");
        }

        return atributos;
    }
    
    public static void actualizarAtributos(String nombreUsuarioActual, String hobbies, String intereses, String descripcion, String nuevaClave) {
        StringBuilder sql = new StringBuilder("UPDATE usuario SET ");
        boolean actualizar = false;

        // Añadir campos a la consulta solo si no están vacíos
        if (hobbies != null && !hobbies.isEmpty()) {
            sql.append("hobbies = ?, ");
            actualizar = true;
        }
        if (intereses != null && !intereses.isEmpty()) {
            sql.append("intereses = ?, ");
            actualizar = true;
        }
        if (descripcion != null && !descripcion.isEmpty()) {
            sql.append("descripcion = ?, ");
            actualizar = true;
        }
        if (nuevaClave != null && !nuevaClave.isEmpty()) {
            sql.append("clave = ?, ");
            actualizar = true;
        }

        // Eliminar la última coma y espacio sobrante
        if (actualizar) {
            sql.setLength(sql.length() - 2); // Remueve la coma y el espacio final
            sql.append(" WHERE nombre = ?"); // Condición para actualizar el usuario actual
        } else {
            System.out.println("No hay campos para actualizar.");
            return; // Si no hay campos para actualizar, termina el método
        }

        System.out.println("SQL: " + sql.toString());
        // SQL: UPDATE usuario SET hobbies = ?, intereses = ?, descripcion = ?, clave = ? WHERE nombre = ?

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            // Establecer los valores para los campos no vacíos
            if (hobbies != null && !hobbies.isEmpty()) {
                stmt.setString(paramIndex++, hobbies);
            }
            if (intereses != null && !intereses.isEmpty()) {
                stmt.setString(paramIndex++, intereses);
            }
            if (descripcion != null && !descripcion.isEmpty()) {
                stmt.setString(paramIndex++, descripcion);
            }
            if (nuevaClave != null && !nuevaClave.isEmpty()) {
                stmt.setString(paramIndex++, nuevaClave);
            }

            // Establecer el valor de la clave primaria (nombre del usuario actual)
            stmt.setString(paramIndex, nombreUsuarioActual);

            // Ejecutar la actualización
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar los atributos del usuario: " + e.getMessage());
        }
    }



}

