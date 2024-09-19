/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.editor.Publicacion;
import com.mycompany.revistas.digitales.backend.editor.Revista;
import com.mycompany.revistas.digitales.backend.suscriptor.MeGusta;
import com.mycompany.revistas.digitales.backend.suscriptor.Suscripcion;
import com.mycompany.revistas.digitales.backend.usuarios.Editor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "RevistaServlet", urlPatterns = {"/RevistaServlet"})
public class RevistaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Revista revista = Revista.recuperarRevista(request.getParameter("revistaName"));
        ArrayList<Publicacion> publicaciones = Publicacion.recuperarPublicaciones(revista.getNombre());
        
        HttpSession sesion = request.getSession(false);
        sesion.setAttribute("revistaAtributo", revista);
        sesion.setAttribute("tagsAtributo", Revista.recuperarTags(revista.getNombre()));
        sesion.setAttribute("publicacionesAtributo", publicaciones);
        sesion.setAttribute("numeroMegusta", MeGusta.numeroMegusta(revista.getNombre()));
        sesion.setAttribute("usuarioSuscrito", Suscripcion.usuarioSuscrito(revista.getNombre(), (String) sesion.getAttribute("nombreAtributo")));
        request.getRequestDispatcher("/Editor/revista.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("desc");
        String categoria = request.getParameter("cat");
        String fecha = request.getParameter("fecha");
        
        String tagsString = request.getParameter("tags");
        List<String> tags = null;
        if (tagsString != null && !tagsString.isEmpty()) {
            tags = Arrays.asList(tagsString.split(","));
        }
        
        HttpSession sesion = request.getSession(false);
        
        Revista nuevaRevista = new Revista(nombre, (String) sesion.getAttribute("nombreAtributo"), "NO", LocalDate.parse(fecha), 0, descripcion, categoria);
        boolean guardadoExitoso = nuevaRevista.guardarEnDB();
        nuevaRevista.guardarTags(tags);
        
        if(guardadoExitoso){
            Editor editor = new Editor((String) sesion.getAttribute("nombreAtributo"));
            editor.recuperarRevistasDB();
            sesion.setAttribute("revistasAtributo", editor.getRevistas());
            request.setAttribute("mensajeAtributo", "Revista publicada, ya puedes crear versiones.");
        }else{
            request.setAttribute("mensajeAtributo", "Error en la creacion, verifica que el nombre no coincida con otra revista.");
        }
        
        request.getRequestDispatcher("/Home/homeEditor.jsp").forward(request, response);
    }
}
