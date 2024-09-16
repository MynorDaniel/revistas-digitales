<%-- 
    Document   : anunciosAdmin
    Created on : Sep 15, 2024, 4:43:37 PM
    Author     : mynordma
--%>

<%@page import="com.mycompany.revistas.digitales.backend.anuncios.TipoAnuncio"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Administrar Anuncios</title>
    <jsp:include page="/Includes/resources.jsp"/>
    <script>
        // Función de validación para precio
        function validarPrecio() {
            const precioInput = document.getElementById('precio');
            const submitButton = document.getElementById('submitButton');
            const precio = precioInput.value;
            const regex = /^\d+(\.\d{1,2})?$/; // Número con máximo dos decimales

            // Validar que el precio sea mayor que 0 y tenga como máximo dos decimales
            if (precio > 0 && regex.test(precio)) {
                submitButton.disabled = false; // Habilitar el botón si es válido
            } else {
                submitButton.disabled = true; // Deshabilitar si no es válido
            }
        }
    </script>
</head>
<body>
    <jsp:include page="/Includes/headerAdmin.jsp"/>
    
    <div class="container">
        <h1 class="my-4 text-center" style="font-size: 36px; font-weight: bold; color: #333;">Administrar Anuncios</h1>
        <p class="text-center" style="font-size: 18px; color: #555;">Cambiar Precio</p>

        <!-- Tabla existente de tipos de anuncio -->
        <table class="table table-striped">
            <thead>
              <tr>
                <th scope="col">Tipo</th>
                <th scope="col">Precio</th>
              </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<TipoAnuncio> tipos = (ArrayList<TipoAnuncio>) session.getAttribute("tiposAnuncioAtributo");

                    if (tipos != null && !tipos.isEmpty()) {
                        for (TipoAnuncio tipo : tipos) {
                %>
                            <tr>
                                <td><%= tipo.getTipo() %></td>
                                <td><%= tipo.getPrecio() %></td>
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

        <!-- Nuevo formulario para cambiar el precio del anuncio -->
        <form action="<%= request.getContextPath() %>/TipoAnuncioServlet" method="POST">
            <div class="form-group">
                <label for="tipoAnuncio">Selecciona el tipo de anuncio:</label>
                <select id="tipoAnuncio" name="tipoAnuncio" class="form-control">
                    <%
                        if (tipos != null && !tipos.isEmpty()) {
                            for (TipoAnuncio tipo : tipos) {
                    %>
                                <option value="<%= tipo.getTipo() %>"><%= tipo.getTipo() %></option>
                    <%
                            }
                        } else {
                    %>
                            <option disabled>No hay tipos de anuncios disponibles</option>
                    <%
                        }
                    %>
                </select>
            </div>

            <div class="form-group">
                <label for="precio">Nuevo precio:</label>
                <input type="text" id="precio" name="precio" class="form-control" oninput="validarPrecio()" placeholder="Ingresa el precio">
            </div>
                <br>
            <div class="text-center">
                <button type="submit" id="submitButton" class="btn btn-primary" disabled>Cambiar Precio</button>
            </div>
        </form>
    </div>
</body>
</html>
