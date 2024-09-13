<%-- 
    Document   : homeAnunciante
    Created on : Sep 12, 2024, 12:30:55 AM
    Author     : mynordma
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.mycompany.revistas.digitales.backend.Anuncio"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Anunciante</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/Includes/headerAnuncios.jsp"/>
        <div class="px-3 py-2 border-bottom mb-3"></div>

        <div class="container">
            <!-- Título principal -->
            <h1 class="my-4 text-center" style="font-size: 36px; font-weight: bold; color: #333;">Bienvenido al Panel de Anuncios</h1>

            <!-- Descripción -->
            <p class="text-center" style="font-size: 18px; color: #555;">En esta sección, puedes comprar anuncios para que aparezcan en la aplicacion, ademas de gestionarlos.</p>

            <!-- Lista de anuncios -->
            <h2 class="my-4" style="font-size: 28px; font-weight: bold; color: #444;">Lista de Anuncios Comprados</h2>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Fecha De Compra</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Vigencia</th>
                        <th scope="col">Texto</th>
                        <th scope="col">Imagen</th>
                        <th scope="col">Video</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Precio</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Obtener la lista de anuncios de la sesión
                        ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) session.getAttribute("anunciosAtribute");

                        // Verificar si hay anuncios
                        if (anuncios != null && !anuncios.isEmpty()) {
                            // Iterar sobre la lista de anuncios
                            for (Anuncio anuncio : anuncios) {
                    %>
                                <tr>
                                    <td><%= anuncio.getFecha() %></td>
                                    <td><%= anuncio.getTipo() %></td>
                                    <td><%= anuncio.getVigencia() %></td>
                                    <td><%= (anuncio.getTexto() != null) ? anuncio.getTexto() : "No hay texto" %></td>
                                    <td><%= (anuncio.getImagen() != null) ? "Imagen disponible" : "No hay imagen" %></td>
                                    <td><%= (anuncio.getVideo() != null) ? "Video disponible" : "No hay video" %></td>
                                    <td><%= anuncio.getEstado() %></td>
                                    <td><%= anuncio.getPrecio() %></td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="8" class="text-center">No hay anuncios disponibles</td>
                            </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

        <jsp:include page="/Includes/footer.jsp"/>

    </body>
</html>
