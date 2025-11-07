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
import Dao.ClienteDAO;
import Impl.ClienteDAOFile;
import Model.Cliente;
/**
 *
 * @author HP
 */
public class ClienteNegocio {


    private ClienteDAO clienteDAO = new ClienteDAOFile();

    public ClienteNegocio() {
    }

    // Registrar un nuevo cliente
    public boolean registrarCliente(Cliente cliente) {
        // Regla de negocio: no se puede registrar un cliente duplicado
        if (consultarCliente(cliente.getIdentificacion()) == null) {
            clienteDAO.registrarCliente(cliente);
            return true;
        }
        return false;
    }

    // Listar todos los clientes registrados
    public List<Cliente> listarClientes() {
        return clienteDAO.listarCliente();
    }

    // Consultar un cliente por su documento
    public Cliente consultarCliente(String identificacion) {
        return clienteDAO.consultarCliente(identificacion);
    }

    // Eliminar cliente si existe
    public boolean eliminarCliente(String identificacion) {
        if (consultarCliente(identificacion) != null) {
            clienteDAO.eliminarCliente(identificacion);
            return true;
        }
        return false;
    }

    // Actualizar la información de un cliente existente
    public boolean actualizarCliente(Cliente cliente) {
        if (consultarCliente(cliente.getIdentificacion()) != null) {
            return clienteDAO.actualizarCliente(cliente);
        }
        return false;
    }
}


