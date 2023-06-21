package Aerolinea;
import java.util.*;

public class Cliente {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    protected String nombre;
    protected String apellido;
    protected String pasaporte;
    protected String nombreDeUsuario;
    protected String contrasena;

    protected double millas;
    protected final LinkedList<Pasaje> pasajes;

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

    public Cliente(String nombre, String apellido, String pasaporte, LinkedList<Pasaje> pasajes) {
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

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public double getMillas() {
        return millas;
    }

    public void setMillas(double millas) {
        this.millas = millas;
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
        int i = 1;
        for (Pasaje pas : pasajes) {
            System.out.println("\n========================== PASAJE " + i + " ==========================");
            System.out.println(pas.toString());
            System.out.println("===============================================================");
            i = i + 1;
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

    public void verPerfil() {
        Scanner scan = new Scanner(System.in);
        System.out.println("MI PERFIL");
        System.out.println(nombre + " " + apellido);
        System.out.println("Pasaporte " + pasaporte);
        System.out.println("Millas: " + millas);

        if (this instanceof Estandar) {
            System.out.println("Clase: Estándar");
        } else if (this instanceof Gold) {
            System.out.println("Clase: Gold");
        } else if (this instanceof Platinum) {
            System.out.println("Clase: Platinum");
        } else if (this instanceof Black) {
            System.out.println("Clase: Black");
        }

        System.out.println("1. Cambiar contraseña");
        System.out.println("2. Eliminar mi cuenta");
        System.out.println("3. Volver");
        System.out.print("Ingrese una opción: ");
        int opcion = scan.nextInt();
        scan.nextLine();

        switch (opcion) {
            case 1:
                boolean cont1 = true;
                while (cont1) {
                    System.out.println("Nueva contraseña: ");
                    String nuevaPass = scan.nextLine();
                    System.out.println("Confirme nueva contraseña: ");
                    if (scan.nextLine().equals(nuevaPass)) {
                        this.contrasena = nuevaPass;
                        System.out.println("CONTRASEÑA MODIFICADA CON ÉXITO");
                        cont1 = false;
                    } else {
                        System.out.println("Las contraseñas no coinciden. Por favor vuelva a intentar.");
                    }
                }
                break;
            case 2:
                ///AAAAAA
                break;
            case 3:
                break;
            default:
                System.out.println("Por favor ingrese una opción válida.");
                break;
        }
    }
}