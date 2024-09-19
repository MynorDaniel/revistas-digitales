package com.mycompany.revistas.digitales.backend.anuncios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author mynordma
 */
public class Anuncio {
    
    private final int id;
    private final LocalDate fecha;
    private final String tipo;
    private final String vigencia;
    private final String texto;
    private final InputStream imagen; 
    private final InputStream video;  
    private final String estado;
    private final double precio;

    // Constructor
    public Anuncio(int id, LocalDate fecha, String tipo, String vigencia, String texto, InputStream imagen, InputStream video, String estado, double precio) {
        this.id = id;
        this.fecha = fecha;
        this.tipo = tipo;
        this.vigencia = vigencia;
        this.texto = texto;
        this.imagen = imagen;
        this.video = video;
        this.estado = estado;
        this.precio = precio;
    }
    
    public int getId(String criterio) {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getVigencia() {
        return vigencia;
    }

    public String getTexto() {
        return texto;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public InputStream getVideo() {
        return video;
    }

    public String getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public boolean guardarEnLaBD(String nombreUsuario) {
        String sql = "INSERT INTO anuncio (anunciante, fecha, tipo, vigencia, texto, imagen, video, estado, precio) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombreUsuario);
            pstmt.setString(2, fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            pstmt.setString(3, tipo);
            pstmt.setString(4, vigencia);
            pstmt.setString(5, texto);

            // Si la imagen no es nula, guardar el InputStream en la base de datos
            if (imagen != null) {
                pstmt.setBinaryStream(6, imagen); // Guardar imagen como InputStream
            } else {
                pstmt.setNull(6, java.sql.Types.BLOB);
            }

            // Si el video no es nulo, guardar el InputStream en la base de datos
            if (video != null) {
                pstmt.setBinaryStream(7, video); // Guardar video como InputStream
            } else {
                pstmt.setNull(7, java.sql.Types.BLOB);
            }

            pstmt.setString(8, estado);
            pstmt.setDouble(9, precio);

            pstmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Error al guardar anuncio: " + e.getMessage());
            return false;
        }
    }

    
    public static int getTotal() {
        String query = "SELECT COUNT(*) AS total FROM anuncio";
        int total = 0;
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                total = rs.getInt("total");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return total;
    }
    
    public static void desactivar(String id) {
        String sql = "DELETE FROM anuncio WHERE id = ?";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, id);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                System.out.println("Anuncio con ID " + id + " eliminado con éxito.");
            } else {
                System.out.println("No se encontró el anuncio con ID " + id + ".");
            }
            
        } catch (SQLException e) {
            System.out.println("Error al eliminar el anuncio: " + e.getMessage());
        }
    }
    
    public static ArrayList<Anuncio> obtenerAnuncios() {
        String sql = "SELECT * FROM anuncio ORDER BY RAND() LIMIT 8"; // Selecciona aleatoriamente hasta 8 anuncios
        ArrayList<Anuncio> anuncios = new ArrayList<>();

        try (Connection conexion = ConexionBD.obtenerConexion(); 
             PreparedStatement stmt = conexion.prepareStatement(sql); 
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String tipo = rs.getString("tipo");
                String vigencia = rs.getString("vigencia");
                String texto = rs.getString("texto");
                InputStream imagen = rs.getBinaryStream("imagen");
                InputStream video = rs.getBinaryStream("video");
                String estado = rs.getString("estado");
                double precio = rs.getDouble("precio");

                Anuncio anuncio = new Anuncio(id, fecha, tipo, vigencia, texto, imagen, video, estado, precio);
                anuncios.add(anuncio);
            }

        } catch (SQLException e) {
            System.out.println("Error en obtener anuncios");
        }

        return anuncios;
    }
    
    public static ArrayList<Anuncio> obtenerAnunciosCaducados() {
        ArrayList<Anuncio> anunciosCaducados = new ArrayList<>();
        String sql = "SELECT * FROM anuncio";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            LocalDate today = LocalDate.now();
            
            while (rs.next()) {
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String vigencia = rs.getString("vigencia");
                
                long vigenciaDias = 0;
                switch (vigencia) {
                    case "1 dia":
                        vigenciaDias = 1;
                        break;
                    case "3 dias":
                        vigenciaDias = 3;
                        break;
                    case "1 semana":
                        vigenciaDias = 7;
                        break;
                    case "2 semanas":
                        vigenciaDias = 14;
                        break;
                }
                
                LocalDate fechaExpiracion = fecha.plusDays(vigenciaDias);
                
                if (today.isAfter(fechaExpiracion)) {
                    int id = rs.getInt("id");
                    String tipo = rs.getString("tipo");
                    String texto = rs.getString("texto");
                    InputStream imagen = rs.getBinaryStream("imagen");
                    InputStream video = rs.getBinaryStream("video");
                    String estado = rs.getString("estado");
                    double precio = rs.getDouble("precio");
                    
                    Anuncio anuncio = new Anuncio(id, fecha, tipo, vigencia, texto, imagen, video, estado, precio);
                    anunciosCaducados.add(anuncio);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en get anuncios caducados");
        }

        return anunciosCaducados;
    }

}
