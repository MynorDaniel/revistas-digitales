<%@page import="com.mycompany.revistas.digitales.backend.editor.Publicacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.revistas.digitales.backend.editor.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Revista</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <!-- Header dependiendo del rol del usuario -->
        <%
            Revista revista = (Revista) session.getAttribute("revistaAtributo");
            String rol = (String) session.getAttribute("rolAtributo");
            String headerFile = ""; 

            if ("EDITOR".equals(rol)) {
                headerFile = "/Includes/headerEditor.jsp";
            } else if ("SUSCRIPTOR".equals(rol)) {
                headerFile = "/Includes/headerSuscriptor.jsp";
            }
        %>

        <jsp:include page="<%= headerFile %>" />
        
        <br>
        <div class="container">
            <div class="jumbotron p-3 p-md-5 text-white rounded bg-dark">
                <div class="col-md-6 px-0">
                    <h1 class="display-4 font-italic"><%= revista.getNombre() %></h1>
                    <p class="lead my-3"><%= revista.getDescripcion()%></p>
                </div>
                <div class="card-body">
                    <ul class="list-unstyled">
                        <li><strong>Autor:</strong> <%= revista.getEditor() %></li>
                        <li><strong>Categoría:</strong> <%= revista.getCategoria() %></li>
                        <li><strong>Me Gusta:</strong> <%= session.getAttribute("numeroMegusta") %></li>
                        <li><strong>Bloqueo de Me Gusta y Comentarios:</strong> <%= revista.getBloqueo()%></li>
                        <li><strong>Fecha:</strong> <%= revista.getFecha() %></li>
                        <li><strong>Tags:</strong>
                            <ul>
                                <%
                                    List<String> tags = (List<String>) session.getAttribute("tagsAtributo");
                                    if (tags != null && !tags.isEmpty()) {
                                        for (String tag : tags) {
                                %>
                                            <li><%= tag %></li>
                                <%
                                        }
                                    } else {
                                %>
                                        <li>No hay tags disponibles.</li>
                                <%
                                    }
                                %>
                            </ul>
                        </li>
                    </ul>
                </div>
                <% if ("EDITOR".equals(session.getAttribute("rolAtributo"))) { %>
                    <!-- Botón para redirigir a verSuscriptores.jsp -->
                    <div class="mt-3">
                        <a href="verSuscriptores.jsp" class="btn btn-info">Ver Suscriptores</a>
                    </div>
                    <!-- Botón para editar revista -->
                    <div class="mt-3">
                        <a href="${pageContext.request.contextPath}/Editor/editarRevista.jsp" class="btn btn-info">Editar</a>
                    </div>
                <% } else if ("SUSCRIPTOR".equals(session.getAttribute("rolAtributo"))) { %>
                    <% if (!"SI".equals(revista.getBloqueo())) { %> <!-- Solo muestra si no está bloqueado -->
                        <!-- Verificar si el usuario está suscrito -->
                        <%
                            Boolean usuarioSuscrito = (Boolean) session.getAttribute("usuarioSuscrito");
                            if (usuarioSuscrito != null && usuarioSuscrito) {
                        %>
                            <div class="mt-3">
                                <span class="badge badge-success">Suscrito</span>
                            </div>
                        <% } else { %>
                            <!-- Formulario para suscribirse -->
                            <div class="mt-3">
                                <form action="${pageContext.request.contextPath}/InteraccionServlet" method="POST">
                                    <input type="hidden" name="accion" value="suscribirse">
                                    <input type="hidden" name="revistaName" value="<%= revista.getNombre() %>">
                                    
                                    <!-- Campo para seleccionar la fecha de suscripción -->
                                    <div class="form-group">
                                        <label for="fechaSuscripcion">Fecha de suscripción (opcional):</label>
                                        <input type="date" class="form-control" id="fechaSuscripcion" name="fechaSuscripcion">
                                    </div>
                                    <br>
                                    <button type="submit" class="btn btn-danger">Suscribirse</button>
                                </form>
                            </div>
                        <% } %>

                        <!-- Formulario para dar me gusta -->
                        <div class="mt-3">
                            <form action="${pageContext.request.contextPath}/InteraccionServlet" method="POST">
                                <input type="hidden" name="accion" value="like">
                                <input type="hidden" name="revistaName" value="<%= revista.getNombre() %>">
                                <button type="submit" class="btn btn-primary">Like</button>
                            </form>
                        </div>
                    <% } %>
                <% } %>
                <br>

                <!-- Tabla de versiones -->         
                <table class="table table-striped table-dark">
                    <thead>
                        <tr>
                            <th scope="col">Versión</th>
                            <th scope="col">PDF</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Publicacion> publicaciones = (ArrayList<Publicacion>) session.getAttribute("publicacionesAtributo");

                            if (publicaciones != null && !publicaciones.isEmpty()) {
                                for (Publicacion pub : publicaciones) {
                                    int version = pub.getVersion();
                        %>
                                    <tr>
                                        <td><%= version %></td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/PublicacionServlet" method="GET">
                                                <input type="hidden" name="version" value="<%= version %>">
                                                <button type="submit" class="btn btn-primary" title="Ver PDF">
                                                    <i class="bi bi-file-earmark-pdf fs-3"></i>
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                        <%
                                }
                            } else {
                        %>
                            <tr>
                                <td colspan="2" class="text-center">No hay publicaciones disponibles.</td>
                            </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>   
        </div>

        <!-- Sección de comentarios, siempre al final -->
        <% if (!"SI".equals(revista.getBloqueo())) { %> <!-- Solo muestra si no está bloqueado -->
        <div class="container">
            <div class="comment-container comment-section card bg-dark text-white">
                <div class="card-body">
                    <div class="d-flex flex-column">
                        <!-- Añadir comentario -->
                        <div class="add-comment-section d-flex flex-row mb-4">
                            <i class="bi bi-person mr-2 fs-3"></i>
                            <input type="text" class="form-control mr-3 mx-2" placeholder="Agregar comentario">
                            <button class="btn btn-secondary" type="button">Comentar</button>
                        </div>

                        <!-- Comentarios existentes -->
                        <div class="commented-section mt-2">
                            <div class="d-flex flex-row align-items-center commented-user">
                                <h5 class="mr-2">Nombre usuario</h5>
                                <span class="dot mb-1"></span>
                            </div>
                            <div class="comment-text-sm">
                                <span>
                                    Nunc sed id semper risus in hendrerit gravida rutrum. Non odio euismod lacinia at quis risus sed. Commodo ullamcorper a lacus vestibulum sed arcu non odio euismod.
                                </span>
                            </div>
                        </div>
                        <hr>
                        <div class="commented-section mt-2">
                            <div class="d-flex flex-row align-items-center commented-user">
                                <h5 class="mr-2">Nombre usuario</h5>
                                <span class="dot mb-1"></span>
                            </div>
                            <div class="comment-text-sm">
                                <span>
                                    Nunc sed id semper risus in hendrerit gravida rutrum. Non odio euismod lacinia at quis risus sed. Commodo ullamcorper a lacus vestibulum sed arcu non odio euismod.
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% } %>

        <jsp:include page="/Includes/footer.jsp"/>
    </body>
</html>
