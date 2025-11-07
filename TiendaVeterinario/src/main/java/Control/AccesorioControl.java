/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Accesorio;
import Model.Accesorio;

import Negocio.AccesorioNegocio;
import Negocio.AccesorioNegocio;
import Vista.TablaAccesorios;


import java.text.ParseException;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author USUARIO
 */

public class AccesorioControl {

    AccesorioNegocio accesorioNegocio = new AccesorioNegocio();

    public void registrarAccesorio(String id, String precio, String descripcion) {
        if ((!descripcion.matches("[a-zA-Z ]*"))) {
            datosErroneos("La descipción sólo pueden contener letras");
            return;
        }
        
        

        if (descripcion.equals("") || precio.equals("") || id.equals("")) {
    JOptionPane.showMessageDialog(null, 
        "Debe completar todos los campos para poder almacenar un accesorio, inténtelo de nuevo.");
    return;
} else {
    try {
        double precioDouble = Double.valueOf(precio);

        Accesorio accesorio = new Accesorio(id, precioDouble, descripcion);
        if (accesorioNegocio.registrarAccesorio(accesorio)) {
            operacionExitosa("Se ha almacenado el accesorio");
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, 
            "El valor del precio no es válido. Ingrese un número, por ejemplo: 15.5");
    }
}
    }
    public void consultarAccesorio(String id){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!id.matches("[0-9]*")){
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }
        if(id.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de ingresar un documento.");
            return;
        }
        Accesorio accesorio = accesorioNegocio.consultarAccesorio(id);
        if(accesorio == null){
            operacionExitosa("El número de id ingresado no coincide con ninguno de "
                    + "los id registrados");
        }else {
            operacionExitosa("Datos clienteles:\n\n" + accesorio.toString());
        }
    }
    public void listarAccesorio(){
        TablaAccesorios ventana = new TablaAccesorios();
        ventana.llenarTabla();
        ventana.setVisible(true);
    }
    
    
    public void datosErroneos(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
    }

    public void operacionExitosa(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
}
