/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.Cliente;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ClienteDAO {
    public boolean registrarCliente(Cliente cliente);
    public Cliente consultarCliente(String identificacion);
    public boolean eliminarCliente(String identificacion);
    public List<Cliente> listarCliente();
    public boolean actualizarCliente(Cliente cliente);
    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
    
    
}
