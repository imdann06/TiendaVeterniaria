/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author USUARIO
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HP
 */
public class Detalle {
    int cantidad;
    Accesorio accesorio;
    Venta venta;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Accesorio getAccesorio() {
        return accesorio;
    }

    public void setAccesorio(Accesorio accesorio) {
        this.accesorio = accesorio;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Detalle() {
    }

    public Detalle(int cantidad, Accesorio accesorio, Venta venta) {
        this.cantidad = cantidad;
        this.accesorio = accesorio;
        this.venta = venta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Detalle{");
        sb.append("cantidad=").append(cantidad);
        sb.append(", accesorio=").append(accesorio);
        sb.append(", venta=").append(venta);
        sb.append('}');
        return sb.toString();
    }
    
}
