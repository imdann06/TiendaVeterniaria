package Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String identificacion;
    private String nombres;
    private String direccionContacto;
    private String numeroContacto;
    private List<MascotaDTO> mascotas = new ArrayList<>(); 

    public Cliente() {
    }

    public Cliente(String identificacion, String nombres, String direccionContacto, String numeroContacto) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.direccionContacto = direccionContacto;
        this.numeroContacto = numeroContacto;
    }

    // MÉTODOS PARA MANEJAR MASCOTAS
    public void agregarMascota(MascotaDTO mascota) {
        mascotas.add(mascota);
    }

    public List<MascotaDTO> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<MascotaDTO> mascotas) {
        this.mascotas = mascotas;
    }

    // GETTERS Y SETTERS ORIGINALES
    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDireccionContacto() {
        return direccionContacto;
    }

    public void setDireccionContacto(String direccionContacto) {
        this.direccionContacto = direccionContacto;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente{");
        sb.append("identificacion=").append(identificacion);
        sb.append(", nombres=").append(nombres);
        sb.append(", direccionContacto=").append(direccionContacto);
        sb.append(", numeroContacto=").append(numeroContacto);
        sb.append(", mascotas=").append(mascotas.size()).append(" mascotas");
        sb.append('}');
        return sb.toString();
    }
}