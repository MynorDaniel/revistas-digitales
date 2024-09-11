/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mynordma
 */
public class ConexionBD {
    // Variable estática para almacenar la única instancia de la conexión
    private static Connection conexion;

    // Credenciales de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/REVISTAS_DIGITALES";
    private static final String USUARIO = "root";
    private static final String CLAVE = "mynordma";

    // Constructor privado para evitar instanciación desde otras clases
    private ConexionBD() {
    }

    // Método público estático para obtener la única instancia de la conexión
    public static Connection obtenerConexion() {
        if (conexion == null) {
            try {
                // Cargar el driver JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Crear la conexión
                conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
                System.out.println("Conexión establecida correctamente.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return conexion;
    }

    // Método para cerrar la conexión si es necesario
    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null;  // Resetear la instancia de conexión
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
