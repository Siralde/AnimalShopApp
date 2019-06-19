/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uv.bd.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ajgs
 */
public class Salas {

  
    private int codigo; 
    private String nombreSala;
    private int capacidad;

    public Salas() {}

    public Salas(int codigo, String nombreSala, int capacidad) {
        this.codigo = codigo;
        this.nombreSala = nombreSala;
        this.capacidad = capacidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        return "Sala{" + "codigo = " + this.codigo + 
            ", nombre sala = " + this.nombreSala + 
            ", capacidad = " + this.capacidad + '}';
    }
}
