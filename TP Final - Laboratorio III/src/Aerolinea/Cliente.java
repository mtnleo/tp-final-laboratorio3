package Aerolinea;
import java.util.HashMap;

public class Cliente {
    protected String nombre;
    protected String apellido;
    protected String pasaporte;
    protected String nombreDeUsuario;
    protected String contrasena;
    protected HashMap <String, Pasaje> pasajes;

    public Cliente() {
    }

    public Cliente(Cliente cliente) {
        this.nombre = cliente.nombre;
        this.apellido = cliente.apellido;
        this.pasaporte = cliente.pasaporte;
        this.pasajes = cliente.pasajes;
    }

    public Cliente(String nombre, String apellido, String pasaporte) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pasaporte = pasaporte;
        this.pasajes = new HashMap <String, Pasaje> ();
    }

    public Cliente(String nombre, String apellido, String pasaporte, HashMap <String, Pasaje> pasajes) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pasaporte = pasaporte;
        this.pasajes = pasajes;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", pasaporte='" + pasaporte + '\'' +
                ", pasajes=" + pasajes +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPasaporte() {
        return pasaporte;
    }

    public void setPasaporte(String pasaporte) {
        this.pasaporte = pasaporte;
    }

    public HashMap<String, Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPasajes(HashMap<String, Pasaje> pasajes) {
        this.pasajes = pasajes;
    }
}