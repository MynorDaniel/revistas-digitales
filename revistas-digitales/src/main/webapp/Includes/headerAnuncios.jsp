<%-- 
    Document   : headerAnuncios
    Created on : Sep 12, 2024, 7:25:17 PM
    Author     : mynordma
--%>

<div class="px-3 py-2 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <!-- Logo alineado a la izquierda -->
            <a href="/" class="d-flex align-items-center text-white text-decoration-none">
                <i class="bi bi-book" style="font-size: 40px;"></i>
            </a>

            <!-- Título centrado -->
            <h1 class="mx-auto text-center" style="font-size: 24px;">REVISTAS DIGITALES</h1>

            <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                <li>
                    <a href="${pageContext.request.contextPath}/Home/homeAnunciante.jsp" class="nav-link text-secondary">
                        <i class="bi bi-house-door-fill d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Home
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Anunciante/comprarAnuncio.jsp" class="nav-link text-white">
                        <i class="bi bi-grid-fill d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Comprar Anuncio
                    </a>
                </li>
                <li>
                    <a href="#" class="nav-link text-white">
                        <i class="bi bi-person-circle d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Perfil
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>
