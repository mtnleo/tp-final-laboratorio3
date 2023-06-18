package Aerolinea;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class Avion {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    private String id; // Id único del avión concreto
    private String nombre; // nombre y modelo
    private double distancia; // distancia que cubre en un solo tanque
    private int cantidadPasajeros; //cantidad de pasajeros que soporta

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Avion() {
    }

    public Avion(Avion avion) {
        this.id = avion.id;
        this.nombre = avion.nombre;
        this.distancia = avion.distancia;
        this.cantidadPasajeros = avion.cantidadPasajeros;
    }

    public Avion(String id, String nombre, double distancia, int cantidadPasajeros) {
        this.id = id;
        this.nombre = nombre;
        this.distancia = distancia;
        this.cantidadPasajeros = cantidadPasajeros;
    }

    ////////////////////////////////////////////
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    ////////////////////////////////////////////
    // METODOS ---- ----------------------------
    ////////////////////////////////////////////
/*
    public static void guardarAvionJson(Avion avion) {
        File file = new File("aviones.json");

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            Gson gson = new Gson();

            gson.toJson()
        } catch (IOException e) {
            System.out.println("Problemas abriendo el archivo aviones.json");
        }

    }
*/
    @Override
    public String toString() {
        return "[ID: " + id +
                " | Nombre: " + nombre +
                " | Distancia: " + distancia +
                " | Cantidad de pasajeros: " + cantidadPasajeros +
                ']';
    }
}