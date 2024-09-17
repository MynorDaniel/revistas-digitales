<%-- 
    Document   : editarRevista
    Created on : Sep 16, 2024, 10:14:51 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/Includes/headerEditor.jsp"/>
        
        <div class="container mt-4">
            <!-- Formulario -->
            <form method="POST" action="${pageContext.request.contextPath}/EditarRevistaServlet" enctype="multipart/form-data">
                <h1>Editar Revista</h1>
                
                <!-- Campo para subir PDF -->
                <div class="form-group mb-3">
                    <label for="pdf">Subir Nueva Version</label>
                    <input type="file" id="pdf" name="pdf" class="form-control" accept=".pdf">
                </div>
                
                <!-- Select para bloquear interacciones -->
                <div class="form-group mb-3">
                    <label for="bloqueo">Bloquear interacciones</label>
                    <select id="bloqueo" name="bloqueo" class="form-select" required>
                        <option value="NO" selected>NO</option>
                        <option value="SI">SI</option>
                    </select>
                </div>
                
                <!-- Botón de envío -->
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
            </form>
            
        </div>

        <jsp:include page="/Includes/footer.jsp"/>
    </body>
</html>
