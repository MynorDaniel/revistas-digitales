/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.editor.Publicacion;
import com.mycompany.revistas.digitales.backend.editor.Revista;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "EditarRevistaServlet", urlPatterns = {"/EditarRevistaServlet"})
@MultipartConfig
public class EditarRevistaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Part filePart = request.getPart("pdf");
        boolean sinArchivo = (filePart == null || filePart.getSize() == 0);
        String bloqueo = request.getParameter("bloqueo");
        
        HttpSession sesion = request.getSession(false);
        Revista revista = (Revista) sesion.getAttribute("revistaAtributo");
        Revista.cambiarBloqueo(revista.getNombre(), bloqueo);

        if(!sinArchivo){
            try (InputStream fileContent = filePart.getInputStream()) {
                Publicacion publicacion = new Publicacion(revista.getNombre(), fileContent);
                publicacion.guardarPublicacionBD();
            }catch(Exception e){
                System.out.println("Error revista servlet");
            }
        }
        
        Revista revistaActualizada = Revista.recuperarRevista(revista.getNombre());
        ArrayList<Publicacion> publicaciones = Publicacion.recuperarPublicaciones(revista.getNombre());
        sesion.setAttribute("revistaAtributo", revistaActualizada);
        sesion.setAttribute("tagsAtributo", Revista.recuperarTags(revista.getNombre()));
        sesion.setAttribute("publicacionesAtributo", publicaciones);
        request.getRequestDispatcher("/Editor/revista.jsp").forward(request, response);
    }

}
