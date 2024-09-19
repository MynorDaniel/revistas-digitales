/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.Pago;
import com.mycompany.revistas.digitales.backend.anuncios.Anuncio;
import com.mycompany.revistas.digitales.backend.usuarios.Anunciante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "PagoServlet", urlPatterns = {"/PagoServlet"})
@MultipartConfig

public class PagoServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        
        HttpSession sesion = request.getSession(false);
        String nombreUsuario = (String) sesion.getAttribute("nombreAtributo");
        
        Anuncio anuncio = (Anuncio) sesion.getAttribute("anuncioAtribute");
        boolean updateExitoso = anuncio.guardarEnLaBD(nombreUsuario);
        
        boolean pagoExitoso = false;
        if(updateExitoso){
            Pago pago = new Pago();
            pagoExitoso = pago.pagarAnuncio(nombreUsuario, anuncio.getPrecio(), anuncio.getFecha()); 
        }
        
        
        if(updateExitoso && pagoExitoso){
            request.setAttribute("alertaAtributo", "Anuncio comprado exitosamente.");
        }else{
            request.setAttribute("alertaAtributo", "Error en la compra");
        }
        
        Anunciante anunciante = new Anunciante(nombreUsuario);
        anunciante.recuperarAnunciosEnLaBD();
        sesion.setAttribute("anunciosAtribute", anunciante.getAnuncios());
        
        request.getRequestDispatcher("/Home/homeAnunciante.jsp").forward(request, response);
    }
}
