package Aerolinea;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class Vuelo {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private String codigoVuelo; // N° de vuelo
    private Aeropuerto origen;
    private Aeropuerto destino;
    private Avion avion;

    private double distanciaKm;
    private double duracion;

    private LocalDateTime salida;
    private LocalDateTime llegada; // Se calcula automáticamente

    private EstadoVuelo estadoVuelo;

    private LinkedList<Pasaje> pasajes;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Vuelo() {
    }

    // Constructor por copia
    public Vuelo(Vuelo vuelo) {
        this.codigoVuelo = vuelo.codigoVuelo;
        this.origen = vuelo.origen;
        this.destino = vuelo.destino;
        this.distanciaKm = vuelo.distanciaKm;
        this.avion = vuelo.avion;
        this.salida = vuelo.salida;
        this.duracion = vuelo.duracion;
        this.llegada = vuelo.llegada;
        this.pasajes = vuelo.pasajes;
    }

    // Inicializa la lista de pasajes vacía
    public Vuelo(String numero, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, double duracion) {
        this.codigoVuelo = numero;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.avion = avion;
        this.salida = salida;
        this.duracion = duracion;
        this.llegada = calcularLlegada(salida, duracion);
        this.pasajes = new LinkedList<Pasaje> ();
    }

    // Recibe la lista de pasajes por parámetro
    public Vuelo(String numero, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, double duracion, LinkedList <Pasaje> pasajes) {
        this.codigoVuelo = numero;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = km;
        this.avion = avion;
        this.salida = salida;
        this.duracion = duracion;
        this.llegada = calcularLlegada(salida, duracion);
        this.pasajes = pasajes;
    }

    ////////////////////////////////////////////
    // METODOS --------------------------------------
    ////////////////////////////////////////////

    private int[] getHorasMinutosFromDouble(double num) { //obtengo horas y minutos de un double
        int[] resultado = new int[2];
        resultado[0] = (int)(Math.floor(num));
        double minutes = (num - Math.floor(num)) * 60;
        resultado[1] = (int)(Math.floor(minutes));

        return resultado;
    }

    private LocalDateTime calcularLlegada(LocalDateTime salida, double duracion) {
        int[] horario = getHorasMinutosFromDouble(duracion);
        salida = salida.plusHours(horario[0]);
        salida = salida.plusMinutes(horario[1]);
        return salida;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "numero='" + distanciaKm + '\'' +
                ", origen=" + origen +
                ", destino=" + destino +
                ", km=" + distanciaKm +
                ", avion=" + avion +
                ", salida=" + salida +
                ", duracion=" + duracion +
                ", llegada=" + llegada +
                ", pasajes=" + pasajes +
                '}';
    }

    ////////////////////////////////////////////
    /// GETTERS & SETTERS ----------------------
    ////////////////////////////////////////////

    public String getNumero() {
        return codigoVuelo;
    }

    public void setNumero(String numero) {
        this.codigoVuelo = numero;
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
        return distanciaKm;
    }

    public void setKm(double km) {
        this.distanciaKm = km;
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

    public void setEnHorario() {
        this.estadoVuelo = EstadoVuelo.EN_HORARIO;
    }

    public void setDemorado() {
        this.estadoVuelo = EstadoVuelo.DEMORADO;
    }

    public void setCancelado() {
        this.estadoVuelo = EstadoVuelo.CANCELADO;
    }

    public String getEstadoVuelo() {
        return estadoVuelo.getDescripcion();
    }

    public String getCodigoVuelo() {
        return codigoVuelo;
    }

    public void setCodigoVuelo(String codigoVuelo) {
        this.codigoVuelo = codigoVuelo;
    }

    public void setOrigen(Aerolinea.Aeropuerto origen) {
        this.origen = origen;
    }

    public void setDestino(Aerolinea.Aeropuerto destino) {
        this.destino = destino;
    }

    public void setAvion(Aerolinea.Avion avion) {
        this.avion = avion;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public void setEstadoVuelo(Aerolinea.EstadoVuelo estadoVuelo) {
        this.estadoVuelo = estadoVuelo;
    }

    public LinkedList<Aerolinea.Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPasajes(LinkedList<Aerolinea.Pasaje> pasajes) {
        this.pasajes = pasajes;
    }
}