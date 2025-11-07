package Impl;

import Dao.VentaDAO;
import Model.Cliente;
import Model.Detalle;
import Model.MascotaDTO;
import Model.Vendedor;
import Model.Venta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import Dao.ClienteDAO;
import Dao.VendedorDAO;
import Dao.MascotaDAO;
import Impl.MascotaDAOFile;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class VentaDAOFile implements VentaDAO {

    private static final String DELIMITADOR_ARCHIVO = ",";
    private static final String FILE_NAME = "venta.txt";
    private BufferedWriter escritorBuffer;
    private BufferedReader lectorBuffer;
    private FileWriter escritorArchivo;
    private FileReader lectorArchivo;
    private File archivoVenta;

    public VentaDAOFile() {
        archivoVenta = new File(FILE_NAME);
    }

    @Override
    public boolean RegistrarVenta(Venta venta) {
        StringBuilder sb = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append(sdf.format(venta.getFecha()));
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getValorVenta());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getVendedor().getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getCliente().getIdentificacion());
        sb.append(DELIMITADOR_ARCHIVO);
        sb.append(venta.getMascota().getId());

        try {
            escritorArchivo = new FileWriter(archivoVenta, true);
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
    public List<Venta> ListarVentas() {
        archivoVenta = new File(FILE_NAME);
        String linea;
        List<Venta> ventas = new ArrayList<>();

        ClienteDAO clienteDAO = new ClienteDAOFile();
        VendedorDAO vendedorDAO = new VendedorDAOFile();
        MascotaDAO mascotaDAO = new MascotaDAOFile();

        try {
            lectorArchivo = new FileReader(archivoVenta);
            lectorBuffer = new BufferedReader(lectorArchivo);
            while ((linea = lectorBuffer.readLine()) != null) {
                try {
                    String parametros[] = linea.split(",");

                    if (parametros.length < 5) {
                        System.out.println("Línea inválida: " + linea);
                        continue;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date fecha = sdf.parse(parametros[0]);
                    double valorVenta = Double.parseDouble(parametros[1]);

                    Vendedor vendedor = vendedorDAO.consultarVendedor(parametros[2]);
                    Cliente cliente = clienteDAO.consultarCliente(parametros[3]);
                    MascotaDTO mascota = mascotaDAO.consultarMascotas(parametros[4]);

                    if (vendedor != null && cliente != null && mascota != null) {
                        Venta venta = new Venta(fecha, valorVenta, vendedor, cliente, mascota);
                        ventas.add(venta);
                    } else {
                        System.out.println("Error: No se encontró vendedor, cliente o mascota para la venta: " + linea);
                    }

                } catch (ParseException e) {
                    System.out.println("Error parseando fecha en línea: " + linea);
                } catch (NumberFormatException e) {
                    System.out.println("Error en formato numérico en línea: " + linea);
                } catch (Exception e) {
                    System.out.println("Error procesando línea: " + linea);
                }
            }
            return ventas;
        } catch (IOException e) {
            System.out.println("Error leyendo archivo de ventas");
        } finally {
            try {
                if (lectorBuffer != null) lectorBuffer.close();
                if (lectorArchivo != null) lectorArchivo.close();
            } catch (IOException e) {
                System.out.println("Error cerrando recursos");
            }
        }
        return ventas;
    }
}