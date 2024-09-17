<%-- 
    Document   : homeEditor
    Created on : Sep 12, 2024, 12:29:46 AM
    Author     : mynordma
--%>

<%@page import="com.mycompany.revistas.digitales.backend.editor.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/Includes/headerEditor.jsp"/>
        
        <%
            String mensaje = (String) request.getAttribute("mensajeAtributo");
            if (mensaje != null && !mensaje.isEmpty()) {
        %>
            <div class="alert alert-warning" role="alert">
                <%= mensaje %>
            </div>
        <%
            }
        %>
        
        <div class="container">
            <h1 class="my-4 text-center" style="font-size: 36px; font-weight: bold; color: #333;">Bienvenido al Panel de Revistas</h1>
            <p class="text-center" style="font-size: 18px; color: #555;">En esta sección, puedes publicar nuevas revistas, publicar versiones de cada una, administrar tus revistas, etc.</p>
            <h2 class="my-4" style="font-size: 28px; font-weight: bold; color: #444;">Mis Revistas</h2>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Fecha</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<Revista> revistas = (ArrayList<Revista>) session.getAttribute("revistasAtributo");

                        if (revistas != null && !revistas.isEmpty()) {
                            for (Revista revista : revistas) {
                    %>
                                    <tr>
                                        <td><%= revista.getNombre() %></td>
                                        <td><%= revista.getFecha() %></td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/RevistaServlet" method="GET">
                                                <input type="hidden" name="revistaName" value="<%= revista.getNombre() %>"/>
                                                <input class="btn btn-link" type="submit" value="Ver"/>
                                            </form>
                                        </td>
                                    </tr>
                    <%
                            }
                        } else {
                    %>
                            <tr>
                                <td colspan="3" class="text-center">No hay Revistas disponibles</td>
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
