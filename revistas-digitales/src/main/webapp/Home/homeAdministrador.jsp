<%-- 
    Document   : homeAdministrador
    Created on : Sep 12, 2024, 12:30:38 AM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/Includes/headerAdmin.jsp"/>
        
        <div class="container mt-4">
        <div class="row">
            <div class="col-md-12">
                <div class="p-4 bg-light rounded shadow-sm">
                    <h2 class="text-center">Panel Administrativo</h2>
                    <p class="text-center mt-3">
                        En esta seccion puedes administrar los precios de los tipos de anuncio, las revistas, ver las ganancias del negocio, etc.
                    </p>
                </div>
            </div>
        </div>
    </div>
        
        <jsp:include page="/Includes/footer.jsp"/>
    </body>
</html>
