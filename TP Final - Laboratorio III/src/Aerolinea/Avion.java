package Aerolinea;

public class Avion {
    private String id; // Id único del avión concreto
    private String tipo;
    private double distancia;
    private int cantidadPasajeros;

    public Avion() {
    }

    public Avion(Avion avion) {
        this.id = avion.id;
        this.tipo = avion.tipo;
        this.distancia = avion.distancia;
        this.cantidadPasajeros = avion.cantidadPasajeros;
    }

    public Avion(String id, String tipo, double distancia, int cantidadPasajeros) {
        this.id = id;
        this.tipo = tipo;
        this.distancia = distancia;
        this.cantidadPasajeros = cantidadPasajeros;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", distancia=" + distancia +
                ", cantidadPasajeros=" + cantidadPasajeros +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }
}
