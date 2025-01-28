/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proy121verano;

import java.sql.Connection; // Importar clases necesarias
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author FRANKLIN
 */
class Pedido {

    private Cliente cliente;
    private Plato[] platos;
    private int cantidadPlatos;

    public Pedido(Cliente cliente, int maxPlatos) {
        this.cliente = cliente;
        this.platos = new Plato[maxPlatos];
        this.cantidadPlatos = 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Plato[] getPlatos() {
        return platos;
    }

    public void agregarPlato(Plato plato) {
        if (cantidadPlatos < platos.length) {
            platos[cantidadPlatos++] = plato;
        } else {
            System.out.println("No se pueden agregar más platos a este pedido.");
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < cantidadPlatos; i++) {
            total += platos[i].getPrecio();
        }
        return total;
    }
}
