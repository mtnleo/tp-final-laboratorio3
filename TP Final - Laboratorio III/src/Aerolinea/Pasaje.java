package Aerolinea;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pasaje {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    private Vuelo vuelo;
    private LocalDateTime fecha;
    private String pasaporteCliente;
    private double precio;
    private int cantidadValijas; // cada valija tiene un valor de $70
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
        this.pasaporteCliente = pasaje.pasaporteCliente;
        this.precio = pasaje.precio;
        this.cantidadValijas = pasaje.cantidadValijas;
        this.numeroAsiento = pasaje.numeroAsiento;
        this.fecha = pasaje.fecha;
    }

    public Pasaje(Vuelo vuelo, String pasaporteCliente, double precio, int cantidadValijas, int numeroAsiento, LocalDateTime fecha) {
        this.numeroPasaje = UUID.randomUUID().toString().replaceAll("-", "");
        this.vuelo = vuelo;
        this.pasaporteCliente = pasaporteCliente;
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

    public String getPasaporteCliente() {
        return pasaporteCliente;
    }

    public void setPasaporteCliente(String pasaporteCliente) {
        this.pasaporteCliente = pasaporteCliente;
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
                "\nPasaporte Cliente = " + pasaporteCliente +
                "\nPrecio = $" + precio +
                "\nCantidad de Valijas = " + cantidadValijas +
                "\nNumero de Passaje= " + numeroPasaje +
                "\nNumero de Asiento= " + numeroAsiento;
    }

}