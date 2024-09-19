/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.Encriptacion;
import com.mycompany.revistas.digitales.backend.usuarios.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "PerfilServlet", urlPatterns = {"/PerfilServlet"})
public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);

        String nombreUsuario = (String) sesion.getAttribute("nombreAtributo");
        String[] atributosPerfil = Usuario.getAtributos(nombreUsuario);

        request.setAttribute("perfilAtributo", atributosPerfil);
        request.getRequestDispatcher("/perfil.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String hobbies = request.getParameter("hobbies");
        String intereses = request.getParameter("intereses");
        String descripcion = request.getParameter("descripcion");
        Encriptacion encriptacion = new Encriptacion(request.getParameter("clave"));
        String nuevaClave = encriptacion.encriptar();
       
        HttpSession sesion = request.getSession(false);
        String nombreUsuarioActual = (String) sesion.getAttribute("nombreAtributo");

        Usuario.actualizarAtributos(nombreUsuarioActual, hobbies, intereses, descripcion, nuevaClave);
        
        sesion.invalidate();
        
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


}
