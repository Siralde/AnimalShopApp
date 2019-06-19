package es.uv.bd.model;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author diaz
 */
public class Mascota {

    private int idMascota;
    private int idCliente;
    private String nombreMascota;
    private int tipoAnimal;
    private Date fechaNacimiento;

    public Mascota() {}

    public Mascota(int idMascota, int idCliente, String nombreMascota, int tipoAnimal, Date fechaNacimiento) {
        this.idMascota = idMascota;
        this.idCliente = idCliente;
        this.nombreMascota = nombreMascota;
        this.tipoAnimal = tipoAnimal;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(int tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        return "Mascota{" + "idMascota=" + idMascota + 
            ", idCliente=" + idCliente + 
            ", nombreMascota=" + nombreMascota + 
            ", tipoAnimal=" + tipoAnimal + 
            ", fechaNacimiento=" + sdf.format(fechaNacimiento) + '}';
    }

}
