/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Impl;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import Model.Vendedor;
import java.util.ArrayList;
import Dao.VendedorDAO;
import java.util.Date;
/**
 *
 * @author HP
 */
public class VendedorDAOFile implements VendedorDAO{
    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "vendedor.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoVendedor;
    public VendedorDAOFile() {
        archivoVendedor = new File(FILE_NAME);
    }
    @Override
    public boolean RegistrarVendedor(Vendedor vendedor){
        StringBuilder sb = new StringBuilder();
        sb.append(vendedor.getNombres());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(vendedor.getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(vendedor.getGenero());
        sb.append(DELIMITADOR_ARCHIVO);
        try {

            escritorArchivo = new FileWriter(archivoVendedor, true);
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
    public Vendedor LoguearVendedor(String identificacion){
          String linea;

    try (
        BufferedReader lectorBuffer = new BufferedReader(new FileReader(archivoVendedor))
    ) {
        while ((linea = lectorBuffer.readLine()) != null) {
            String[] parametros = linea.split(DELIMITADOR_ARCHIVO);
           
             if (parametros.length >= 3 && parametros[1].equals(identificacion)) {
                char genero = parametros[2].isEmpty() ? ' ' : parametros[2].charAt(0);
                System.out.println(" Vendedor encontrado: " + parametros[0]);
                return new Vendedor(parametros[1], parametros[0], genero);
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al leer archivo de vendedores");
        e.printStackTrace();
    }

    System.out.println("️ No se encontró un vendedor con esa identificación.");
    return null;
    }
    @Override
    public boolean EliminarVendedor(String id){
        String linea;
        archivoVendedor = new File(FILE_NAME);
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoVendedor);
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

            escritorBuffer = new BufferedWriter(new FileWriter(archivoVendedor));
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
    public List<Vendedor> ListarVendedor(){
        archivoVendedor = new File(FILE_NAME);
        String linea;
        List<Vendedor> vendedor = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoVendedor);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                String parametros[] = linea.split(",");
                char genero = parametros[2].isEmpty() ? ' ' : parametros[2].charAt(0);
                vendedor.add(new Vendedor(parametros[1], parametros[0], genero));
            }
            return vendedor;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public Vendedor consultarVendedor(String identificacion){
        String linea;
        try {
            lectorArchivo = new FileReader(archivoVendedor);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(identificacion)) {
                    String parametros[] = linea.split(",");
                    return (new Vendedor(parametros[0], // identificación
                    parametros[1], // nombres
                    parametros[2].charAt(0) // dirección de contacto
                    ));  // número de contacto
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    }

