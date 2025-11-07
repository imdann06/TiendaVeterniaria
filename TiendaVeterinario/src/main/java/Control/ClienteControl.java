/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Model.Cliente;
import Model.MascotaDTO;
import Negocio.ClienteNegocio;
import Negocio.MascotaNegocio;

import Vista.TablaClientes;
import java.text.ParseException;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class ClienteControl {
    ClienteNegocio clienteNegocio = new ClienteNegocio();
    
    
    public void registrarCliente(String identificacion, String nombre, String direccion, String contacto){
        if ((!nombre.matches("[a-zA-Z ]*"))) {
            datosErroneos("El nombre sólo pueden contener letras");
            return;
        }
        if ((!direccion.matches("[a-zA-Z ]*"))) {
            datosErroneos("La raza sólo pueden contener letras");
            return;
        }
        if ((!contacto.matches("[a-zA-Z ]*"))) {
            datosErroneos("El tipo sólo pueden contener letras");
            return;
        }
        
        

        if (nombre.equals("") || identificacion.equals("") || direccion.equals("") || contacto.equals("") ) {
            JOptionPane.showMessageDialog(null, "Debe de completar todos los campos para poder almacenar un mascota, intentelo de nuevo.");
            return;
        } else {
            
            Cliente cliente = new Cliente(identificacion, nombre, direccion, contacto );
            if (clienteNegocio.registrarCliente(cliente) == true) {
                operacionExitosa("Se ha almacenado el cliente");
            } 
            return;
        }
    }
    
public void actualizarCliente(String identificacion, String nombre, String direccion, String contacto ) {
        
        if ((!nombre.matches("[a-zA-Z ]*"))) {
            datosErroneos("El nombre sólo puede contener letras");
            return;
        }
        if ((!direccion.matches("[a-zA-Z ]*"))) {
            datosErroneos("La dirección sólo puede contener letras");
            return;
        }
        if ((!contacto.matches("[a-zA-Z ]*"))) {
            datosErroneos("El contacto sólo puede contener letras");
            return;
        }
        
        if (!identificacion.matches("[0-9]*")) {
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }
        if (nombre.equals("") || direccion.equals("") || contacto.equals("") || identificacion.equals("") ) {
            JOptionPane.showMessageDialog(null, "Debe de completar todos los campos para poder almacenar un mascota, intentelo de nuevo.");
            return;
        } else {
            
            Cliente cliente = new Cliente(identificacion, nombre, direccion, contacto);
            if (clienteNegocio.actualizarCliente(cliente)) {
                operacionExitosa("Se ha actualizado la mascota");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar el cliente\n"
                        + "Por favor verifique se el cliente se encuentra registrada");
            }
        }
    }
