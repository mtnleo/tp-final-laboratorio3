package Aerolinea;

import java.time.LocalDateTime;
import java.util.*;

public class Aerolinea {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////

    private final HashMap<String, LinkedList<Vuelo>> vuelos; // Tree map -> K: Destino | V: LinkedList<Vuelo> (Ordenado segun precio)
    private final HashSet<Cliente> clientes;
    private final ArrayList<Avion> aviones;
    private final ArrayList<Aeropuerto> aeropuertos;


    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Aerolinea() {
        this.vuelos = new HashMap<String, LinkedList<Vuelo>>();
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

    // funcion provisorioa para probar agregarVuelos

    public void addVuelosHC() {
        //hardcode Aeropuerto
        Aeropuerto aeropuerto1 = new Aeropuerto("JFK", "Nueva York", "Estados Unidos");
        Aeropuerto aeropuerto2 = new Aeropuerto("LHR", "Londres", "Reino Unido");
        Aeropuerto aeropuerto3 = new Aeropuerto("CDG", "Paris", "Francia");
        Aeropuerto aeropuerto4 = new Aeropuerto("HND", "Tokio", "Japon");
        Aeropuerto aeropuerto5 = new Aeropuerto("SYD", "Sydney", "Australia");
        Aeropuerto aeropuerto6 = new Aeropuerto("DXB", "Dubai", "Emiratos Arabes Unidos");
        Aeropuerto aeropuerto7 = new Aeropuerto("SFO", "San Francisco", "Estados Unidos");
        Aeropuerto aeropuerto8 = new Aeropuerto("FRA", "Frankfurt", "Alemania");
        Aeropuerto aeropuerto9 = new Aeropuerto("ICN", "Seul", "Corea del Sur");
        Aeropuerto aeropuerto10 = new Aeropuerto("MEX", "Ciudad de Mexico", "Mexico");

        //hardcode Avion
        Avion avion1 = new Avion("A001", "Boeing 747", "Pasajeros", 2000.0, 150);
        Avion avion2 = new Avion("B002", "Cessna Citation X", "Privado", 3000.0, 8);
        Avion avion3 = new Avion("C003", "Airbus A380", "Pasajeros", 8000.0, 550);
        Avion avion4 = new Avion("D004", "Gulfstream G650", "Privado", 7000.0, 14);
        Avion avion5 = new Avion("E005", "Boeing 787", "Pasajeros", 11000.0, 300);
        Avion avion6 = new Avion("F006", "Bombardier Global 6000", "Privado", 6000.0, 12);

        //fechas
        LocalDateTime fecha1 = LocalDateTime.of(2023, 7, 1, 8, 0);
        LocalDateTime fecha2 = LocalDateTime.of(2023, 7, 8, 12, 30);
        LocalDateTime fecha3 = LocalDateTime.of(2023, 7, 15, 16, 45);
        LocalDateTime fecha4 = LocalDateTime.of(2023, 7, 22, 9, 15);
        LocalDateTime fecha5 = LocalDateTime.of(2023, 7, 28, 14, 0);
        LocalDateTime fecha6 = LocalDateTime.of(2023, 7, 31, 19, 30);



        agregarVuelo(new Vuelo("AA-123", aeropuerto2, aeropuerto3, 475, avion1, fecha1, 1.4));
        agregarVuelo(new Vuelo("PP-912", aeropuerto9, aeropuerto5, 8329, avion2, fecha4, 9));
        agregarVuelo(new Vuelo("AA-331", aeropuerto1, aeropuerto7, 4129, avion5, fecha2, 6.5));
    }

    // funcion de agregar a sus respectivas colecciones

    private void agregarVuelo(Vuelo vuelo) {
        if(!vuelos.containsKey(vuelo.getDestino().getCiudad())) {
            vuelos.put(vuelo.getDestino().getCiudad(), new LinkedList<Vuelo>());
        }
        vuelos.get(vuelo.getDestino().getCiudad()).add(vuelo);
    }

    private void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // faltan las funciones de cargar del y al JSON

    public void mostrarVuelos() {
        String[] lista_destinos = vuelos.keySet().toArray(new String[0]); // obtengo destinos para ir iterando y mostrandolos
        for (String destino: lista_destinos) {
            System.out.println("----------------");
            System.out.println("Destino: " + destino);
            mostrarVuelosDestino(vuelos.get(destino));
        }
    }

    public void mostrarVuelosDestino(LinkedList<Vuelo> vuelos_destino) {
        int i = 0;
        for (Vuelo vue: vuelos_destino) {
            System.out.println("#" + i);
            i+=1;
            System.out.println(vue.toString());
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
