package Aerolinea;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pasaje {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    private Vuelo vuelo;
    private LocalDateTime fecha;
    private Cliente cliente;
    private double precio;
    private int cantidadValijas; // cada valija tiene un valor
    private String numeroPasaje;
    private int numeroAsiento;

    ////////////////////////////////////////////
    // CONSTRUCTORES  -------------------------
    ////////////////////////////////////////////

    public Pasaje() {
    }

    public Pasaje(Pasaje pasaje) {
        this.numeroPasaje = pasaje.numeroPasaje;
        this.vuelo = pasaje.vuelo;
        this.cliente = pasaje.cliente;
        this.precio = pasaje.precio;
        this.cantidadValijas = pasaje.cantidadValijas;
        this.numeroAsiento = pasaje.numeroAsiento;
        this.fecha = pasaje.fecha;
    }

    public Pasaje(Vuelo vuelo, Cliente cliente, double precio, int cantidadValijas, int numeroAsiento, LocalDateTime fecha) {
        this.numeroPasaje = UUID.randomUUID().toString().replaceAll("-", "");
        this.vuelo = vuelo;
        this.cliente = cliente;
        this.precio = precio;
        this.cantidadValijas = cantidadValijas;
        this.numeroAsiento = numeroAsiento;
        this.fecha = fecha;
    }

    ////////////////////////////////////////////
    // GETTERS AND SETTERS  --------------------
    ////////////////////////////////////////////

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidadValijas() {
        return cantidadValijas;
    }

    public void setCantidadValijas(int cantidadValijas) {
        this.cantidadValijas = cantidadValijas;
    }

    public String getNumeroPasaje() {
        return numeroPasaje;
    }

    public void setNumeroPasaje(String numeroPassaje) {
        this.numeroPasaje = numeroPassaje;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    ////////////////////////////////////////////
    // METODOS  --------------------------------
    ////////////////////////////////////////////

    @Override
    public String toString() {
        return  "Vuelo = " + vuelo.getOrigen().getCiudad() + " a " + vuelo.getDestino().getCiudad() +
                "\nCodigo Vuelo = " + vuelo.getCodigoVuelo() +
                "\nFecha = " + fecha +
                "\nCliente = " + cliente.getNombre() + " " + cliente.getApellido() + " (" + cliente.getNombreDeUsuario() + ")" +
                "\nPrecio = $" + precio +
                "\nCantidad de Valijas = " + cantidadValijas +
                "\nNumero de Passaje= " + numeroPasaje +
                "\nNumero de Asiento= " + numeroAsiento;
    }

}