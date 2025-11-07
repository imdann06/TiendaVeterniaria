/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;

import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.AccesorioDAO;
import Model.Accesorio;

/**
 *
 * @author HP
 */
public class AccesorioDAOFile implements AccesorioDAO {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "accesorio.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoAccesorio;

    public AccesorioDAOFile() {
        archivoAccesorio = new File(FILE_NAME);
    }

    @Override
    public boolean registrarAccesorio(Accesorio accesorio) {
        StringBuilder sb = new StringBuilder();
        sb.append(accesorio.getId());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(accesorio.getPrecio());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(accesorio.getDescripcion());
        sb.append(DELIMITADOR_ARCHIVO);

        try {

            escritorArchivo = new FileWriter(archivoAccesorio, true);
            escritorBuffer = new BufferedWriter(escritorArchivo);
            escritorBuffer.write(sb.toString());
            escritorBuffer.newLine();
            escritorBuffer.close();
            return true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
        return false;

    }

    @Override
    public Accesorio consultarAccesorio(String id) {
        String linea;
        try {
            lectorArchivo = new FileReader(archivoAccesorio);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(id)) {
                    String parametros[] = linea.split(",");
                    return (new Accesorio(parametros[0], // Descripción
                            Double.parseDouble(parametros[1]), // Precio
                            parametros[2]));                   // ID;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Accesorio> listarAccesorios() {
        archivoAccesorio = new File(FILE_NAME);
        String linea;
        List<Accesorio> mascota = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoAccesorio);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String parametros[] = linea.split(",");
                mascota.add(new Accesorio(parametros[0], // Descripción
                            Double.parseDouble(parametros[1]), // Precio
                        parametros[2]));       // id;
            }
            return mascota;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean venderAccesorio(String id) {
    File archivoAccesorios = new File(FILE_NAME);
    File archivoTemporal = new File("temporal.txt");
    String linea;
    boolean vendido = false;

    try (
        BufferedReader lectorBuffer = new BufferedReader(new FileReader(archivoAccesorios));
        BufferedWriter escritorBuffer = new BufferedWriter(new FileWriter(archivoTemporal))
    ) {
        while ((linea = lectorBuffer.readLine()) != null) {
            String[] parametros = linea.split(",");
            if (!parametros[0].equals(id)) {
                escritorBuffer.write(linea);
                escritorBuffer.newLine();
            } else {
                System.out.println("Accesorio vendido: " + parametros[1]);
                vendido = true;
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }

    // Reemplazamos el archivo original con el temporal
    if (archivoAccesorios.delete() && archivoTemporal.renameTo(archivoAccesorios)) {
        if (vendido) {
            System.out.println("Archivo actualizado correctamente.");
        } else {
            System.out.println("No se encontró el accesorio con ID: " + id);
        }
        return vendido;
    } else {
        System.out.println("No se pudo actualizar el archivo de accesorios.");
        return false;
    }
}
    }
