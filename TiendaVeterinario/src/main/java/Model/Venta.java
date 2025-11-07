package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venta {
    private Date fecha;
    private double valorVenta;
    private List<Detalle> detalles = new ArrayList<>();
    private Vendedor vendedor;
    private Cliente cliente;
    private MascotaDTO mascota;  

    
    public Venta() {
    }

    public Venta(Date fecha, double valorVenta) {
        this.fecha = fecha;
        this.valorVenta = valorVenta;
    }

    public Venta(Date fecha, double valorVenta, Vendedor vendedor, Cliente cliente) {
        this.fecha = fecha;
        this.valorVenta = valorVenta;
        this.vendedor = vendedor;
        this.cliente = cliente;
    }

    
    public Venta(Date fecha, double valorVenta, Vendedor vendedor, Cliente cliente, MascotaDTO mascota) {
        this.fecha = fecha;
        this.valorVenta = valorVenta;
        this.vendedor = vendedor;
        this.cliente = cliente;
        this.mascota = mascota;
    }

   
    public MascotaDTO getMascota() {
        return mascota;
    }

    public void setMascota(MascotaDTO mascota) {
        this.mascota = mascota;
    }

    
    public void agregarDetalle(Detalle detalle) {
        detalles.add(detalle);
    }

    public List<Detalle> getDetalles() {
        return detalles;
    }
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta{");
        sb.append("fecha=").append(fecha);
        sb.append(", valorVenta=").append(valorVenta);
        sb.append(", detalles=").append(detalles);
        sb.append(", vendedor=").append(vendedor);
        sb.append(", cliente=").append(cliente);
        sb.append(", mascota=").append(mascota != null ? mascota.getId() : "null");
        sb.append('}');
        return sb.toString();
    }
}