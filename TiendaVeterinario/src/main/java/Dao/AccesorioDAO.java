/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;
import java.util.List;
import Model.Accesorio;
/**
 *
 * @author HP
 */
public interface AccesorioDAO {
    public boolean registrarAccesorio(Accesorio accesorio);
    public Accesorio consultarAccesorio(String id);
    //seria mejor con un int, numero especifico por accesoriorio, ej "collar de perro rojo es el 00010"
    public List<Accesorio> listarAccesorios(); //que hay en el stock 
	//public boolean eliminarEstudiante(String identificacion);
	//public boolean actualizarEstudiante(EstudianteDTO estudiante);
    //elimiar accesorio, por tipo?, nombre?, id? y no seria eliminar, sino vender,
    public boolean venderAccesorio(String id);

    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
}
