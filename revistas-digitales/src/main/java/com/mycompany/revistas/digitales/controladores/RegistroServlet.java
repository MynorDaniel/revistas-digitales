/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.Registro;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Registro registro = new Registro();
        int resultadoRegistro = registro.registrar(req);
        
        switch (resultadoRegistro) {
            case 0:
                // Nombre duplicado
                req.setAttribute("mensajeAtributo", "El nombre ya está registrado. Por favor, elige otro.");
                break;
            case 1:
                // Campos vacíos
                req.setAttribute("mensajeAtributo", "Por favor, completa todos los campos.");
                break;
            case 3:
                // Error en la conexión con la base de datos
                req.setAttribute("mensajeAtributo", "Error en la base de datos. Inténtalo de nuevo más tarde.");
                break;
            case 2:
                // Error en la conexión con la base de datos
                req.setAttribute("mensajeAtributo", "Registrado correctamente, ya puedes iniciar sesion.");
                break;
        }
        
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
        
    }

}
