/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Vacuna {
    String tipo;
    private List<Dosis> dosisAplicadas = new ArrayList<>();

    public void agregarDosis(Dosis dosis) {
        dosisAplicadas.add(dosis);
    }

    public List<Dosis> getDosisAplicadas() {
        return dosisAplicadas;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Vacuna(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vacuna{");
        sb.append("tipo=").append(tipo);
        sb.append('}');
        return sb.toString();
    }
    
}
