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
    </head>
    <body>
        <h2>Inicio de Sesion</h2>
        <form>
            <input id="nombreLogin" name="nombreLogin" placeholder="Nombre"/>
            <br>
            <input id="claveLogin" name="claveLogin" placeholder="Clave"/>
            <br>
            <button>Iniciar Sesion</button>
        </form>
        <h2>Registrarse</h2>
        <form action="RegistroServlet" method="POST">
            <input id="nombreRegistro" name="nombreRegistro" placeholder="Nombre"/>
            <br>
            <input id="claveRegistro" name="claveRegistro" placeholder="Clave"/>
            <br>
            <label for="rolRegistro">Rol dentro de la aplicacion:</label>
            <br>
            <select id="rol" name="rol">
                <option value="EDITOR">EDITOR</option>
                <option value="SUSCRIPTOR">SUSCRIPTOR</option>
                <option value="ADMINISTRADOR">ADMINISTRADOR</option>
            </select>
            <br>
            <button>Registrarse</button>
        </form>
    </body>
</html>
