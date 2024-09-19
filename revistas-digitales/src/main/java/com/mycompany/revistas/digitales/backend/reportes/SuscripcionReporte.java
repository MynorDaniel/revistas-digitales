/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend.reportes;

import java.util.Date;

/**
 *
 * @author mynordma
 */
public class SuscripcionReporte {
    private String nombre;
    private String revista;
    private Date fecha;

    public SuscripcionReporte(String nombre, String revista, java.util.Date fecha) {
        this.nombre = nombre;
        this.revista = revista;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
