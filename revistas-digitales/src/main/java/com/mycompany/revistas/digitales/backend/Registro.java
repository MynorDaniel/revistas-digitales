/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author mynordma
 */
public class Registro {
    
    private String nombre;
    private String clave;
    private String rol;
    private int tipoError = 2; // 0: Nombre duplicado, 1: Campos vacios, 2: Sin error, 3: Error mysql
    
    public int registrar(HttpServletRequest req){
        
        leerParametros(req);
        verificarParametros();
        registrarEnLaBD();
        
        return tipoError;
    }
    
    private void leerParametros(HttpServletRequest req){
        nombre = req.getParameter("nombreRegistro");
        
        Encriptacion encriptacion = new Encriptacion(req.getParameter("claveRegistro"));
        clave = encriptacion.encriptar();
        
        rol = req.getParameter("rol");
    }
    
    private void verificarParametros(){
        if(nombre.length() <= 0 || clave.length() <= 0){
            tipoError = 1;
        }
    }
    
    private void registrarEnLaBD(){
        
        String sql = "INSERT INTO usuario (nombre, clave, rol) VALUES (?, ?, ?)";
        
        if(tipoError == 1){
            return;
        }
        try (Connection conexion = ConexionBD.obtenerConexion();
                PreparedStatement stmt = conexion.prepareStatement(sql);) {
            
            // Establecemos los valores para la consulta
            stmt.setString(1, nombre);
            stmt.setString(2, clave);  // Clave ya encriptada
            stmt.setString(3, rol);
            
            // Ejecutamos la query
            stmt.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            tipoError = 0;
        } catch (SQLException e) {
            tipoError = 3;
        }
    }
}