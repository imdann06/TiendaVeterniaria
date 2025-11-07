package Impl;

import java.util.List;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import Dao.ClienteDAO;
import Model.Cliente;
import Model.MascotaDTO;
import java.util.Date;
import Dao.MascotaDAO;
import Impl.MascotaDAOFile;

public class ClienteDAOFile implements ClienteDAO{
    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String DELIMITADOR_MASCOTAS = ";";
    private static final String FILE_NAME = "cliente.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoCliente;

    public ClienteDAOFile() {
        archivoCliente = new File(FILE_NAME);
    }

    @Override
    public boolean registrarCliente(Cliente cliente){
        StringBuilder sb = new StringBuilder();
        sb.append(cliente.getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(cliente.getNombres());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(cliente.getDireccionContacto());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(cliente.getNumeroContacto());
        sb.append(DELIMITADOR_ARCHIVO);
        
        // Agregar IDs de mascotas
        if (!cliente.getMascotas().isEmpty()) {
            for (MascotaDTO mascota : cliente.getMascotas()) {
                sb.append(mascota.getId());
                sb.append(DELIMITADOR_MASCOTAS);
            }
            // Eliminar el último delimitador sobrante
            sb.deleteCharAt(sb.length() - 1);
        }

        try {
            escritorArchivo = new FileWriter(archivoCliente, true);
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
    public Cliente consultarCliente(String identificacion){
        String linea;
        try {
            lectorArchivo = new FileReader(archivoCliente);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(identificacion)) {
                    return construirClienteDesdeLinea(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // MÉTODO PARA CONSTRUIR CLIENTE DESDE LÍNEA DE ARCHIVO
    private Cliente construirClienteDesdeLinea(String linea) {
        String parametros[] = linea.split(DELIMITADOR_ARCHIVO);
        
        Cliente cliente = new Cliente(
            parametros[0], // identificación
            parametros[1], // nombres
            parametros[2], // dirección de contacto
            parametros[3]  // número de contacto
        );

        // Si hay mascotas en el archivo (parámetro 4 existe)
        if (parametros.length > 4 && !parametros[4].isEmpty()) {
            String[] idsMascotas = parametros[4].split(DELIMITADOR_MASCOTAS);
            MascotaDAO mascotaDAO = new MascotaDAOFile();
            
            for (String idMascota : idsMascotas) {
                MascotaDTO mascota = mascotaDAO.consultarMascotas(idMascota);
                if (mascota != null) {
                    cliente.agregarMascota(mascota);
                }
            }
        }
        
        return cliente;
    }

    @Override
    public boolean eliminarCliente(String id) {
        // (Mantener tu código actual de eliminación)
        String linea;
        archivoCliente = new File(FILE_NAME);
        File archivoTemporal = new File("temporal.txt");

        try {
            lectorArchivo = new FileReader(archivoCliente);
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

            escritorBuffer = new BufferedWriter(new FileWriter(archivoCliente));
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
    public List<Cliente> listarCliente() {
        archivoCliente = new File(FILE_NAME);
        String linea;
        List<Cliente> clientes = new ArrayList<>();
        try {
            lectorArchivo = new FileReader(archivoCliente);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                Cliente cliente = construirClienteDesdeLinea(linea);
                clientes.add(cliente);
            }
            return clientes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        archivoCliente = new File(FILE_NAME);
        String linea;
        try {
            lectorArchivo = new FileReader(archivoCliente);
            lectorBuffer = new BufferedReader(lectorArchivo);

            while ((linea = lectorBuffer.readLine()) != null) {
                if (linea.substring(0, linea.indexOf(",")).equals(cliente.getIdentificacion())) {
                    lectorBuffer.close();
                    eliminarCliente(cliente.getIdentificacion());
                    registrarCliente(cliente);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}