<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        <jsp:include page="/Includes/headerSuscriptor.jsp"/>
        
        <!-- Barra lateral izquierda y derecha con anuncios -->
        <div class="container-fluid">
            <div class="row">
                <!-- Barra lateral izquierda -->
                <div class="col-md-3 bg-light p-3">
                    <jsp:include page="/Includes/anunciosIzquierda.jsp"/>
                </div>

                <!-- Contenido principal -->
                <div class="col-md-6">
                    <br>
                    
                    <!-- Formulario de búsqueda -->
                    <form action="${pageContext.request.contextPath}/BuscarServlet" method="GET" class="d-flex align-items-center">
                        <input class="form-control me-2" type="search" name="query" placeholder="Buscar revista" aria-label="Buscar">
                        <select class="form-select me-2" name="filtro">
                            <option value="">Buscar por categoría</option>
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
                        <div class="input-group me-2">
                            <input id="tagInput" class="form-control" type="text" placeholder="Seleccionar tags">
                            <button type="button" class="btn btn-outline-secondary" onclick="addTag()">Añadir</button>
                        </div>
                        <div id="selectedTags" class="d-flex flex-wrap mt-2"></div>
                        <input type="hidden" name="tags" value="">
                        <button class="btn btn-outline-dark" type="submit">Buscar</button>
                    </form>
                    
                    <br>
                    <!-- Etiquetas de ejemplo -->
                    <div class="tags-container">
                        <span class="badge bg-secondary me-2">Arte</span>
                        <span class="badge bg-secondary me-2">Ciencia</span>
                        <span class="badge bg-secondary me-2">Cine</span>
                        <span class="badge bg-secondary me-2">Cultura</span>
                        <span class="badge bg-secondary me-2">Deportes</span>
                        <span class="badge bg-secondary me-2">Economia</span>
                        <span class="badge bg-secondary me-2">Educacion</span>
                        <span class="badge bg-secondary me-2">Entretenimiento</span>
                        <span class="badge bg-secondary me-2">Gastronomia</span>
                        <span class="badge bg-secondary me-2">Historia</span>
                        <span class="badge bg-secondary me-2">Literatura</span>
                        <span class="badge bg-secondary me-2">Medio Ambiente</span>
                        <span class="badge bg-secondary me-2">Moda</span>
                        <span class="badge bg-secondary me-2">Musica</span>
                        <span class="badge bg-secondary me-2">Politica</span>
                        <span class="badge bg-secondary me-2">Psicologia</span>
                        <span class="badge bg-secondary me-2">Salud</span>
                        <span class="badge bg-secondary me-2">Sociologia</span>
                        <span class="badge bg-secondary me-2">Tecnologia</span>
                        <span class="badge bg-secondary me-2">Viajes</span>
                    </div>
                    
                    <hr>
                    <jsp:include page="/Includes/mostrarRevistas.jsp"/>
                </div>

                <!-- Barra lateral derecha -->
                <div class="col-md-3 bg-light p-3">
                    <jsp:include page="/Includes/anunciosDerecha.jsp"/>
                </div>
            </div>
        </div>

        <jsp:include page="/Includes/footer.jsp"/>
        
        <script>
            let tags = []; // Array para almacenar tags seleccionados

            function addTag() {
                const tagInput = document.getElementById('tagInput');
                const tagValue = tagInput.value.trim();

                if (tagValue && !tags.includes(tagValue)) {
                    tags.push(tagValue);
                    renderTags();
                    tagInput.value = '';
                }
            }

            function removeTag(tag) {
                tags = tags.filter(t => t !== tag);
                renderTags();
            }

            function renderTags() {
                const selectedTagsContainer = document.getElementById('selectedTags');
                selectedTagsContainer.innerHTML = '';

                tags.forEach(tag => {
                    const tagElement = document.createElement('div');
                    tagElement.className = 'badge bg-secondary me-2 mb-2';
                    tagElement.textContent = tag;

                    const removeButton = document.createElement('button');
                    removeButton.className = 'btn btn-sm btn-light ms-2';
                    removeButton.innerHTML = '&times;';
                    removeButton.onclick = () => removeTag(tag);

                    tagElement.appendChild(removeButton);
                    selectedTagsContainer.appendChild(tagElement);
                });

                document.querySelector('input[name="tags"]').value = tags.join(',');
            }
        </script>

    </body>
</html>
