package Aerolinea;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Objects;

public class Cliente {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    private String nombre;
    private String apellido;
    private String pasaporte;
    private String nombreDeUsuario;
    private String contrasena;
    private final LinkedList<Pasaje> pasajes;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

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

    public Cliente(String nombre, String apellido, String pasaporte, String nombreDeUsuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.pasaporte = pasaporte;
        this.nombreDeUsuario = nombreDeUsuario;
        this.contrasena = contrasena;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////

    public void agregarPasajeCliente(Pasaje pasaje) {
        pasajes.add(pasaje);
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

    public void mostrarPasajes() {
        for (Pasaje pas: pasajes) {
            System.out.println(pas.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(nombreDeUsuario, cliente.nombreDeUsuario) && Objects.equals(pasaporte, cliente.pasaporte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreDeUsuario, pasaporte);
    }
}