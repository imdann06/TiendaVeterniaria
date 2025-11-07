/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.Vacuna;
import java.util.List;

/**
 *
 * @author HP
 */
public interface VacunaDAO {
    public boolean AlmacenarVacuna(Vacuna vacuna);
    public List<Vacuna> ListarVacuna();
    public boolean VenderVacuna(String tipo);
    public Vacuna ConsultarVacuna(String tipo);
    
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    
}
