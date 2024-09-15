/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.anuncios.Anuncio;
import com.mycompany.revistas.digitales.backend.usuarios.Anunciante;
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
@WebServlet(name = "DesactivarAnuncioServlet", urlPatterns = {"/DesactivarAnuncioServlet"})
public class DesactivarAnuncioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("anuncioId");
        
        Anuncio.desactivar(id);
        
        HttpSession sesion = request.getSession(false);
        String nombreUsuario = (String) sesion.getAttribute("nombreAtributo");
        
        Anunciante anunciante = new Anunciante(nombreUsuario);
        anunciante.recuperarAnunciosEnLaBD();
        sesion.setAttribute("anunciosAtribute", anunciante.getAnuncios());
        
        request.getRequestDispatcher("/Home/homeAnunciante.jsp").forward(request, response);
    }

}
