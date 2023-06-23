package Aerolinea;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class Vuelo implements Comparable<Vuelo> {
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

    private int pasajesVendidos;

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
        this.pasajesVendidos = vuelo.pasajesVendidos;
    }

    // Inicializa los pasajesVendidos en 0
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
        this.pasajesVendidos = 0;
    }

    // Recibe los pasajes vendidos por parametro
    public Vuelo(String numero, double precio, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, double duracion, int pasajesVendidos) {
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
        this.pasajesVendidos = pasajesVendidos;
    }

    // Puede establecer la llegada (para que funcione JSON)
    public Vuelo(String numero, double precio, Aeropuerto origen, Aeropuerto destino, double km, Avion avion, LocalDateTime salida, LocalDateTime llegada, double duracion, int pasajesVendidos) {
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
        this.pasajesVendidos = pasajesVendidos;
    }

    ////////////////////////////////////////////
    // METODOS ---------------------------------
    ////////////////////////////////////////////

    public int comprobarEspacioVuelo () { // retorna 0 si no tiene espacio, sino retorna el numero de asiento
        int asiento;

        if (pasajesVendidos >= avion.getCantidadPasajeros()) {
            asiento = 0;
        }
        else {
            asiento = pasajesVendidos + 1;
        }

        return asiento;
    }

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
        return  "Codigo = " + codigoVuelo +
                "\nPrecio = $" + precio +
                "\nOrigen = " + origen +
                "\nDestino = " + destino +
                "\nKm = " + distanciaKm +
                "\nAvion = " + avion +
                "\nSalida = " + salida +
                "\nDuracion = " + duracion +
                "\nLlegada = " + llegada +
                "\nEstado = " + estadoVuelo.toString() +
                "\nPasajes Vendidos = " + pasajesVendidos;
    }

    public String toStringCorto() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d 'de' MMMM", new Locale("es"));
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        return "Precio (sin descuento) = $" + precio +
               "\nOrigen = " + origen.getCiudad() + " (" + origen.getCodigo() + ")" +
               "\nDestino = " + destino.getCiudad() +  " (" + destino.getCodigo() + ")" +
               "\nFecha = " + salida.format(formatoFecha) +
               "\nHora = " + salida.format(formatoHora) +
               "\nCodigo = " + codigoVuelo;
    }

    public String toStringCortoSinPrecio() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("d 'de' MMMM", new Locale("es"));
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        return "Origen = " + origen.getCiudad() + " (" + origen.getCodigo() + ")" +
                "\nDestino = " + destino.getCiudad() +  " (" + destino.getCodigo() + ")" +
                "\nFecha = " + salida.format(formatoFecha) +
                "\nHora = " + salida.format(formatoHora) +
                "\nCodigo = " + codigoVuelo;
    }

    ////////////////////////////////////////////
    /// GETTERS & SETTERS ----------------------
    ////////////////////////////////////////////

    public int getPasajesVendidos() {
        return pasajesVendidos;
    }

    public void setPasajesVendidos(int pasajesVendidos) {
        this.pasajesVendidos = pasajesVendidos;
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

    // PARA COMPARAR EN EL TREESET

    @Override
    public int compareTo(Vuelo vue) {
        return Double.compare(precio, vue.precio);
    }
}