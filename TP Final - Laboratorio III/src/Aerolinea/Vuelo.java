package Aerolinea;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Vuelo {
    private String numero; // N° de vuelo
    private Aeropuerto origen;
    private Aeropuerto destino;
    private double km;
    private Avion avion;
    private LocalDateTime salida;
    private double duracion;
    private LocalDateTime llegada; // Se calcula automáticamente
    private HashMap <String, Pasaje> pasajes; // La clave es el n° de pasaje

    public Vuelo() {
    }

    // Constructor por copia
    public Vuelo(Vuelo vuelo) {
        this.numero = vuelo.numero;
        this.origen = vuelo.origen;
        this.destino = vuelo.destino;
        this.km = vuelo.km;
        this.avion = vuelo.avion;
        this.salida = vuelo.salida;
        this.duracion = vuelo.duracion;
        this.llegada = vuelo.llegada;
        this.pasajes = vuelo.pasajes;
    }

    // Inicializa la lista de pasajes vacía
    public Vuelo(String numero, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, double duracion) {
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
        this.km = km;
        this.avion = avion;
        this.salida = salida;
        this.duracion = duracion;
        this.llegada = calcularLlegada();
        this.pasajes = new HashMap <String, Pasaje> ();
    }

    // Recibe la lista de pasajes por parámetro
    public Vuelo(String numero, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, double duracion, HashMap <String, Pasaje> pasajes) {
        this.numero = numero;
        this.origen = origen;
        this.destino = destino;
        this.km = km;
        this.avion = avion;
        this.salida = salida;
        this.duracion = duracion;
        this.llegada = calcularLlegada();
        this.pasajes = pasajes;
    }

    private LocalDateTime calcularLlegada() {
        // Habría que retornar la salida + la duración.
        return this.llegada;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "numero='" + numero + '\'' +
                ", origen=" + origen +
                ", destino=" + destino +
                ", km=" + km +
                ", avion=" + avion +
                ", salida=" + salida +
                ", duracion=" + duracion +
                ", llegada=" + llegada +
                ", pasajes=" + pasajes +
                '}';
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public LocalDateTime getSalida() {
        return salida;
    }

    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    public LocalDateTime getLlegada() {
        return llegada;
    }

    public void setLlegada(LocalDateTime llegada) {
        this.llegada = llegada;
    }

    public HashMap<String, Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPasajes(HashMap<String, Pasaje> pasajes) {
        this.pasajes = pasajes;
    }
}