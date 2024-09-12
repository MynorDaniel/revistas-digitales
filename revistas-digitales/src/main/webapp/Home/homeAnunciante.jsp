<%-- 
    Document   : homeAnunciante
    Created on : Sep 12, 2024, 12:30:55 AM
    Author     : mynordma
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Anunciante</title>
        <jsp:include page="/Includes/resources.jsp"/>
    </head>
    <body>
        
        <div class="px-3 py-2 bg-dark text-white">
          <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
              <a href="/" class="d-flex align-items-center my-2 my-lg-0 me-lg-auto text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
              </a>

              <ul class="nav col-12 col-lg-auto my-2 justify-content-center my-md-0 text-small">
                <li>
                  <a href="#" class="nav-link text-secondary">
                    <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#home"></use></svg>
                    Home
                  </a>
                </li>
                <li>
                  <a href="#" class="nav-link text-white">
                    <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#grid"></use></svg>
                    Comprar Anuncio
                  </a>
                </li>
                <li>
                  <a href="#" class="nav-link text-white">
                    <svg class="bi d-block mx-auto mb-1" width="24" height="24"><use xlink:href="#people-circle"></use></svg>
                    Perfil
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div class="px-3 py-2 border-bottom mb-3">
        </div>
  
        <div class="container">
            <!-- Título principal -->
            <h1 class="my-4">Bienvenido al Panel Principal</h1>

            <!-- Descripción -->
            <p>En esta sección, puedes gestionar tus anuncios.</p>

            <!-- Tabla de ejemplo -->
            <h2 class="my-4">Lista de Anuncios Comprados</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Tipo</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Vigencia</th>
                        <th scope="col">Fecha De Compra</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Txt</td>
                        <td>334</td>
                        <td>3 dias</td>
                        <td>12/12/1212</td>
                    </tr>
                   <tr>
                        <th scope="row">2</th>
                        <td>Txt-img</td>
                        <td>332</td>
                        <td>2 dias</td>
                        <td>12/12/1215</td>
                    </tr>
                </tbody>
            </table>
        </div>
</body>
</html>
