<%@page import="com.mycompany.revistas.digitales.backend.editor.Revista"%>
<%@page import="java.util.ArrayList"%>
<div class="container">
            <div class="row">
                <%
                    ArrayList<Revista> revistas = (ArrayList<Revista>) request.getAttribute("revistasBuscadasAtributo");

                    if (revistas != null && !revistas.isEmpty()) {
                        for (Revista revista : revistas) {
                %>
                            <div class="col-lg-4 col-md-6 mb-4">
                                <div class="card bg-dark text-white h-100">
                                    <div class="card-body">
                                        <h5 class="card-title"><%= revista.getNombre() %></h5>
                                        <p class="card-text">
                                            Fecha de publicación: <%= revista.getFecha() %><br>
                                            <%= revista.getDescripcion() != null ? revista.getDescripcion() : "Sin descripción" %>
                                        </p>
                                    </div>
                                    <div class="card-footer">
                                        <form action="${pageContext.request.contextPath}/RevistaServlet" method="GET">
                                            <input type="hidden" name="revistaName" value="<%= revista.getNombre() %>"/>
                                            <input class="btn btn-primary" type="submit" value="Ver más"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                <%
                        }
                    } else {
                %>
                        <div class="col-12">
                            <p class="text-center">No hay Revistas disponibles</p>
                        </div>
                <%
                    }
                %>
            </div>
        </div>