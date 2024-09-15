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
        byte[] imagen = partAByte(request.getPart("imagenAnuncio"));
        byte[] video = partAByte(request.getPart("videoAnuncio"));
        TipoAnuncio tipo = new TipoAnuncio(tipoAnuncio);
        double precio = tipo.obtenerPrecio();
        
        Anuncio anuncio = new Anuncio(Anuncio.getTotal(), LocalDate.parse(fecha), tipoAnuncio, vigencia, texto, 
        imagen, video, "ACTIVADO", precio);
        
        
        // Guardar los parametros en la sesion

        HttpSession sesion = request.getSession();
        
        sesion.setAttribute("anuncioAtribute", anuncio);
        
        request.getRequestDispatcher("/pagar.jsp").forward(request, response);
    }
    
    private byte[] partAByte(Part part) throws IOException {
        // Verifica si el Part tiene un contenido
        if (part == null) {
            return null;
        }

        try (InputStream inputStream = part.getInputStream();
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            // Lee el contenido del archivo en bloques y lo escribe en el ByteArrayOutputStream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Devuelve el contenido como un arreglo de bytes
            return outputStream.toByteArray();
        }
    }
}
