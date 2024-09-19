<%@page import="com.mycompany.revistas.digitales.backend.usuarios.Usuario"%>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Perfil de Usuario</title>
    <jsp:include page="/Includes/resources.jsp"/>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <%
        String[] perfilAtributos = (String[]) request.getAttribute("perfilAtributo");
    %>
    

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-8 col-md-10">
                <div class="card shadow-lg border-0 rounded">
                    <div class="card-header bg-dark text-white text-center">
                        <i class="bi bi-person fs-1"></i>
                    </div>
                    <div class="card-body">
                        <!-- Mostrar nombre del usuario y rol -->
                        <div class="text-center mb-4">
                            <h3 class="font-weight-bold"><%= session.getAttribute("nombreAtributo") %></h3>
                            <p class="text-muted"><strong>Rol:</strong> <%= session.getAttribute("rolAtributo") %></p>
                        </div>

                        <!-- Mostrar atributos del perfil -->
                        <div class="mb-3">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item"><strong>Hobbies:</strong> <%= (perfilAtributos != null && perfilAtributos.length > 0) ? perfilAtributos[0] : "No disponible" %></li>
                                <li class="list-group-item"><strong>Intereses:</strong> <%= (perfilAtributos != null && perfilAtributos.length > 1) ? perfilAtributos[1] : "No disponible" %></li>
                                <li class="list-group-item"><strong>Descripción:</strong> <%= (perfilAtributos != null && perfilAtributos.length > 2) ? perfilAtributos[2] : "No disponible" %></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
                            
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="card shadow-lg border-0 rounded">
                    <div class="card-header bg-dark text-white text-center">
                        <h2>Editar Perfil</h2>
                    </div>
                    <div class="card-body">
                        <!-- Formulario para editar perfil -->
                        <form action="${pageContext.request.contextPath}/PerfilServlet" method="post">
                            
                            <!-- Hobbies -->
                            <div class="form-group">
                                <label for="hobbies">Hobbies</label>
                                <input type="text" class="form-control" id="hobbies" name="hobbies" placeholder="Ingresa tus hobbies">
                            </div>
                            <!-- Intereses -->
                            <div class="form-group">
                                <label for="intereses">Intereses</label>
                                <input type="text" class="form-control" id="intereses" name="intereses" placeholder="Ingresa tus intereses">
                            </div>
                            <!-- Descripción -->
                            <div class="form-group">
                                <label for="descripcion">Descripción</label>
                                <textarea class="form-control" id="descripcion" name="descripcion" rows="4" placeholder="Escribe una breve descripción sobre ti"></textarea>
                            </div>
                            <!-- Nueva Clave -->
                            <div class="form-group">
                                <label for="clave">Nueva Clave</label>
                                <input type="password" class="form-control" id="clave" name="clave" placeholder="Ingresa una nueva clave">
                            </div>
                            <!-- Botón de enviar -->
                            <div class="text-center">
                                <label>Al guardar los cambios se pedira iniciar sesion de nuevo</label>
                                <button type="submit" class="btn btn-success">Guardar Cambios</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/Includes/footer.jsp"/>
    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


