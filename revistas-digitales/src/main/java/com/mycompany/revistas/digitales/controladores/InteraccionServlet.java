/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.editor.Publicacion;
import com.mycompany.revistas.digitales.backend.editor.Revista;
import com.mycompany.revistas.digitales.backend.suscriptor.MeGusta;
import com.mycompany.revistas.digitales.backend.suscriptor.Suscripcion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
@WebServlet(name = "InteraccionServlet", urlPatterns = {"/InteraccionServlet"})
public class InteraccionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        String revistaName = request.getParameter("revistaName");
        String fechaSuscripcion = request.getParameter("fechaSuscripcion");
        
        // Si la fecha no es proporcionada, se usa la fecha actual
        
        LocalDate fecha;
        
        if (fechaSuscripcion == null || fechaSuscripcion.isEmpty()) {
            fecha = LocalDate.now();
        } else {
            fecha = LocalDate.parse(fechaSuscripcion, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
        
        HttpSession sesion = request.getSession(false);
        
        if ("suscribirse".equals(accion)) {
            Suscripcion suscripcion = new Suscripcion(revistaName, (String) sesion.getAttribute("nombreAtributo"), fecha);
            suscripcion.realizarSuscripcion();
        } else if ("like".equals(accion)) {
            MeGusta meGusta = new MeGusta(revistaName, (String) sesion.getAttribute("nombreAtributo"));
            meGusta.guardar();
        }

        Revista revistaActualizada = Revista.recuperarRevista(revistaName);
        ArrayList<Publicacion> publicaciones = Publicacion.recuperarPublicaciones(revistaName);
        sesion.setAttribute("revistaAtributo", revistaActualizada);
        sesion.setAttribute("tagsAtributo", Revista.recuperarTags(revistaName));
        sesion.setAttribute("publicacionesAtributo", publicaciones);
        sesion.setAttribute("numeroMegusta", MeGusta.numeroMegusta(revistaName));
        sesion.setAttribute("usuarioSuscrito", Suscripcion.usuarioSuscrito(revistaName, (String) sesion.getAttribute("nombreAtributo")));
        request.getRequestDispatcher("/Editor/revista.jsp").forward(request, response);
    }
}
