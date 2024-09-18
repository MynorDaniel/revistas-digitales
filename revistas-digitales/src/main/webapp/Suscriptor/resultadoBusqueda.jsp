<%@page import="com.mycompany.revistas.digitales.backend.editor.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busqueda</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/Includes/headerSuscriptor.jsp"/>
        <div class="container">
            <h1 class="my-4 text-center" style="font-size: 36px; font-weight: bold; color: #333;">Bienvenido al Panel de Revistas</h1>
            <p class="text-center" style="font-size: 18px; color: #555;">En esta sección, puedes publicar nuevas revistas, publicar versiones de cada una, administrar tus revistas, etc.</p>
            <h2 class="my-4" style="font-size: 28px; font-weight: bold; color: #444;">Mis Revistas</h2>

            <div class="row">
                <%
                    ArrayList<Revista> revistas = (ArrayList<Revista>) request.getAttribute("revistasBuscadasAtributo");

                    if (revistas != null && !revistas.isEmpty()) {
                        for (Revista revista : revistas) {
                %>
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card bg-dark text-white h-100">
                                    <div class="card-body">
                                        <h5 class="card-title"><%= revista.getNombre() %></h5>
                                        <p class="card-text">
                                            Fecha de publicación: <%= revista.getFecha() %><br>
                                            <%= revista.getDescripcion() != null ? revista.getDescripcion() : "Sin descripción" %>
                                        </p>
                                    </div>
                                    <div class="card-footer">
                                        <form action="${pageContext.request.contextPath}/RevistaServlet" method="GET">
                                            <input type="hidden" name="revistaName" value="<%= revista.getNombre() %>"/>
                                            <input class="btn btn-primary" type="submit" value="Ver más"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                <%
                        }
                    } else {
                %>
                        <div class="col-12">
                            <p class="text-center">No hay Revistas disponibles</p>
                        </div>
                <%
                    }
                %>
            </div>
        </div>

        <jsp:include page="/Includes/footer.jsp"/>
    </body>
</html>
