/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.editor.Revista;
import java.io.IOException;
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
@WebServlet(name = "BuscarServlet", urlPatterns = {"/BuscarServlet"})
public class BuscarServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreRevista = request.getParameter("query");
        String categoria = request.getParameter("filtro");
        String tagsString = request.getParameter("tags");

        String[] tags = (tagsString != null && !tagsString.isEmpty()) ? tagsString.split(",") : new String[0];
        
        ArrayList<Revista> revistas = Revista.getRevistas(nombreRevista, categoria, tags);
        
        request.setAttribute("revistasBuscadasAtributo", revistas);
        
        request.getRequestDispatcher("/Suscriptor/resultadoBusqueda.jsp").forward(request, response);
    }
}
