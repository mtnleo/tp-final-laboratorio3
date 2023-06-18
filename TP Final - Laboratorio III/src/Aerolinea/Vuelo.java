package Aerolinea;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

public class Vuelo {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private String codigoVuelo; // N° de vuelo
    private double precio;
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
        this.precio = vuelo.precio;
        this.origen = vuelo.origen;
        this.destino = vuelo.destino;
        this.distanciaKm = vuelo.distanciaKm;
        this.avion = vuelo.avion;
        this.salida = vuelo.salida;
        this.duracion = vuelo.duracion;
        this.llegada = vuelo.llegada;
        this.estadoVuelo = vuelo.estadoVuelo;
        this.pasajes = vuelo.pasajes;
    }

    // Inicializa la lista de pasajes vacía
    public Vuelo(String numero, double precio, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, double duracion) {
        this.codigoVuelo = numero;
        this.precio = precio;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = km;
        this.avion = avion;
        this.salida = salida;
        this.duracion = duracion;
        this.llegada = calcularLlegada(salida, duracion);
        this.estadoVuelo = EstadoVuelo.EN_HORARIO;
        this.pasajes = new LinkedList<Pasaje> ();
    }

    // Recibe la lista de pasajes por parámetro
    public Vuelo(String numero, double precio, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, double duracion, LinkedList <Pasaje> pasajes) {
        this.codigoVuelo = numero;
        this.precio = precio;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = km;
        this.avion = avion;
        this.salida = salida;
        this.duracion = duracion;
        this.estadoVuelo = EstadoVuelo.EN_HORARIO;
        this.llegada = calcularLlegada(salida, duracion);
        this.pasajes = pasajes;
    }

    // Puede establecer la llegada (para que funcione JSON)
    public Vuelo(String numero, double precio, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, LocalDateTime llegada, double duracion, LinkedList <Pasaje> pasajes) {
        this.codigoVuelo = numero;
        this.precio = precio;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = km;
        this.avion = avion;
        this.salida = salida;
        this.duracion = duracion;
        this.estadoVuelo = EstadoVuelo.EN_HORARIO;
        this.llegada = llegada;
        this.pasajes = pasajes;
    }



    ////////////////////////////////////////////
    // METODOS ---------------------------------
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
        return  "Numero = " + codigoVuelo +
                "\nPrecio = " + precio +
                "\nOrigen = " + origen +
                "\nDestino = " + destino +
                "\nKm = " + distanciaKm +
                "\nAvion = " + avion +
                "\nSalida = " + salida +
                "\nDuracion = " + duracion +
                "\nLlegada = " + llegada +
                "\nEstado del vuelo = " + estadoVuelo.getDescripcion() +
                "\nPasajes = " + pasajes;
    }

    ////////////////////////////////////////////
    /// GETTERS & SETTERS ----------------------
    ////////////////////////////////////////////

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
        setLlegada(calcularLlegada(salida, duracion));
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
        setLlegada(calcularLlegada(salida, duracion));
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
    
    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}