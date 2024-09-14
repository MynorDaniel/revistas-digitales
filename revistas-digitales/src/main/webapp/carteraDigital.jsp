<%-- 
    Document   : carteraDigital
    Created on : Sep 13, 2024, 1:56:13 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cartera Digital</title>
        <jsp:include page="/Includes/resources.jsp"/>
        <script>
            function validateInput() {
                var input = document.getElementById('montoRecarga');
                var button = document.getElementById('recargarBtn');
                var value = input.value;

                // Expresión regular para validar números con hasta dos decimales
                var regex = /^[0-9]+(\.[0-9]{1,2})?$/;

                // Verificar si el valor cumple con el formato y es mayor a cero
                if (regex.test(value) && parseFloat(value) > 0) {
                    button.disabled = false;
                } else {
                    button.disabled = true;
                }
            }
        </script>
    </head>
    <body>
        <div class="px-3 py-2 bg-dark text-white">
            <div class="container">
                <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                    <!-- Logo alineado a la izquierda -->
                    <a href="/" class="d-flex align-items-center text-white text-decoration-none">
                        <i class="bi bi-book" style="font-size: 40px;"></i>
                    </a>

                    <!-- Título centrado -->
                    <h1 class="mx-auto text-center" style="font-size: 24px;">REVISTAS DIGITALES</h1>

                </div>
            </div>
        </div>
        <div class="container mt-4">
            <h1>Cartera Digital</h1>
            
            <br>
            <h2>Recargar Saldo</h2>
            <form action="${pageContext.request.contextPath}/CarteraDigitalServlet" method="POST">
                <div class="input-group mb-3">
                    <span class="input-group-text">$</span>
                    <span class="input-group-text">0.00</span>
                    <input name="monto" type="text" id="montoRecarga" class="form-control" aria-label="Dollar amount (with dot and two decimal places)" oninput="validateInput()">
                </div>
                <button type="submit" class="btn btn-primary" id="recargarBtn" disabled>Recargar</button>
            </form>
            <br>
            <div>
                <label for="saldo">Saldo Actual:</label>
                <span id="saldo"><%= session.getAttribute("saldoAtributo") %></span>
            </div>
        </div>
    </body>
</html>
