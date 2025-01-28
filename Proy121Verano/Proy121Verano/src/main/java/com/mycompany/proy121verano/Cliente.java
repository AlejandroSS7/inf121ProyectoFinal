/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proy121verano;

/**
 *
 * @author FRANKLIN
 */
class Cliente extends Persona {
    public Cliente(String nombre, String id) {
        super(nombre, id);
    }

    @Override
    public String obtenerTipo() {
        return "Cliente";
    }
}
