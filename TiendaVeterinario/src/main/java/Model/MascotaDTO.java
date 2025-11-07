/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author diego.valle1
 */

import java.util.Date;

import java.util.Date;

/**
 *
 * @author diego.valle1
 */
public class MascotaDTO {
    private String id;
    private String raza;
    private int edad;
    private String nombre;
    private double peso;
    private Date fechaIngreso;
    private String lugarOrigen;
    private char genero;
    private double precio;
    private String tipo;

    public MascotaDTO() {
    }

    public MascotaDTO(String id, String raza, int edad, String nombre, double peso, Date fechaIngreso, String lugarOrigen, char genero, double precio, String tipo) {
        this.id = id;
        this.raza = raza;
        this.edad = edad;
        this.nombre = nombre;
        this.peso = peso;
        this.fechaIngreso = fechaIngreso;
        this.lugarOrigen = lugarOrigen;
        this.genero = genero;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngrso(Date fechaIngrso) {
        this.fechaIngreso = fechaIngrso;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MascotaDTO{");
        sb.append("id=").append(id);
        sb.append(", raza=").append(raza);
        sb.append(", edad=").append(edad);
        sb.append(", nombre=").append(nombre);
        sb.append(", peso=").append(peso);
        sb.append(", fechaIngrso=").append(fechaIngreso);
        sb.append(", lugarOrigen=").append(lugarOrigen);
        sb.append(", genero=").append(genero);
        sb.append(", precio=").append(precio);
        sb.append(", tipo=").append(tipo);
        sb.append('}');
        return sb.toString();
    }
    
    
            
}
