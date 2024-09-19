/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.anuncios.Anuncio;
import com.mycompany.revistas.digitales.backend.anuncios.TipoAnuncio;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "AnuncioServlet", urlPatterns = {"/AnuncioServlet"})
@MultipartConfig
public class AnuncioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener los valores seleccionados por el usuario
        String tipoAnuncio = request.getParameter("tipoAnuncio");
        String vigencia = request.getParameter("vigencia");
        String fecha = request.getParameter("fecha");
        String texto = request.getParameter("textoAnuncio");

        // Obtener imagen y video como InputStream
        InputStream imagenStream = null;
        InputStream videoStream = null;

        Part imagenPart = request.getPart("imagenAnuncio");
        if (imagenPart != null && imagenPart.getSize() > 0) {
            imagenStream = imagenPart.getInputStream();
        }

        Part videoPart = request.getPart("videoAnuncio");
        if (videoPart != null && videoPart.getSize() > 0) {
            videoStream = videoPart.getInputStream();
        }

        // Crear el objeto TipoAnuncio y obtener el precio
        TipoAnuncio tipo = new TipoAnuncio(tipoAnuncio);
        double precio = tipo.obtenerPrecio();

        // Crear el objeto Anuncio con InputStream para imagen y video
        Anuncio anuncio = new Anuncio(Anuncio.getTotal(), LocalDate.parse(fecha), tipoAnuncio, vigencia, texto,
                imagenStream, videoStream, "ACTIVADO", precio);

        // Guardar el anuncio en la sesión
        HttpSession sesion = request.getSession();
        sesion.setAttribute("anuncioAtribute", anuncio);

        // Redirigir a la página de pago
        request.getRequestDispatcher("/pagar.jsp").forward(request, response);
    }

}
