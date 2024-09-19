<%-- 
    Document   : headerAdmin
    Created on : Sep 15, 2024, 4:32:43 PM
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
                    <a href="${pageContext.request.contextPath}/Home/homeAdministrador.jsp" class="nav-link text-secondary">
                        <i class="bi bi-house-door-fill d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Home
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/TipoAnuncioServlet" class="nav-link text-white">
                        <i class="bi bi-grid-fill d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Administrar Anuncios
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Admin/revistasAdmin.jsp" class="nav-link text-white">
                        <i class="bi bi-file-earmark-richtext-fill d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Administrar Revistas
                    </a>
                </li>
                <li>
    <form action="${pageContext.request.contextPath}/ReporteServlet" method="POST">
        <button type="submit" class="btn btn-link nav-link text-white" style="padding: 0; border: none; background: none;">
            <i class="bi bi-table d-block mx-auto mb-1" style="font-size: 24px;"></i>
            Reportes
        </button>
    </form>
</li>

                <li>
                    <a href="${pageContext.request.contextPath}/PerfilServlet" class="nav-link text-white">
                        <i class="bi bi-person-circle d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Perfil
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/LogOutServlet" class="nav-link text-white">
                        <i class="bi bi-box-arrow-right d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Cerrar Sesion
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>

