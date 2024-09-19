/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.reportes;

import com.mycompany.revistas.digitales.backend.bd.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mynordma
 */
public class Reporte {
    
    public List<SuscripcionReporte> getSuscripciones() {
        List<SuscripcionReporte> suscripciones = new ArrayList<>();
        
        String sql = "SELECT revista_nombre, usuario_nombre, fecha FROM suscripcion";
        
        try (Connection conexion = ConexionBD.obtenerConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                String revista = rs.getString("revista_nombre");
                String usuario = rs.getString("usuario_nombre");
                java.sql.Date fecha = rs.getDate("fecha");
                
                SuscripcionReporte suscripcion = new SuscripcionReporte(usuario, revista, new java.util.Date(fecha.getTime()));
                
                suscripciones.add(suscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return suscripciones;
    }
    
    public List<Ganancia> getGanancia() {
        List<Ganancia> ganancias = new ArrayList<>();

        String monto = "0";
        String total = "0";

        try (Connection con = ConexionBD.obtenerConexion()) {

            String sqlPagos = "SELECT SUM(monto) FROM pago";
            try (PreparedStatement stmtPagos = con.prepareStatement(sqlPagos);
                 ResultSet rsPagos = stmtPagos.executeQuery()) {
                
                if (rsPagos.next()) {
                    monto = rsPagos.getString(1);
                }
            }

            String sqlAnuncios = "SELECT COUNT(*) FROM anuncio";
            try (PreparedStatement stmtAnuncios = con.prepareStatement(sqlAnuncios);
                 ResultSet rsAnuncios = stmtAnuncios.executeQuery()) {

                if (rsAnuncios.next()) {
                    total = rsAnuncios.getString(1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ganancias.add(new Ganancia(monto, total));

        return ganancias;
    }
}
