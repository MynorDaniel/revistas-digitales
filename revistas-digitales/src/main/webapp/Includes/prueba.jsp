

    <h1>Bienvenido a la página de inicio</h1>

    <%
        // Obtener un atributo de sesión
        String nombreUsuario = (String) session.getAttribute("nombreAtributo");
        String rolUsuario = (String) session.getAttribute("rolAtributo");

        // Verificar si el atributo existe y no es nulo
        if (nombreUsuario != null && rolUsuario != null) {
            out.println("<p>Nombre: " + nombreUsuario + "</p>");
            out.println("<p>Rol: " + rolUsuario + "</p>");
        } else {
            out.println("<p>No hay datos de sesión disponibles.</p>");
        }
    %>


