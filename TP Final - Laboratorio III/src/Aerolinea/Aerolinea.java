package Aerolinea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
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

    }

    // funcion de agregar a sus respectivas colecciones

    public void agregarVuelo() {
        Vuelo vuelo = cargarVueloPorTeclado();

        if(!vuelos.containsKey(vuelo.getDestino().getCiudad())) {
            vuelos.put(vuelo.getDestino().getCiudad(), new LinkedList<Vuelo>());
            System.out.println("Vuelo generado con exito.");
        }

        else {
            System.out.println("Error. El codigo de vuelo ya existe.");
        }
    }

    private Vuelo cargarVueloPorTeclado() {
                Scanner scan = new Scanner(System.in);
                Vuelo vuelo = new Vuelo();

                System.out.println("Ingrese el código del vuelo:");
                String codigoVuelo = scan.nextLine();
                vuelo.setCodigoVuelo(codigoVuelo);
                System.out.println("Ingrese el aeropuerto de origen:");
                Aeropuerto origen = new Aeropuerto(scan.nextLine());
                vuelo.setOrigen(origen);
                System.out.println("Ingrese el aeropuerto de destino:");
                Aeropuerto destino = new Aeropuerto(scan.nextLine());
                vuelo.setDestino(destino);
                System.out.println("Ingrese el avión:");
                Avion avion = new Avion(scan.nextLine());
                vuelo.setAvion(avion);
                System.out.println("Ingrese la distancia en kilómetros:");
                double distanciaKm = scan.nextDouble();
                vuelo.setDistanciaKm(distanciaKm);
                System.out.println("Ingrese la duración en horas:");
                double duracion = scan.nextDouble();
                vuelo.setDuracion(duracion);
                System.out.println("Ingrese la fecha y hora de salida (YYYY-MM-DD HH:mm):");
                String fechaSalida = scan.next();
                String horaSalida = scan.next();
                LocalDateTime salida = LocalDateTime.parse(fechaSalida + " " + horaSalida);
                vuelo.setSalida(salida);

                return vuelo;
    }

    public void modificarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el numero del vuelo a modificar: ");
        String codigoVuelo = scan.nextLine();
        if(vuelos.containsKey(codigoVuelo)) {
            Vuelo vuelo = null;
            String [] destinos = vuelos.keySet().toArray(new String[0]);
            for(String destino: destinos) {
                for(Vuelo v: vuelos.get(destino)) {
                    if(v.getCodigoVuelo().equals(codigoVuelo)) {
                        vuelo = v;
                    }
                }
            }
            System.out.println("Seleccione el campo a modificar: ");
            System.out.println("1. Origen");
            System.out.println("2. Destino");
            System.out.println("3. Avion");
            System.out.println("4. Distancia");
            System.out.println("5. Duracion");
            System.out.println("6. Fecha y hora de salida");
            System.out.println("7. Estado del vuelo");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo origen: ");
                    String codigoOrigen = scan.nextLine();
                    Aeropuerto origenNuevo = buscarAeropuerto(codigoOrigen);
                    if (origenNuevo != null) {
                        vuelo.setOrigen(origenNuevo);
                    } else {
                        System.out.println("El aeropuerto ingresado no existe.");
                    }
                    break;

                case 2:
                    System.out.print("Ingrese el nuevo destino: ");
                    String codigoDestino = scan.nextLine();
                    Aeropuerto destinoNuevo = buscarAeropuerto(codigoDestino);
                    if (destinoNuevo != null) {
                        vuelo.setDestino(destinoNuevo);
                    } else {
                        System.out.println("El aeropuerto ingresado no existe.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el nuevo avion: ");
                    String codigoAvion = scan.nextLine();
                    Avion nuevoAvion = buscarAvion(codigoAvion);
                    if (nuevoAvion != null) {
                        vuelo.setAvion(nuevoAvion);
                    } else {
                        System.out.println("El avion ingresado no existe.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese la nueva distancia: ");
                    double distanciaNueva = scan.nextDouble();
                    vuelo.setDistanciaKm(distanciaNueva);
                    break;

                case 5:
                    System.out.print("Ingrese la nueva duracion: ");
                    double duracionNueva = scan.nextDouble();
                    vuelo.setDuracion(duracionNueva);
                    break;

                case 6:
                    System.out.print("Ingrese la nueva fecha y hora de salida: ");
                    break;

                case 7:
                    System.out.print("Ingrese el estado del vuelo: ");
                    String estado = scan.nextLine();
                    if (estado.equals("Demorado")) {
                        vuelo.setDemorado();
                    } else if (estado.equals("En horario")) {
                        vuelo.setEnHorario();
                    } else if (estado.equals("Cancelado")) {
                        vuelo.setCancelado();
                    } else {
                        System.out.println("El estado ingresado no es valido.");
                    }
                    break;

                default:
                    break;
            }
        }
        else {
            System.out.println("El vuelo ingresado no existe.");
        }
    }

    private Aeropuerto buscarAeropuerto(String codigo) {
        Aeropuerto aeropuerto = null;

        for(Aeropuerto a: aeropuertos) {
            if(a.getCodigo() == codigo) {
                aeropuerto = a;
            }
        }

        return aeropuerto;
    }

    private Avion buscarAvion(String codigo) {
        Avion avion = null;

        for(Avion a: aviones) {
            if(a.getId() == codigo) {
                avion = a;
            }
        }

        return avion;
    }

    public void eliminarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el codigo del vuelo a eliminar: ");
        String codigoVuelo = scan.nextLine();
        if(vuelos.containsKey(codigoVuelo)) {
            vuelos.remove(codigoVuelo);
            System.out.println("El vuelo ha sido eliminado.");
        }
        else {
            System.out.println("No se encontro el vuelo ingresado.");
        }
    }

    private void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    // faltan las funciones de cargar del y al JSON

    public void agregarAvion() {
        Avion avion = cargarAvionPorTeclado();

        if(buscarAvion(avion.getId()) != null) {
            aviones.add(avion);
            System.out.println("Avion generado con exito.");
        }

        else {
            System.out.println("Error. El codigo de avion ya existe.");
        }
    }

    public void modificarAvion() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el ID del avion a modificar: ");
        String idAvion = scan.nextLine();
        if(buscarAvion(idAvion) != null) {
            Avion avion = buscarAvion(idAvion);
            System.out.println("Seleccione el campo a modificar: ");
            System.out.println("1. Nombre");
            System.out.println("2. Tipo");
            System.out.println("3. Distancia");
            System.out.println("4. Cantidad de pasajeros");
            int opcion = scan.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nuevo nombre: ");
                    String nombre = scan.nextLine();
                    avion.setNombre(nombre);
                    break;

                case 2:
                    System.out.print("Ingrese el nuevo tipo: ");
                    String tipo = scan.nextLine();
                    avion.setTipo(tipo);
                    break;

                case 3:
                    System.out.print("Ingrese la nueva distancia: ");
                    Double distancia = scan.nextDouble();
                    avion.setDistancia(distancia);
                    break;

                case 4:
                    System.out.print("Ingrese la nueva cantidad de pasajeros: ");
                    int cant = scan.nextInt();
                    avion.setCantidadPasajeros(cant);
                    break;
                default:
                    break;
            }
        }

        else {
            System.out.println("El avion ingresado no existe.");
        }
    }

    public void eliminarAvion() {
            Scanner scan = new Scanner(System.in);
            System.out.println("Ingrese el ID del avion a eliminar: ");
            String id = scan.nextLine();
            if(buscarAvion(id) != null) {
                aviones.remove(id);
                System.out.println("El avion ha sido eliminado.");
            }
            else {
                System.out.println("No se encontro el avion ingresado.");
            }
    }

    private Avion cargarAvionPorTeclado() {
        Scanner scan = new Scanner(System.in);
        Avion avion = new Avion();

        System.out.println("Ingrese el ID del avion:");
        String id = scan.nextLine();
        avion.setId(id);
        System.out.println("Ingrese el nombre del avion:");
        String nombre = scan.nextLine();
        avion.setNombre(nombre);
        System.out.println("Ingrese el tipo de avion:");
        String tipo = scan.nextLine();
        avion.setTipo(tipo);
        System.out.println("Ingrese la distancia:");
        double distancia = scan.nextDouble();
        avion.setDistancia(distancia);
        System.out.println("Ingrese cantidad de pasajeros:");
        int cantidad = scan.nextInt();
        avion.setCantidadPasajeros(cantidad);

        return avion;
    }

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

    // JSON'S

    public void cargarJson(String pathname) {
        File file = new File(pathname);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            Gson gson = new GsonBuilder() // El Gson builder para poder cargar el LocalDateTime que rompe el archivo
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();

            gson.toJson(this, Aerolinea.class, bufferedWriter);

            bufferedWriter.close();
        }
        catch (IOException e) {
            System.out.println("|X| ERROR CARGANDO AEROLINEA A JSON |X|");
            System.out.println(e.getMessage());
        }

    }

    public static Aerolinea leerJson(String pathname) {
        Aerolinea aerolinea = null;
        File file = new File(pathname);
        if (file.exists()) {
            if (file.canRead()) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                    Gson gson = new GsonBuilder() // El Gson builder para poder leer el LocalDateTime que rompe el archivo
                            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                            .create();

                    aerolinea = gson.fromJson(bufferedReader, Aerolinea.class);

                    bufferedReader.close();
                }
                catch (IOException e) {
                    System.out.println("|X| ERROR E/S LEYENDO EL JSON |X|");
                    System.out.println(e.getMessage());
                }
            }
            else {
                System.out.println("|X| ERROR LEYENDO EL JSON, NO TIENE PERMISOS |X|");
            }
        }
        else {
            System.out.println("|X| ERROR LEYENDO EL JSON, NO EXISTE EL ARCHIVO |X|");
        }

        return aerolinea;
    }
}