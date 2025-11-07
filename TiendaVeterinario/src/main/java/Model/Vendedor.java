/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author HP
 */
public class Vendedor {
    private String nombres;
    private String identificacion;
    private char genero;

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public Vendedor(String nombres, String identificacion, char genero) {
        this.nombres = nombres;
        this.identificacion = identificacion;
        this.genero = genero;
    }

    public Vendedor() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vendedor{");
        sb.append("nombres=").append(nombres);
        sb.append(", identificacion=").append(identificacion);
        sb.append(", genero=").append(genero);
        sb.append('}');
        return sb.toString();
    }
    
    
}
