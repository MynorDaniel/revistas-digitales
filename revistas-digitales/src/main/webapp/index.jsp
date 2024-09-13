<%-- 
    Document   : index
    Created on : Sep 10, 2024, 11:18:44 AM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <jsp:include page="/Includes/resources.jsp"/></head>
<body>
    <div class="px-3 py-2 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-between">
                <!-- Logo alineado a la izquierda -->
                <a href="/" class="d-flex align-items-center text-white text-decoration-none">
                    <i class="bi bi-book" style="font-size: 40px;"></i>
                </a>

                <!-- Título centrado -->
                <h1 class="mx-auto text-center" style="font-size: 24px;">Revistas Digitales</h1>
            </div>
        </div>
    </div>
    
    <!-- Scriptlet para manejar los mensajes de advertencia -->
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

    <div class="container mt-4">
        <div class="row">
            <div class="col-md-12">
                <div class="p-4 bg-light rounded shadow-sm">
                    <h2 class="text-center">Bienvenido a Revistas Digitales</h2>
                    <p class="text-center mt-3">
                        Nuestra plataforma te permite acceder a una amplia variedad de revistas de diferentes categorías, 
                        interactuar con sus autores y otros lectores, además de poder adquirir espacios publicitarios para 
                        promocionar tus productos o servicios.
                    </p>
                </div>
            </div>
        </div>
    </div>

    
    <div class="container mt-4">
        <h2>Inicio de Sesion</h2>
        <form action="LoginServlet" method="POST">
            <div class="form-group mb-3">
                <label for="nombreLogin" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombreLogin" name="nombreLogin" placeholder="Nombre">
            </div>
            <div class="form-group mb-3">
                <label for="claveLogin" class="form-label">Clave</label>
                <input type="password" class="form-control" id="claveLogin" name="claveLogin" placeholder="Clave">
            </div>
            <button type="submit" class="btn btn-primary">Iniciar Sesion</button>
        </form>

        <h2 class="mt-4">Registrarse</h2>
        <form action="RegistroServlet" method="POST">
            <div class="form-group mb-3">
                <label for="nombreRegistro" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="nombreRegistro" name="nombreRegistro" placeholder="Nombre">
            </div>
            <div class="form-group mb-3">
                <label for="claveRegistro" class="form-label">Clave</label>
                <input type="password" class="form-control" id="claveRegistro" name="claveRegistro" placeholder="Clave">
            </div>
            <div class="form-group mb-3">
                <label for="rol" class="form-label">Rol dentro de la aplicación:</label>
                <select id="rol" name="rol" class="form-select">
                    <option value="EDITOR">EDITOR</option>
                    <option value="SUSCRIPTOR">SUSCRIPTOR</option>
                    <option value="ADMINISTRADOR">ADMINISTRADOR</option>
                    <option value="ANUNCIANTE">ANUNCIANTE</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Registrarse</button>
        </form>
    </div>

    <jsp:include page="/Includes/footer.jsp"/>
</body>
</html>


