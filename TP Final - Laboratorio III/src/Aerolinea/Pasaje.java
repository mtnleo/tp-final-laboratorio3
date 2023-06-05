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
    private Character grupo; //???
    private double precio;
    private String equipaje; // ¿¿puede ser un enum??
    private String numeroPassaje;
    private int numeroAsiento;

    ////////////////////////////////////////////
    // CONSTRUCTORES  -------------------------
    ////////////////////////////////////////////

    public Pasaje() {
    }

    public Pasaje(Pasaje pasaje) {
        this.numeroPassaje = pasaje.numeroPassaje;
        this.vuelo = pasaje.vuelo;
        this.cliente = pasaje.cliente;
        this.grupo = pasaje.grupo;
        this.precio = pasaje.precio;
        this.equipaje = pasaje.equipaje;
        this.numeroAsiento = pasaje.numeroAsiento;
        this.fecha = pasaje.fecha;
    }

    public Pasaje(String numero, Vuelo vuelo, Cliente cliente, char grupo, double precio, String equipaje, int numeroAsiento, LocalDateTime fecha) {
        this.numeroPassaje = UUID.randomUUID().toString().replaceAll("-", "");
        this.vuelo = vuelo;
        this.cliente = cliente;
        this.grupo = grupo;
        this.precio = precio;
        this.equipaje = equipaje;
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

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEquipaje() {
        return equipaje;
    }

    public void setEquipaje(String equipaje) {
        this.equipaje = equipaje;
    }

    public String getNumeroPassaje() {
        return numeroPassaje;
    }

    public void setNumeroPassaje(String numeroPassaje) {
        this.numeroPassaje = numeroPassaje;
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
        return "Pasaje{" +
                "vuelo=" + vuelo +
                ", fecha=" + fecha +
                ", cliente=" + cliente +
                ", grupo=" + grupo +
                ", precio=" + precio +
                ", equipaje='" + equipaje + '\'' +
                ", numeroPassaje='" + numeroPassaje + '\'' +
                ", numeroAsiento=" + numeroAsiento +
                '}';
    }

}