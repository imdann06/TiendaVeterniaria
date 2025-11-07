/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import java.util.List;
import Dao.VacunaDAO;
import Impl.VacunaDAOFile;
import Model.Vacuna;
/**
 *
 * @author HP
 */
public class VacunaNegocio {
    private VacunaDAO vacunaDAO = new VacunaDAOFile();

    public VacunaNegocio() {
    }

    // Registrar una nueva vacuna
    public boolean almacenarVacuna(Vacuna vacuna) {
        // Regla de negocio: evitar duplicados por código o nombre
        if (consultarVacuna(vacuna.getTipo()) == null) {
            vacunaDAO.AlmacenarVacuna(vacuna);
            return true;
        }
        return false;
    }

    // Listar todas las vacunas registradas
    public List<Vacuna> listarVacunas() {
        return vacunaDAO.ListarVacuna();
    }

    // Consultar una vacuna por su código
    public Vacuna consultarVacuna(String tipo) {
        return vacunaDAO.ConsultarVacuna(tipo);
    }

    // Eliminar una vacuna si existe
    public boolean VenderVacuna(String tipo) {
        if (consultarVacuna(tipo) != null) {
            vacunaDAO.VenderVacuna(tipo);
            return true;
        }
        return false;
    }

}

