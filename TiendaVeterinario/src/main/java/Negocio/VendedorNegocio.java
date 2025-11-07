/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;
import java.util.List;
import Dao.VendedorDAO;
import Impl.VendedorDAOFile;
import Model.Vendedor;

/**
 *
 * @author HP
 */
public class VendedorNegocio {
    private VendedorDAO vendedorDAO = new VendedorDAOFile();

    public VendedorNegocio() {
    }

    // Registrar un nuevo vendedor
 public boolean registrarVendedor(Vendedor vendedor) {
        vendedorDAO.RegistrarVendedor(vendedor);
        return true;
 }

    // Listar todos los vendedores
    public List<Vendedor> listarVendedores() {
        return vendedorDAO.ListarVendedor();
    }

    // Eliminar un vendedor
 public boolean eliminarVendedor(String id) {
        return vendedorDAO.EliminarVendedor(id);
    }
 public Vendedor consultarVendedor(String identificacion) {
        return vendedorDAO.consultarVendedor(identificacion);
    }
}
