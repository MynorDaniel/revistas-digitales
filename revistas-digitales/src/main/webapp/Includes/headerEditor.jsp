<%-- 
    Document   : headerEditor
    Created on : Sep 15, 2024, 6:56:12 PM
    Author     : mynordma
--%>

<div class="px-3 py-2 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <!-- Logo alineado a la izquierda -->
            <a href="/" class="d-flex align-items-center text-white text-decoration-none">
                <i class="bi bi-book" style="font-size: 40px;"></i>
            </a>

            <!-- T�tulo centrado -->
            <h1 class="mx-auto text-center" style="font-size: 24px;">REVISTAS DIGITALES</h1>

            <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                <li>
                    <a href="${pageContext.request.contextPath}/Home/homeEditor.jsp" class="nav-link text-secondary">
                        <i class="bi bi-house-door-fill d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Home
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Editor/publicar.jsp" class="nav-link text-white">
                        <i class="bi bi-file-earmark-richtext-fill d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Publicar Revista
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/ReporteServlet" class="nav-link text-white">
                        <i class="bi bi-table d-block mx-auto mb-1" style="font-size: 24px;"></i>
                        Reportes
                    </a>
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

