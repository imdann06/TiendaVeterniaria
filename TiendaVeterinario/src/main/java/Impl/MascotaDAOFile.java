/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

/**
 *
 * @author diego.valle1
 */
import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import Model.MascotaDTO;
import java.util.ArrayList;
import Dao.MascotaDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

public class MascotaDAOFile implements MascotaDAO {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "mascotaDTO.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoMascotas;

    public MascotaDAOFile() {
        archivoMascotas = new File(FILE_NAME);
    }
//Organizar el orden de los parámetros
    @Override
    public boolean almacenarMascotas(MascotaDTO mascota) {
        StringBuilder sb = new StringBuilder();
        sb.append(mascota.getId());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getRaza());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getEdad());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getNombre());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getPeso());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getFechaIngreso());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getLugarOrigen());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getGenero());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getPrecio());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(mascota.getTipo());
        sb.append(DELIMITADOR_ARCHIVO);
        
        

        try { 

            escritorArchivo = new FileWriter(archivoMascotas, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);
            escritorBuffer.write(sb.toString());
            escritorBuffer.newLine();
            escritorBuffer.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error");
        }
        return false;
    }

    @Override
    public MascotaDTO consultarMascotas(String id) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoMascotas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(id)) {
                    String parametros[] = linea.split(",");
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.US);
                    try{
                    Date fechaIngreso = formatoFecha.parse(parametros[5]);
                    return (new MascotaDTO(parametros[0], parametros[1],
                        Integer.parseInt(parametros[2]), parametros[3], Double.parseDouble(parametros[4]), fechaIngreso, parametros[6], parametros[7].charAt(0), Double.parseDouble(parametros[8]), parametros[9]));
                    }catch(ParseException e){
                        System.out.println("Formato Fecha Incorrecto: "+parametros[5]);
                        return null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<MascotaDTO> listarMascotas() {
        archivoMascotas = new File(FILE_NAME);
        String linea;
        List<MascotaDTO> mascotas = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoMascotas);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String parametros[] = linea.split(",");
                SimpleDateFormat formatoFecha = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", java.util.Locale.US);
                try{
                    Date fechaIngreso = formatoFecha.parse(parametros[5]);
                mascotas.add(new MascotaDTO(parametros[0], parametros[1],
                        Integer.parseInt(parametros[2]), parametros[3], Double.parseDouble(parametros[4]), fechaIngreso, parametros[6], parametros[7].charAt(0), Double.parseDouble(parametros[8]), parametros[9]));
                }catch(ParseException e){
                    System.out.println("Error con la fecha: "+ parametros[5]);
                    return null;
                    }
            }
            return mascotas;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean eliminarMascotas(String id) {
        String linea;
        archivoMascotas = new File(FILE_NAME);
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoMascotas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            escritorArchivo = new FileWriter(archivoTemporal, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(id)) {
                    System.out.println("coincide");
                    escritorBuffer.write("");
                } else {
                    escritorBuffer.write(linea);
                    escritorBuffer.newLine();
                }
            }
            escritorBuffer.close();
            lectorBuffer.close();

            escritorBuffer = new BufferedWriter(new FileWriter(archivoMascotas));
            lectorBuffer = new BufferedReader(new FileReader(archivoTemporal));
            while ((linea = lectorBuffer.readLine()) != null) {
                escritorBuffer.write(linea);
                escritorBuffer.newLine();
            }
            escritorBuffer.close();
            lectorBuffer.close();
            System.out.println(archivoTemporal.delete());
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (null != lectorArchivo) {
                    lectorArchivo.close();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        }
        return false;
    }

    @Override
    public boolean actualizarMascotas(MascotaDTO mascota) {
        archivoMascotas = new File(FILE_NAME);
        String linea;
        try {
            lectorArchivo = new FileReader(archivoMascotas);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(mascota.getId())) {
                    lectorBuffer.close();
                    eliminarMascotas(mascota.getId());
                    almacenarMascotas(mascota);
                    return true;
                }
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    

}
