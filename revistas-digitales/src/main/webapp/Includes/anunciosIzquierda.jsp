<%@page import="java.io.InputStream"%>
<%@page import="com.mycompany.revistas.digitales.backend.anuncios.Anuncio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Anuncios Izquierda</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="row">
            <jsp:useBean id="anunciosMostrarAtributo" type="java.util.ArrayList" scope="request"/>
            <%
                int size = anunciosMostrarAtributo.size();
                for (int i = 0; i < size-4; i++) {
                    Anuncio anuncio = (Anuncio) anunciosMostrarAtributo.get(i);
            %>
                <div class="col-md-12 mb-3"> <!-- Tarjeta más ancha ocupando toda la fila -->
                    <div class="card h-100" style="min-height: 200px;"> <!-- Altura mínima personalizada -->
                        <div class="card-body">
                            <!-- Mostrar imagen, video o solo texto -->
                            <%
                                InputStream imagenStream = anuncio.getImagen();
                                InputStream videoStream = anuncio.getVideo();
                                
                                // Verificación del video
                                if (videoStream != null && videoStream.available() > 0) {
                                    byte[] videoBytes = new byte[videoStream.available()];
                                    videoStream.read(videoBytes);
                                    String videoBase64 = java.util.Base64.getEncoder().encodeToString(videoBytes);
                            %>
                                    <!-- Video del anuncio -->
                                    <video class="card-img-top" controls style="max-height: 250px;">
                                        <source src="data:video/mp4;base64,<%= videoBase64 %>" type="video/mp4">
                                        Tu navegador no soporta el video.
                                    </video>
                            <%
                                // Verificación de la imagen
                                } else if (imagenStream != null && imagenStream.available() > 0) {
                                    byte[] imagenBytes = new byte[imagenStream.available()];
                                    imagenStream.read(imagenBytes);
                                    String imagenBase64 = java.util.Base64.getEncoder().encodeToString(imagenBytes);
                            %>
                                    <!-- Imagen del anuncio -->
                                    <img src="data:image/jpeg;base64,<%= imagenBase64 %>" class="card-img-top" alt="Imagen del anuncio" style="max-height: 250px;">
                            <%
                                } else {
                            %>
                                    <!-- Solo texto si no hay imagen ni video -->
                                    <p class="card-text"><%= anuncio.getTexto() %></p>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>
            <%
                }
            %>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html>
