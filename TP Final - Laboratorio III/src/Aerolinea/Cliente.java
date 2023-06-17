package Aerolinea;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Cliente {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    protected String nombre;
    protected String apellido;
    protected String pasaporte;
    protected String nombreDeUsuario;
    protected String contrasena;
    protected LinkedList<Pasaje> pasajes;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

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
        this.pasajes = new LinkedList<Pasaje>();
    }

    public Cliente(String nombre, String apellido, String pasaporte, LinkedList <Pasaje> pasajes) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pasaporte = pasaporte;
        this.pasajes = pasajes;
    }

    ////////////////////////////////////////////
    // GETTERS AND SETTERS --------------------
    ////////////////////////////////////////////

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

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", pasaporte='" + pasaporte + '\'' +
                ", pasajes=" + pasajes +
                '}';
    }

    public void mostrarPasajes() {
        for (Pasaje pas: pasajes) {
            System.out.println(pas.toString());
        }
    }


}