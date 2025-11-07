package Negocio;

import Impl.VentaDAOFile;
import Dao.VentaDAO;
import Model.Venta;
import Model.MascotaDTO;
import java.util.List;

public class VentaNegocio {
    private VentaDAO ventaDAO = new VentaDAOFile();

    public VentaNegocio() {
    }

    public boolean registarVenta(Venta venta) {
        // Validar que la mascota no esté ya vendida
        if (mascotaYaVendida(venta.getMascota().getId())) {
            System.out.println("Error: La mascota con ID " + venta.getMascota().getId() + " ya fue vendida");
            return false;
        }
        
        return ventaDAO.RegistrarVenta(venta);
    }

    public List<Venta> listarVentas() {
        return ventaDAO.ListarVentas();
    }

    // Método para validar si una mascota ya fue vendida
    private boolean mascotaYaVendida(String idMascota) {
        List<Venta> ventas = listarVentas();
        for (Venta venta : ventas) {
            if (venta.getMascota() != null && venta.getMascota().getId().equals(idMascota)) {
                return true;
            }
        }
        return false;
    }
}