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
public class Conciertos {
 
    private int idConcierto;
    private String titulo;
    private Date fechaInicio;
    private Date fechaFin;
    private int idOrquesta;
    private int idDirector;
    private int idPrograma;
    private int numObras;
    private int duracion;

    public Conciertos() {}

    public Conciertos(int idConciertos, String titulo, Date fechaInicio, Date fechaFin, int idOrquesta, 
                        int idDirector, int idPrograma, int numObras, int duracion) {
        this.idConcierto = idConciertos;
        this.titulo = titulo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idOrquesta = idOrquesta;
        this.idDirector = idDirector;
        this.idPrograma = idPrograma;
        this.numObras = numObras;
        this.duracion = duracion;
    }

    
    public int getIdConcierto() {
        return idConcierto;
    }

    public void setIdConcierto(int idConcierto) {
        this.idConcierto = idConcierto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getIdOrquesta() {
        return idOrquesta;
    }

    public void setIdOrquesta(int idOrquesta) {
        this.idOrquesta = idOrquesta;
    }

    public int getIdDirector() {
        return idDirector;
    }

    public void setIdDirector(int idDirector) {
        this.idDirector = idDirector;
    }

    public int getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(int idPrograma) {
        this.idPrograma = idPrograma;
    }

    public int getNumObras() {
        return numObras;
    }

    public void setNumObras(int numObras) {
        this.numObras = numObras;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        return "Concierto{" + "idConcierto = " + this.idConcierto + 
            ", titulo = " + this.titulo + 
            ", fechaInicio = " + sdf.format(this.fechaInicio) + 
            ", fechaFin = " + sdf.format(this.fechaFin) + 
            ", idOrquesta = " + this.idOrquesta + 
            ", idDirector = " + this.idDirector +
            ", idPrograma = " + this.idPrograma +
            ", numObras = " + this.numObras +
            ", duracion = " + this.duracion +
            '}';
    }
}
