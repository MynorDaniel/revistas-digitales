/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.anuncios.Anuncio;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "AnuncioCaducadoServlet", urlPatterns = {"/AnuncioCaducadoServlet"})
public class AnuncioCaducadoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Anuncio> anunciosCaducados = Anuncio.obtenerAnunciosCaducados();
        for (Anuncio anuncioCaducado : anunciosCaducados) {
            Anuncio.desactivar(String.valueOf(anuncioCaducado.getId()));
        }
        
        request.getRequestDispatcher("/Home/homeAdministrador.jsp").forward(request, response);
    }

}
