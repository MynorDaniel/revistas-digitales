<%-- 
    Document   : pagar.jsp
    Created on : Sep 13, 2024, 1:49:05 PM
    Author     : mynordma
--%>

<%@page import="com.mycompany.revistas.digitales.backend.anuncios.Anuncio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagar</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <div class="px-3 py-2 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <!-- Logo alineado a la izquierda -->
                    <a href="/" class="d-flex align-items-center text-white text-decoration-none">
                        <i class="bi bi-book" style="font-size: 40px;"></i>
                    </a>

                    <!-- Título centrado -->
                    <h1 class="mx-auto text-center" style="font-size: 24px;">REVISTAS DIGITALES</h1>

                    <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                        <li>
                            <a href="${pageContext.request.contextPath}/CarteraDigitalServlet" class="nav-link text-white">
                                <i class="bi bi-cash-coin d-block mx-auto mb-1" style="font-size: 24px;"></i>
                                Cartera Digital
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="container">
            <!-- Título principal -->
            <h1 class="my-4 text-center" style="font-size: 36px; font-weight: bold; color: #333;">Nuevo Anuncio</h1>


            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Fecha De Compra</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Vigencia</th>
                        <th scope="col">Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        Anuncio anuncio = (Anuncio) session.getAttribute("anuncioAtribute");

                    %>
                    <tr>
                        <td><%= anuncio.getFecha() %></td>
                        <td><%= anuncio.getTipo() %></td>
                        <td><%= anuncio.getVigencia() %></td>
                        <td><%= anuncio.getPrecio() %></td>
                    </tr>
                    
                </tbody>
            </table>
                    
            <form method="POST" action="${pageContext.request.contextPath}/PagoServlet">
                <input class="btn btn-primary" type="submit" value="Confirmar" />
            </form>
        </div>      
                        
    </body>
</html>
