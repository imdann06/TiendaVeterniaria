/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.MascotaDTO;
import Negocio.MascotaNegocio;
import java.text.SimpleDateFormat;
import Vista.TablaMascotas;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.ParseException;
import Vista.TablaMascotas;

/**
 *
 * @author USUARIO
 */
public class MascotaControl {

    MascotaNegocio animalNegocio = new MascotaNegocio();

    public void almacenarAnimal(String id, String raza, String edad, String nombre, String peso, String fechaIngreso, String lugarOrigen, String genero, String precio, String tipo) {
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if ((!nombre.matches("[a-zA-Z ]*"))) {
            datosErroneos("El nombre sólo pueden contener letras");
            return;
        }
        if ((!raza.matches("[a-zA-Z ]*"))) {
            datosErroneos("La raza sólo pueden contener letras");
            return;
        }
        if ((!tipo.matches("[a-zA-Z ]*"))) {
            datosErroneos("El tipo sólo pueden contener letras");
            return;
        }
        if (!edad.matches("[0-9]*")) {
            datosErroneos("La edad debe ser un número sin puntos ni comas");
            return;
        }
        if (!precio.matches("[0-9]*")) {
            datosErroneos("El precio debe ser un número sin puntos ni comas");
            return;
        }
        if (!peso.matches("[0-9]*")) {
            datosErroneos("El peso debe ser un número sin puntos ni comas");
            return;
        }
        if ((!lugarOrigen.matches("[a-zA-Z ]*"))) {
            datosErroneos("El lugar de origen sólo pueden contener letras");
            return;
        }
        if (!(genero.equalsIgnoreCase("M") || genero.equalsIgnoreCase("F"))) {
            datosErroneos("Por favor ingrese en el género \"F\" para femenino o \"M\" "
                    + "para masculino (sin comillas)");
            return;
        }
        if (!id.matches("[0-9]*")) {
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }

        if (nombre.equals("") || raza.equals("") || edad.equals("") || id.equals("") || peso.equals("") || fechaIngreso.equals("") || lugarOrigen.equals("") || genero.equals("") || precio.equals("") || tipo.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe de completar todos los campos para poder almacenar un mascota, intentelo de nuevo.");
            return;
        } else {
            char generoChar = genero.charAt(0);
            Integer edadInt = Integer.valueOf(edad);
            Double pesoDouble = Double.valueOf(peso);
            Double precioDouble = Double.valueOf(precio);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            try{
            Date fechaIngresoString = formatoFecha.parse(fechaIngreso);
            MascotaDTO animal = new MascotaDTO(id, raza, edadInt, nombre, pesoDouble, fechaIngresoString, lugarOrigen, generoChar, precioDouble, tipo);
            if (animalNegocio.almacenarAnimal(animal) == true) {
                operacionExitosa("Se ha almacenado el mascota");
            } else {
                if (JOptionPane.showConfirmDialog(null, "EL animal ya se encuentra registrada, ¿desea"
                        + " actualizar la información con los datos ingresados?", "Mascota existente",
                        JOptionPane.YES_NO_OPTION) == 0) {
                    actualizarAnimal(id, raza, edad, nombre, peso, fechaIngreso, lugarOrigen, genero, precio, tipo);
                }
            }
            return;}catch(ParseException e){
                System.out.println("Formato de fecha incorrecto");
            }
        }
    }

    public void actualizarAnimal(String id, String raza, String edad, String nombre, String peso, String fechaIngreso, String lugarOrigen, String genero, String precio, String tipo) {
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if ((!nombre.matches("[a-zA-Z ]*"))) {
            datosErroneos("El nombre sólo puede contener letras");
            return;
        }
        if (!(genero.equals("M") || genero.equals("F"))) {
            datosErroneos("Por favor ingrese en el género \"F\" para femenino o \"M\" "
                    + "para masculino (sin comillas)");
            return;
        }
        if (!id.matches("[0-9]*")) {
            datosErroneos("El documento debe ser un número sin puntos ni comas");
            return;
        }
        if (nombre.equals("") || raza.equals("") || edad.equals("") || id.equals("") || peso.equals("") || fechaIngreso.equals("") || lugarOrigen.equals("") || genero.equals("") || precio.equals("") || tipo.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe de completar todos los campos para poder almacenar un mascota, intentelo de nuevo.");
            return;
        } else {
            char generoChar = genero.charAt(0);
            Integer edadInt = Integer.valueOf(edad);
            Double pesoDouble = Double.valueOf(peso);
            Double precioDouble = Double.valueOf(precio);
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            try{
            Date fechaIngresoString = formatoFecha.parse(fechaIngreso);
            MascotaDTO animal = new MascotaDTO(id, raza, edadInt, nombre, pesoDouble, fechaIngresoString, lugarOrigen, generoChar, precioDouble, tipo);
            if (animalNegocio.actualizarAnimal(animal)) {
                operacionExitosa("Se ha actualizado la mascota");
            } else {
                JOptionPane.showMessageDialog(null, "No se ha podido actualizar la mascota\n"
                        + "Por favor verifique si la mascota se encuentra registrada");
            }}catch(ParseException e){
                System.out.println("Formato de fecha incorrecto");
            }
        }
    }
    public void consultarAnimal(String id){
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
        MascotaDTO mascota = animalNegocio.consultarAnimal(id);
        if(mascota == null){
            operacionExitosa("El número de id ingresado no coincide con ninguno de "
                    + "los id registrados");
        }else {
            operacionExitosa("Datos animalles:\n\n" + mascota.toString());
        }
    }
    public void eliminarAnimal(String id){
        //Matches verifica que el sting contenga solo los caracteres indicados,
        //por ejemplo letras, números o espacios en blanco
        if(!id.matches("[0-9]*")){
            datosErroneos("El id debe ser un número sin puntos ni comas");
            return;
        }
        
        if(animalNegocio.eliminarAnimal(id)){
            operacionExitosa("Se ha eliminado la mascota");
        }else{
            JOptionPane.showMessageDialog(null, "Ningúna mascota coincide con el id ingresado",
                    "Mascota no encontrada", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void listarMascotas(){
        TablaMascotas ventana = new TablaMascotas();
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
