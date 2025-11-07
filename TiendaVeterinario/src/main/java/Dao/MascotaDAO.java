/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;
import Model.MascotaDTO;
import java.util.List;

/**
 *
 * @author diego.valle1
 */
public interface MascotaDAO {
   public boolean almacenarMascotas(MascotaDTO mascota);
	public MascotaDTO consultarMascotas(String id);
	public List<MascotaDTO> listarMascotas();
	public boolean eliminarMascotas(String id);
	public boolean actualizarMascotas(MascotaDTO mascota);

    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
        
	

 
}
