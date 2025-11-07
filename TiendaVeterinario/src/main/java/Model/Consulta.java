/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Date;
/**
 *
 * @author diego.valle1
 */
public class Consulta {
    private Date fecha;
    private String sintomas;
    private String tratamientos ;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Consulta{");
        sb.append("fecha=").append(fecha);
        sb.append(", sintomas=").append(sintomas);
        sb.append(", tratamientos=").append(tratamientos);
        sb.append('}');
        return sb.toString();
    }

    public Consulta() {
    }

    public Consulta(Date fecha, String sintomas, String tratamientos) {
        this.fecha = fecha;
        this.sintomas = sintomas;
        this.tratamientos = tratamientos;
    }
    
}
