<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reporte de Suscripciones</title>
</head>
<body>
    <h1>Reporte de Suscripciones</h1>
    <div>
        <!-- Incluir el reporte generado en formato HTML -->
        <iframe src="<%= request.getAttribute("reportePath") %>" style="width: 100%; height: 600px; border: none;">

        </iframe>
    </div>
</body>
</html>
