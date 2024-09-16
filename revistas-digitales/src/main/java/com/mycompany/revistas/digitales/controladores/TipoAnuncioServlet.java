/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.anuncios.TipoAnuncio;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "TipoAnuncioServlet", urlPatterns = {"/TipoAnuncioServlet"})
public class TipoAnuncioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<TipoAnuncio> tipos = TipoAnuncio.obtenerTipos();
        HttpSession sesion = request.getSession(false);
        sesion.setAttribute("tiposAnuncioAtributo", tipos);
        request.getRequestDispatcher("/Admin/anunciosAdmin.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipoAnuncio = request.getParameter("tipoAnuncio");
        String precioStr = request.getParameter("precio");
        double nuevoPrecio = Double.parseDouble(precioStr);
        
        TipoAnuncio.cambiarPrecioDB(tipoAnuncio, nuevoPrecio);
        
        ArrayList<TipoAnuncio> tipos = TipoAnuncio.obtenerTipos();
        HttpSession sesion = request.getSession(false);
        sesion.setAttribute("tiposAnuncioAtributo", tipos);
        request.getRequestDispatcher("/Admin/anunciosAdmin.jsp").forward(request, response);
    }
}
