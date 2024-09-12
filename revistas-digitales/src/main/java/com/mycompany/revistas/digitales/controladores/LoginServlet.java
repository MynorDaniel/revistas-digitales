/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.revistas.digitales.controladores;

import com.mycompany.revistas.digitales.backend.Login;
import java.io.IOException;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Login login = new Login();
        boolean usuarioLogueado = login.loguear(req);
        
        if(usuarioLogueado){
            HttpSession sesion = req.getSession();
            
            sesion.setAttribute("nombreAtributo", login.getNombre());
            sesion.setAttribute("rolAtributo", login.getRol());
            
            switch (login.getRol()) {
                case "EDITOR":
                    req.getRequestDispatcher("/Home/homeEditor.jsp").forward(req, resp);
                    break;
                case "SUSCRIPTOR":
                    req.getRequestDispatcher("/Home/homeSuscriptor.jsp").forward(req, resp);
                    break;
                case "ADMINISTRADOR":
                    req.getRequestDispatcher("/Home/homeAdministrador.jsp").forward(req, resp);
                    break;
                case "ANUNCIANTE":
                    req.getRequestDispatcher("/Home/homeAnunciante.jsp").forward(req, resp);
                    break;
            }
            
        }else{
            req.setAttribute("mensajeAtributo", "Error al iniciar sesion, revisa los datos.");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
