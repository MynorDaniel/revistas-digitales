/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.revistas.digitales.backend;

import java.util.Base64;

/**
 *
 * @author mynordma
 */
public class Encriptacion {
    
    private final String palabra;

    public Encriptacion(String palabra) {
        this.palabra = palabra;
    }
    
    public String encriptar(){
        // Convertir la palabra en bytes
        byte[] bytes = palabra.getBytes();
        // Encriptar usando Base64
        String palabraEncriptada = Base64.getEncoder().encodeToString(bytes);
        return palabraEncriptada;
    }
}
