/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proy121verano;

/**
 *
 * @author FRANKLIN
 */
class Empleado extends Persona {
    private double salario;

    public Empleado(String nombre, String id, double salario) {
        super(nombre, id);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String obtenerTipo() {
        return "Empleado";
    }

    public double calcularSalarioConBonos(double bonos) {
        return salario + bonos;
    }
}