public void registrarClienteConMascota(String identificacion, String nombre, String direccion, String contacto, String idMascota) {
    
    if ((!nombre.matches("[a-zA-Z ]*"))) {
        datosErroneos("El nombre sólo pueden contener letras");
        return;
    }
    if (nombre.equals("") || identificacion.equals("") || direccion.equals("") || contacto.equals("") || idMascota.equals("")) {
        JOptionPane.showMessageDialog(null, "Debe completar todos los campos incluyendo ID de mascota");
        return;
    }

    
    MascotaNegocio mascotaNegocio = new MascotaNegocio();
    MascotaDTO mascota = mascotaNegocio.consultarAnimal(idMascota);
    if (mascota == null) {
        JOptionPane.showMessageDialog(null, "La mascota con ID " + idMascota + " no existe");
        return;
    }

    
    Cliente cliente = clienteNegocio.consultarCliente(identificacion);
    
    if (cliente == null) {
        
        cliente = new Cliente(identificacion, nombre, direccion, contacto);
        cliente.agregarMascota(mascota);
        
        if (clienteNegocio.registrarCliente(cliente)) {
            operacionExitosa("Cliente registrado con mascota exitosamente");
        }
    } else {
        
        cliente.agregarMascota(mascota);
        if (clienteNegocio.actualizarCliente(cliente)) {
            operacionExitosa("Mascota agregada al cliente existente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar cliente");
        }
    }
}
    public void consultarCliente(String id){
        
        if(!id.matches("[0-9]*")){
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }
        if(id.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de ingresar un documento.");
            return;
        }
        Cliente cliente = clienteNegocio.consultarCliente(id);
        if(cliente == null){
            operacionExitosa("El número de id ingresado no coincide con ninguno de "
                    + "los id registrados");
        }else {
            operacionExitosa("Datos clienteles:\n\n" + cliente.toString());
        }
    }
    public void eliminarCliente(String id){
        
        if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        
        if(clienteNegocio.eliminarCliente(id)){
            operacionExitosa("Se ha eliminado el cliente");
        }else{
            JOptionPane.showMessageDialog(null, "Ningúna mascota coincide con el id ingresado",
                    "Cliente no encontrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void listarClientes(){
        TablaClientes ventana = new TablaClientes();
        ventana.llenarTabla();
        ventana.setVisible(true);
    }
    public void datosErroneos(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
    }

    public void operacionExitosa(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
    public void agregarMascotaACliente(String idCliente, String idMascota) {
    
    Cliente cliente = clienteNegocio.consultarCliente(idCliente);
    if (cliente == null) {
        JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
   
    MascotaNegocio mascotaNegocio = new MascotaNegocio();
    MascotaDTO mascota = mascotaNegocio.consultarAnimal(idMascota);
    if (mascota == null) {
        JOptionPane.showMessageDialog(null, "Mascota no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    
    cliente.agregarMascota(mascota);
    if (clienteNegocio.actualizarCliente(cliente)) {
        JOptionPane.showMessageDialog(null, "Mascota agregada al cliente exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Error al agregar mascota al cliente", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public void listarMascotasDeCliente(String idCliente) {
    Cliente cliente = clienteNegocio.consultarCliente(idCliente);
    if (cliente == null) {
        JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    if (cliente.getMascotas().isEmpty()) {
        JOptionPane.showMessageDialog(null, "El cliente no tiene mascotas registradas", "Información", JOptionPane.INFORMATION_MESSAGE);
    } else {
        StringBuilder sb = new StringBuilder();
        sb.append("Mascotas del cliente ").append(cliente.getNombres()).append(":\n\n");
        for (MascotaDTO mascota : cliente.getMascotas()) {
            sb.append("- ").append(mascota.getNombre()).append(" (").append(mascota.getRaza()).append(")\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Mascotas del Cliente", JOptionPane.INFORMATION_MESSAGE);
    }
}
public void buscarClientePorMascota(String idMascota) {
    // Validar que la mascota existe
    MascotaNegocio mascotaNegocio = new MascotaNegocio();
    MascotaDTO mascota = mascotaNegocio.consultarAnimal(idMascota);
    
    if (mascota == null) {
        JOptionPane.showMessageDialog(null, "La mascota con ID " + idMascota + " no existe", 
                                    "Mascota no encontrada", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    
    List<Cliente> clientes = clienteNegocio.listarClientes(); 
    Cliente clienteConMascota = null;
    
    for (Cliente cliente : clientes) {
        if (cliente.getMascotas() != null) {
            for (MascotaDTO mascotaCliente : cliente.getMascotas()) {
                if (mascotaCliente.getId().equals(idMascota)) {
                    clienteConMascota = cliente;
                    break;
                }
            }
        }
        if (clienteConMascota != null) break;
    }
    
    // Mostrar resultado
    if (clienteConMascota != null) {
        StringBuilder sb = new StringBuilder();
        sb.append("🐕 Mascota encontrada:\n");
        sb.append("ID: ").append(mascota.getId()).append("\n");
        sb.append("Nombre: ").append(mascota.getNombre()).append("\n");
        sb.append("Raza: ").append(mascota.getRaza()).append("\n\n");
        
        sb.append("👤 Dueño:\n");
        sb.append("Nombre: ").append(clienteConMascota.getNombres()).append("\n");
        sb.append("Documento: ").append(clienteConMascota.getIdentificacion()).append("\n");
        sb.append("Teléfono: ").append(clienteConMascota.getNumeroContacto());
        
        JOptionPane.showMessageDialog(null, sb.toString(), 
                                    "Dueño de la Mascota", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, 
            "La mascota con ID " + idMascota + " no tiene dueño registrado",
            "Mascota sin dueño", JOptionPane.INFORMATION_MESSAGE);
    }
}
}
