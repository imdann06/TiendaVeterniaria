/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Dosis {
    private Date fecha;
    private double cantidad;
    private Vacuna vacuna;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Vacuna getVacuna() {
        return vacuna;
    }

    public void setVacuna(Vacuna vacuna) {
        this.vacuna = vacuna;
    }

    public Dosis() {
    }

    public Dosis(Date fecha, double cantidad, Vacuna vacuna) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.vacuna = vacuna;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dosis{");
        sb.append("fecha=").append(fecha);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", vacuna=").append(vacuna);
        sb.append('}');
        return sb.toString();
    }
    
}
