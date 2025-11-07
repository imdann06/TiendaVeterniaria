package Control;

import Negocio.VentaNegocio;
import Dao.VentaDAO;
import Impl.VentaDAOFile;
import Model.Venta;
import java.util.Date;
import Control.ClienteControl;
import Negocio.ClienteNegocio;
import Model.Cliente;
import Model.Vendedor;
import Negocio.VendedorNegocio;
import Vista.TablaVentas;
import Negocio.MascotaNegocio;
import Model.MascotaDTO;
import javax.swing.JOptionPane;

public class VentaContol {
    VentaNegocio ventaNegocio = new VentaNegocio();
    ClienteNegocio clienteNegocio = new ClienteNegocio();
    VendedorNegocio vendedorNegocio = new VendedorNegocio();
    MascotaNegocio mascotaNegocio = new MascotaNegocio();

    public void registrarVenta(Date fecha, double valorVenta, String documentoVendedor, String documentoCliente, String idMascota) {
        // Validar que la mascota existe
        MascotaDTO mascota = mascotaNegocio.consultarAnimal(idMascota);
        if (mascota == null) {
            JOptionPane.showMessageDialog(null, "Error: La mascota con ID " + idMascota + " no existe", "Mascota no encontrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el cliente existe
        Cliente cliente = clienteNegocio.consultarCliente(documentoCliente);
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Error: El cliente con documento " + documentoCliente + " no existe", "Cliente no encontrado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que el vendedor existe
        Vendedor vendedor = vendedorNegocio.consultarVendedor(documentoVendedor);
        if (vendedor == null) {
            JOptionPane.showMessageDialog(null, "Error: El vendedor con documento " + documentoVendedor + " no existe", "Vendedor no encontrado", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear y registrar la venta con la mascota
        Venta venta = new Venta(fecha, valorVenta, vendedor, cliente, mascota);
        boolean exito = ventaNegocio.registarVenta(venta);
        
        if (exito) {
            JOptionPane.showMessageDialog(null, "Venta registrada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error al registrar la venta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void listarVentas(){
        TablaVentas ventana = new TablaVentas();
        ventana.llenarTabla();
        ventana.setVisible(true);
    }
}