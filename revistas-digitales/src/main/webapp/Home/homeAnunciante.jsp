<%-- 
    Document   : homeAnunciante
    Created on : Sep 12, 2024, 12:30:55 AM
    Author     : mynordma
--%>

<%@page import="com.mycompany.revistas.digitales.backend.anuncios.Anuncio"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <jsp:include page="/Includes/resources.jsp"/>
        <style>
            /* Añadir aquí el CSS para ocultar la columna */
            .ocultar-columna {
                display: none;
            }
        </style>
    </head>
    <body>
        <jsp:include page="/Includes/headerAnuncios.jsp"/>
        <%
            String mensaje = (String) request.getAttribute("alertaAtributo");
            if (mensaje != null && !mensaje.isEmpty()) {
        %>
            <div class="alert alert-warning" role="alert">
                <%= mensaje %>
            </div>
        <%
            }
        %>
        <div class="px-3 py-2 border-bottom mb-3"></div>

        <div class="container">
            <h1 class="my-4 text-center" style="font-size: 36px; font-weight: bold; color: #333;">Bienvenido al Panel de Anuncios</h1>
            <p class="text-center" style="font-size: 18px; color: #555;">En esta sección, puedes comprar anuncios para que aparezcan en la aplicación, además de gestionarlos.</p>
            <h2 class="my-4" style="font-size: 28px; font-weight: bold; color: #444;">Lista de Anuncios Comprados</h2>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th class="ocultar-columna" scope="col">#</th>
                        <th scope="col">Fecha De Compra</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Vigencia</th>
                        <th scope="col">Texto</th>
                        <th scope="col">Imagen</th>
                        <th scope="col">Video</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Visibilidad</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Anuncio> anuncios = (ArrayList<Anuncio>) session.getAttribute("anunciosAtribute");

                        if (anuncios != null && !anuncios.isEmpty()) {
                            for (Anuncio anuncio : anuncios) {
                    %>
                                <tr>
                                    <td class="ocultar-columna"><%= anuncio.getId() %></td>
                                    <td><%= anuncio.getFecha() %></td>
                                    <td><%= anuncio.getTipo() %></td>
                                    <td><%= anuncio.getVigencia() %></td>
                                    <td><%= (anuncio.getTexto() != null) ? anuncio.getTexto() : "No hay texto" %></td>
                                    <td><%= (anuncio.getImagen() != null) ? "Imagen disponible" : "No hay imagen" %></td>
                                    <td><%= (anuncio.getVideo() != null) ? "Video disponible" : "No hay video" %></td>
                                    <td><%= anuncio.getEstado() %></td>
                                    <td><%= anuncio.getPrecio() %></td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/DesactivarAnuncioServlet" method="POST">
                                            <input type="hidden" name="anuncioId" value="<%= anuncio.getId() %>"/>
                                            <input class="btn btn-primary" type="submit" value="Desactivar"/>
                                        </form>
                                    </td>
                                </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="10" class="text-center">No hay anuncios disponibles</td>
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

