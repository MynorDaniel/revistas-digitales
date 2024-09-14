package com.mycompany.revistas.digitales.backend.anuncios;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import jakarta.servlet.http.Part;
import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author mynordma
 */
public class Anuncio {
    private final LocalDate fecha;
    private final String tipo;
    private final String vigencia;
    private final String texto;
    private final byte[] imagen; 
    private final byte[] video;  
    private final String estado;
    private final double precio;

    // Constructor
    public Anuncio(LocalDate fecha, String tipo, String vigencia, String texto, 
        byte[] imagen, byte[] video, String estado, double precio) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.vigencia = vigencia;
        this.texto = texto;
        this.imagen = imagen;
        this.video = video;
        this.estado = estado;
        this.precio = precio;
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

    public byte[] getImagen() {
        return imagen;
    }

    public byte[] getVideo() {
        return video;
    }

    public String getEstado() {
        return estado;
    }

    public double getPrecio() {
        return precio;
    }
    
    
}
