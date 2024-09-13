<%-- 
    Document   : comprarAnuncio
    Created on : Sep 12, 2024, 2:55:37 PM
    Author     : mynordma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprar Anuncio</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/Includes/headerAnuncios.jsp"/>
        <div class="container mt-4">
            <h2>Comprar Anuncio</h2>
            <br>
            <form method="POST" id="anuncioForm" action="${pageContext.request.contextPath}/PagoServlet">

                <div class="form-group mb-3">
                    <label for="tipoAnuncio" class="form-label">Tipo</label>
                    <select id="tipoAnuncio" name="tipoAnuncio" class="form-select" onchange="mostrarOpciones(); verificarCampos();">
                        <option value="">Selecciona un tipo</option>
                        <option value="Txt">Texto</option>
                        <option value="Txt-Img">Texto e Imagen</option>
                        <option value="Video">Video</option>
                    </select>
                </div>
                
                <div class="form-group mb-3">
                    <label for="vigencia" class="form-label">Vigencia</label>
                    <select id="vigencia" name="vigencia" class="form-select" onchange="verificarCampos();">
                        <option value="">Selecciona la vigencia</option>
                        <option value="1 dia">1 Día</option>
                        <option value="3 dias">3 Días</option>
                        <option value="1 semana">1 Semana</option>
                        <option value="2 semanas">2 Semanas</option>
                    </select>
                </div>
                
                <div class="form-group mb-3">
                    <label for="fecha">Fecha</label>
                    <input type="date" id="fecha" name="fecha" class="form-control" onchange="verificarCampos();"/>
                </div>
                
                <!-- Opciones dinámicas de contenido -->
                <div id="opcionTexto" class="form-group mb-3">
                    <label for="textoAnuncio">Texto del Anuncio</label>
                    <textarea id="textoAnuncio" name="textoAnuncio" class="form-control" oninput="verificarCampos();"></textarea>
                </div>

                <div id="opcionImagen" class="form-group mb-3" style="display: none;">
                    <label for="imagenAnuncio" class="form-label">Seleccionar Imagen</label>
                    <input class="form-control" type="file" id="imagenAnuncio" name="imagenAnuncio" accept="image/*" onchange="verificarCampos();">
                </div>

                <div id="opcionVideo" class="form-group mb-3" style="display: none;">
                    <label for="videoAnuncio" class="form-label">Seleccionar Video</label>
                    <input class="form-control" type="file" id="videoAnuncio" name="videoAnuncio" accept="video/*" onchange="verificarCampos();">
                </div>
                
                <!-- Botón deshabilitado por defecto -->
                <button type="submit" class="btn btn-primary" id="pagarBtn" disabled>Pagar</button>
            </form>
        </div>
        <jsp:include page="/Includes/footer.jsp"/>
        
        <!-- Script para mostrar/ocultar elementos según la opción seleccionada -->
        <script>
            function mostrarOpciones() {
                var tipoAnuncio = document.getElementById("tipoAnuncio").value;
                
                // Ocultar todas las opciones inicialmente
                document.getElementById("opcionTexto").style.display = "none";
                document.getElementById("opcionImagen").style.display = "none";
                document.getElementById("opcionVideo").style.display = "none";
                
                // Mostrar las opciones según el tipo de anuncio
                if (tipoAnuncio === "Txt") {
                    document.getElementById("opcionTexto").style.display = "block";
                } else if (tipoAnuncio === "Txt-Img") {
                    document.getElementById("opcionTexto").style.display = "block";
                    document.getElementById("opcionImagen").style.display = "block";
                } else if (tipoAnuncio === "Video") {
                    document.getElementById("opcionVideo").style.display = "block";
                }
                verificarCampos(); // Verificar campos después de mostrar opciones
            }

            function verificarCampos() {
                var tipoAnuncio = document.getElementById("tipoAnuncio").value;
                var vigencia = document.getElementById("vigencia").value;
                var fecha = document.getElementById("fecha").value;
                var textoAnuncio = document.getElementById("textoAnuncio").value;
                var imagenAnuncio = document.getElementById("imagenAnuncio").value;
                var videoAnuncio = document.getElementById("videoAnuncio").value;

                // Deshabilitar el botón por defecto
                var botonPagar = document.getElementById("pagarBtn");
                botonPagar.disabled = true;
                
                // Condiciones para habilitar el botón "Pagar"
                if (tipoAnuncio && vigencia && fecha) {
                    if (tipoAnuncio === "Txt" && textoAnuncio) {
                        botonPagar.disabled = false;
                    } else if (tipoAnuncio === "Txt-Img" && textoAnuncio && imagenAnuncio) {
                        botonPagar.disabled = false;
                    } else if (tipoAnuncio === "Video" && textoAnuncio && videoAnuncio) {
                        botonPagar.disabled = false;
                    }
                }
            }
        </script>
    </body>
</html>


