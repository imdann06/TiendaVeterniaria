/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

/**
 *
 * @author USUARIO
 */
import java.util.List;

import Dao.MascotaDAO;
import Impl.MascotaDAOFile;
import Model.MascotaDTO;

public class MascotaNegocio {

    private MascotaDAO mascotaDAO = new MascotaDAOFile();
    
    public MascotaNegocio(){
        
    }

    public boolean almacenarAnimal(MascotaDTO animal) {
        //Validaciones de negocio y se ejecutarán los casos de uso de la aplicación
        if(consultarAnimal(animal.getId())== null){
            mascotaDAO.almacenarMascotas(animal);
            return true;
        }
        return false;
    }

    public List<MascotaDTO> listarAnimals() {
        return mascotaDAO.listarMascotas();
    }
    
    public MascotaDTO consultarAnimal(String id){
        return mascotaDAO.consultarMascotas(id);
    }
    
    public boolean eliminarAnimal(String id){
        if(consultarAnimal(id) != null){
            mascotaDAO.eliminarMascotas(id);
            return true;
        }
        return false;
    }
    
    public boolean actualizarAnimal(MascotaDTO animal){
        if(consultarAnimal(animal.getId()) != null){
            return mascotaDAO.actualizarMascotas(animal);
        }
        return false;
    }


}

