/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.CarteraDigital;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CarteraDigitalServlet", urlPatterns = {"/CarteraDigitalServlet"})
public class CarteraDigitalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(false);
        String nombreUsuario = (String) sesion.getAttribute("nombreAtributo");
        
        CarteraDigital cd = new CarteraDigital();
        String saldoUsuario = cd.obtenerSaldo(nombreUsuario);
        
        sesion.setAttribute("saldoAtributo", saldoUsuario);
        
        request.getRequestDispatcher("/carteraDigital.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(false);
        String nombreUsuario = (String) sesion.getAttribute("nombreAtributo");
        
        CarteraDigital cd = new CarteraDigital();
        cd.recargar(nombreUsuario, Double.parseDouble((String) request.getParameter("monto")));
        
        String saldoUsuario = cd.obtenerSaldo(nombreUsuario);
        sesion.setAttribute("saldoAtributo", saldoUsuario);
        
        request.getRequestDispatcher("/carteraDigital.jsp").forward(request, response);
    }
}
