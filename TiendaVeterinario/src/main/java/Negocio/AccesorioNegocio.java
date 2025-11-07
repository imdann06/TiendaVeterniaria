/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import java.util.List;
import Dao.AccesorioDAO;
import Impl.AccesorioDAOFile;
import Model.Accesorio;
/**
 *
 * @author HP
 */
public class AccesorioNegocio {
    private AccesorioDAO accesorioDAO = new AccesorioDAOFile();

    public AccesorioNegocio() {
    }

    // Registrar un nuevo accesorio
    public boolean registrarAccesorio(Accesorio accesorio) {
        // Regla de negocio: evitar duplicados por código o ID
        if (consultarAccesorio(accesorio.getId()) == null) {
            accesorioDAO.registrarAccesorio(accesorio);
            return true;
        }
        return false;
    }

    // Listar todos los accesorios disponibles
    public List<Accesorio> listarAccesorios() {
        return accesorioDAO.listarAccesorios();
    }

    // Consultar un accesorio específico
    public Accesorio consultarAccesorio(String codigo) {
        return accesorioDAO.consultarAccesorio(codigo);
    }

    // Eliminar un accesorio si existe
    public boolean eliminarAccesorio(String codigo) {
        if (consultarAccesorio(codigo) != null) {
            accesorioDAO.venderAccesorio(codigo);
            return true;
        }
        return false;
    }

    }


