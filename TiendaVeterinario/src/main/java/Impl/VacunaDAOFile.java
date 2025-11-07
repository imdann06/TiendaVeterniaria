/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import Model.Vacuna;
import java.util.ArrayList;
import Dao.VacunaDAO;
import java.util.Date;
/**
 *
 * @author HP
 */
public class VacunaDAOFile implements VacunaDAO{
    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "vacuna.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoVacuna;
    public VacunaDAOFile() {
        archivoVacuna = new File(FILE_NAME);
    }
    @Override 
    public boolean AlmacenarVacuna(Vacuna vacuna){
        StringBuilder sb = new StringBuilder();
        sb.append(vacuna.getTipo());
        sb.append(DELIMITADOR_ARCHIVO);
        try {

            escritorArchivo = new FileWriter(archivoVacuna, true);
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
    public List<Vacuna> ListarVacuna(){
        archivoVacuna = new File(FILE_NAME);
        String linea;
        List<Vacuna> vacuna = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoVacuna);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String parametros[] = linea.split(",");
                vacuna.add(new Vacuna(parametros[0]));
            }
            return vacuna;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean VenderVacuna(String tipo){
    File archivoVacunas = new File(FILE_NAME);
    File archivoTemporal = new File("temporal.txt");
    String linea;
    boolean vendida = false;

    try (
        BufferedReader lectorBuffer = new BufferedReader(new FileReader(archivoVacunas));
        BufferedWriter escritorBuffer = new BufferedWriter(new FileWriter(archivoTemporal))
    ) {
        while ((linea = lectorBuffer.readLine()) != null) {
            // Si la línea no coincide con el tipo, la copiamos
            if (!linea.equalsIgnoreCase(tipo)) {
                escritorBuffer.write(linea);
                escritorBuffer.newLine();
            } else {
                System.out.println(" Vacuna vendida: " + linea);
                vendida = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    // Reemplazamos el archivo original con el temporal
    if (archivoVacunas.delete() && archivoTemporal.renameTo(archivoVacunas)) {
        if (vendida) {
            System.out.println(" Archivo de vacunas actualizado correctamente.");
        } else {
            System.out.println("️ No se encontró la vacuna con tipo: " + tipo);
        }
        return vendida;
    } else {
        System.out.println(" No se pudo actualizar el archivo de vacunas.");
        return false;
    }
}
    @Override
    public Vacuna ConsultarVacuna(String tipo){
        String linea;
        try {
            lectorArchivo = new FileReader(archivoVacuna);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(tipo)) {
                    String parametros[] = linea.split(",");
                    return (new Vacuna(parametros[0])); // tipo 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
