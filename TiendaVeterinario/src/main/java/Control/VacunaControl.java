/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Vacuna;
import Model.Vacuna;
import Negocio.VacunaNegocio;
import Vista.TablaVacunas;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class VacunaControl {

    VacunaNegocio vacunaNegocio = new VacunaNegocio();

    public void almacenarVacuna(String tipo) {
        if ((!tipo.matches("[a-zA-Z ]*"))) {
            datosErroneos("El nombre sólo pueden contener letras");
            return;
        }

        if (tipo.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "Debe completar todos los campos para poder almacenar una vacuna, inténtelo de nuevo.");
            return;
        } else {

            Vacuna vacuna = new Vacuna(tipo);
            if (vacunaNegocio.almacenarVacuna(vacuna)) {
                operacionExitosa("Se ha almacenado el vacuna");
            }

        }
    }
    
    public void listarVacuna(){
        TablaVacunas ventana = new TablaVacunas();
        ventana.llenarTabla();
        ventana.setVisible(true);
    }
    
    public void consultarVacuna(String tipo){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        
        if(tipo.equals("")){
            JOptionPane.showMessageDialog(null,"Debe de ingresar una vacuna.");
            return;
        }
        Vacuna vacuna = vacunaNegocio.consultarVacuna(tipo);
        if(vacuna == null){
            operacionExitosa("El tipo de vacuna ingresado no coincide con ninguno de "
                    + "las vacunas registradas");
        }else {
            operacionExitosa("Datos clienteles:\n\n" + vacuna.toString());
        }
    }
    
    public void datosErroneos(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Datos incorrectos", JOptionPane.ERROR_MESSAGE);
    }

    public void operacionExitosa(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
    }
}
