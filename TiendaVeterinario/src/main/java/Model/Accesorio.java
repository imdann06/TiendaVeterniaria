/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Accesorio {
    private String id;
    private double precio;
    private String descripcion;

    public Accesorio(String id, double precio, String descripcion) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
    }

    public Accesorio() {
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.descripcion = Descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Accesorio{");
        sb.append("id=").append(id);
        sb.append(", precio=").append(precio);
        sb.append(", descripcion=").append(descripcion);
        sb.append('}');
        return sb.toString();
    }
    
}