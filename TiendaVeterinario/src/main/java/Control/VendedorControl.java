/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;
import Model.Vendedor;
import Negocio.VendedorNegocio;
import Vista.TablaVendedores;
import javax.swing.JOptionPane;
/**
 *
 * @author USUARIO
 */
public class VendedorControl {
    VendedorNegocio vendedorNegocio = new VendedorNegocio();
    public void registrarVendedor(String nombre, String identificacion, String genero){
        if ((!nombre.matches("[a-zA-Z ]*"))) {
            datosErroneos("El nombre sólo pueden contener letras");
            return;
        }
        
        if (!(genero.equals("M") || genero.equals("F"))) {
            datosErroneos("Por favor ingrese en el género \"F\" para femenino o \"M\" "
                    + "para masculino (sin comillas)");
            return;
        }
        
        

        if (nombre.equals("") || identificacion.equals("") || genero.equals("")  ) {
            JOptionPane.showMessageDialog(null, "Debe de completar todos los campos para poder almacenar un mascota, intentelo de nuevo.");
            return;
        } else {
            char generoChar = genero.charAt(0);
            
            Vendedor vendedor = new Vendedor(identificacion, nombre, generoChar );
            if (vendedorNegocio.registrarVendedor(vendedor) == true) {
                operacionExitosa("Se ha almacenado el vendedor");
            } 
            return;
        }
    }
    public void listarVendedor(){
        TablaVendedores ventana = new TablaVendedores();
        ventana.llenarTabla();
        ventana.setVisible(true);
    }
    
    
    
    
    
    public void datosErroneos(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
    }
    
    public void eliminarVendedor(String id){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        
        if(vendedorNegocio.eliminarVendedor(id)){
            operacionExitosa("Se ha eliminado la mascota");
        }else{
            JOptionPane.showMessageDialog(null, "Ningúna mascota coincide con el id ingresado",
                    "Mascota no encontrada", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void operacionExitosa(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
}
