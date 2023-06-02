package Aerolinea;

import java.nio.file.FileSystemAlreadyExistsException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class Aerolinea {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    private LinkedHashSet<Vuelo> vuelos;
    private HashSet<Cliente> clientes;
    private ArrayList<Avion> aviones;
    private ArrayList<Aeropuerto> aeropuertos;


    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Aerolinea() {
        this.vuelos = new LinkedHashSet<Vuelo>();
        this.clientes = new HashSet<Cliente>();
        this.aviones = new ArrayList<Avion>();
        this.aeropuertos = new ArrayList<Aeropuerto>();
    }

    ////////////////////////////////////////////
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////

    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////

    // faltan las funciones de cargar del y al JSON

    public void mostrarVuelos() {
        for (Vuelo vuelo: vuelos) {
            System.out.println(vuelo.toString());
        }
    }

    public void mostrarClientes() { //ADMIN FUNC
        for (Cliente cliente: clientes) {
            System.out.println(cliente.toString());
        }
    }

    public void mostrarAviones() {
        for (Avion avion: aviones) {
            System.out.println(avion.toString());
        }
    }

    public void mostrarAeropuertos() {
        for (Aeropuerto aep: aeropuertos) {
            System.out.println(aep.toString());
        }
    }
}
