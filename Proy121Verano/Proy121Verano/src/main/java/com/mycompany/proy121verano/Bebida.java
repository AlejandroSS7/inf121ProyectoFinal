/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proy121verano;

/**
 *
 * @author FRANKLIN
 */
class Bebida extends Plato {
    public Bebida(String nombre, double precio) {
        super(nombre, precio);
    }

    @Override
    public String obtenerCategoria() {
        return "Bebida";
    }
}
