<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Publicar Revista</title>
    <jsp:include page="/Includes/resources.jsp"/>
</head>
<body>
    <jsp:include page="/Includes/headerEditor.jsp"/>
    <br>
    <div class="container">
        <form method="POST" action="${pageContext.request.contextPath}/RevistaServlet">
            <h1>Nueva Revista</h1>
            
            <div class="form-group">
                <label for="nombre">Nombre</label>
                <input name="nombre" type="text" class="form-control" id="nombre" placeholder="Nombre de la revista" oninput="verificarCampos();">
            </div>
            <br>
            <div class="form-group">
                <label for="desc">Descripción</label>
                <input name="desc" type="text" class="form-control" id="desc" placeholder="Descripción" oninput="verificarCampos();">
            </div>
            <br>
            <div class="form-group">
                <label for="cat">Categoría</label>
                <input name="cat" type="text" class="form-control" id="cat" placeholder="Categoría" oninput="verificarCampos();">
            </div>
            <br>
            <label for="tagSelect">Tags</label>
            <select name="tagSelect" id="tagSelect" class="form-select" onchange="agregarTag()">
                <option value="" disabled selected>Selecciona un tag</option>
                <option value="Arte">Arte</option>
                <option value="Ciencia">Ciencia</option>
                <option value="Cine">Cine</option>
                <option value="Cultura">Cultura</option>
                <option value="Deportes">Deportes</option>
                <option value="Economia">Economía</option>
                <option value="Educacion">Educación</option>
                <option value="Entretenimiento">Entretenimiento</option>
                <option value="Gastronomia">Gastronomía</option>
                <option value="Historia">Historia</option>
                <option value="Literatura">Literatura</option>
                <option value="Medio Ambiente">Medio Ambiente</option>
                <option value="Moda">Moda</option>
                <option value="Musica">Música</option>
                <option value="Politica">Política</option>
                <option value="Psicologia">Psicología</option>
                <option value="Salud">Salud</option>
                <option value="Sociologia">Sociología</option>
                <option value="Tecnologia">Tecnología</option>
                <option value="Viajes">Viajes</option>


            </select>
            <br>
            <div class="mt-4">
                <label for="tagsContainer">Tags seleccionados:</label>
                <div id="tagsContainer"></div>
            </div>
            <br>
            
            <!-- Campo oculto para enviar los tags seleccionados -->
            <input type="hidden" id="tagsOcultos" name="tags" value="">

            <div class="form-group mb-3">
                <label for="fecha">Fecha</label>
                <input type="date" id="fecha" name="fecha" class="form-control" onchange="verificarCampos();"/>
            </div>
            <br>
            <button type="submit" class="btn btn-primary mt-3" disabled>Confirmar</button>
        </form>
    </div>

    <jsp:include page="/Includes/footer.jsp"/>
    
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            let tagsSeleccionados = [];

            function agregarTag() {
                const select = document.getElementById('tagSelect');
                const selectedTag = select.value;
                const tagsContainer = document.getElementById('tagsContainer');

                if (selectedTag && !tagsSeleccionados.includes(selectedTag)) {
                    // Agregar el tag a la lista
                    tagsSeleccionados.push(selectedTag);

                    // Crear un elemento span para el tag
                    const tagElement = document.createElement('span');
                    tagElement.className = 'badge bg-primary me-2';
                    tagElement.id = selectedTag;
                    tagElement.innerHTML = selectedTag + ' <button type="button" class="btn-close btn-close-white ms-2" aria-label="Remove" onclick="eliminarTag(\'' + selectedTag + '\')"></button>';
                    tagsContainer.appendChild(tagElement);

                    // Actualizar el campo oculto con los tags seleccionados
                    actualizarCampoTags();
                }
            }

            function eliminarTag(tagId) {
                const tagElement = document.getElementById(tagId);
                if (tagElement) {
                    tagElement.remove();

                    // Remover el tag de la lista
                    tagsSeleccionados = tagsSeleccionados.filter(t => t !== tagId);

                    // Actualizar el campo oculto con los tags seleccionados
                    actualizarCampoTags();
                }
            }

            function actualizarCampoTags() {
                const campoOculto = document.getElementById('tagsOcultos');
                campoOculto.value = tagsSeleccionados.join(',');
                verificarCampos();
            }

            window.agregarTag = agregarTag;
            window.eliminarTag = eliminarTag;

        });

        function verificarCampos() {
            var nombre = document.getElementById("nombre").value;
            var desc = document.getElementById("desc").value;
            var cat = document.getElementById("cat").value;
            var fecha = document.getElementById("fecha").value;
            var tags = document.getElementById("tagsOcultos").value;

            var botonSubmit = document.querySelector("button[type='submit']");

            // Deshabilitar el botón si falta algún campo
            botonSubmit.disabled = !(nombre && desc && cat && fecha && tags);
        }
    </script>
</body>
</html>
