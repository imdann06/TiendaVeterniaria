/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao;

import Model.Cliente;
import Model.Vendedor;
import java.util.List;

/**
 *
 * @author HP
 */
public interface VendedorDAO {
    public boolean RegistrarVendedor(Vendedor vendedor);
    public Vendedor LoguearVendedor(String identificacion);
    public List<Vendedor> ListarVendedor();
    public boolean EliminarVendedor(String id);
    public Vendedor consultarVendedor(String identificacion);

    @Override
    public String toString();

    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();
}