package Aerolinea;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Aerolinea {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    String nombre;
    private final HashMap<String, LinkedList<Vuelo>> vuelos; // Tree map -> K: Destino | V: LinkedList<Vuelo> (Ordenado segun precio)
    private final HashSet<Cliente> clientes;
    private final ArrayList<Avion> aviones;
    private final ArrayList<Aeropuerto> aeropuertos;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Aerolinea(String nombre) {
        this.nombre = nombre;
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
        aeropuertos.add(aeropuerto1);
        aeropuertos.add(aeropuerto2);
        aeropuertos.add(aeropuerto3);
        aeropuertos.add(aeropuerto4);
        aeropuertos.add(aeropuerto5);
        aeropuertos.add(aeropuerto6);
        aeropuertos.add(aeropuerto7);
        aeropuertos.add(aeropuerto8);
        aeropuertos.add(aeropuerto9);
        aeropuertos.add(aeropuerto10);

        Avion avion1 = new Avion("A001", "Boeing 747", 2000.0, 150);
        Avion avion2 = new Avion("B002", "Cessna Citation X", 3000.0, 8);
        Avion avion3 = new Avion("C003", "Airbus A380", 8000.0, 550);
        Avion avion4 = new Avion("D004", "Gulfstream G650", 7000.0, 14);
        Avion avion5 = new Avion("E005", "Boeing 787", 11000.0, 300);
        Avion avion6 = new Avion("F006", "Bombardier Global 6000", 6000.0, 12);
        aviones.add(avion1);
        aviones.add(avion2);
        aviones.add(avion3);
        aviones.add(avion4);
        aviones.add(avion5);
        aviones.add(avion6);

        LocalDateTime fecha1 = LocalDateTime.of(2023, 7, 1, 8, 0);
        LocalDateTime fecha2 = LocalDateTime.of(2023, 7, 8, 12, 30);
        LocalDateTime fecha3 = LocalDateTime.of(2023, 7, 15, 16, 45);
        LocalDateTime fecha4 = LocalDateTime.of(2023, 7, 22, 9, 15);
        LocalDateTime fecha5 = LocalDateTime.of(2023, 7, 28, 14, 0);
        LocalDateTime fecha6 = LocalDateTime.of(2023, 7, 31, 19, 30);

        Vuelo v1 = new Vuelo("AP125", 100000, aeropuerto1, aeropuerto2, 5000, avion1, fecha1, 20);
        Vuelo v2 = new Vuelo("AP220", 120000, aeropuerto3, aeropuerto1, 4500, avion2, fecha2, 18);
        Vuelo v3 = new Vuelo("AP307", 40000, aeropuerto4, aeropuerto10, 900, avion3, fecha3, 2.5);
        agregarVuelo(v1);
        agregarVuelo(v2);
        agregarVuelo(v3);
    }

//    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//           VUELOS       ||||||||||||||||||||||||||||||||||||||||||||||||
//    ||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public void agregarVueloTeclado() {
        Scanner scan = new Scanner(System.in);
        System.out.println("AGREGAR VUELO");
        System.out.print("Codigo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {
            if (vuelo == null) {
                vuelo = cargarVueloPorTeclado(codigo);
                agregarVuelo(vuelo);
                // AGREGAR VUELO A LA COLECCION
                System.out.println("VUELO AGREGADO CON ÉXITO");
            } else {
                throw new VueloExistenteException("ERROR: EL VUELO INGRESADO YA EXISTE");
            }
        } catch (VueloExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void agregarVuelo(Vuelo vuelo) {
        Vuelo vueloBuscado = buscarVuelo(vuelo.getCodigoVuelo());

        try {
            if (vueloBuscado == null) {
                String destinoCiudad = vuelo.getDestino().getCiudad();
                LinkedList<Vuelo> addVuelos = null;
                if (vuelos.containsKey(destinoCiudad)) {
                    addVuelos = vuelos.get(destinoCiudad);
                } else {
                    addVuelos = new LinkedList<Vuelo>();
                }

                addVuelos.add(vuelo);

                vuelos.put(destinoCiudad, addVuelos);

                System.out.println("VUELO AGREGADO CON ÉXITO");
            } else {
                throw new VueloExistenteException("El vuelo que se intento agregar ya existe! '" + vuelo.getCodigoVuelo() + "'");
            }
        } catch (VueloExistenteException e) {
            System.out.println(e.getMessage());
        }


    }

    private Vuelo buscarVuelo(String codigo) {
        Vuelo vuelo = null;

        String[] destinos = vuelos.keySet().toArray(new String[0]);

        for (String destino : destinos) {
            for (Vuelo v : vuelos.get(destino)) {
                if (v.getCodigoVuelo().equals(codigo)) {
                    vuelo = v;
                }
            }
        }

        return vuelo;
    }

    private Vuelo cargarVueloPorTeclado(String codigo) {
        Scanner scan = new Scanner(System.in);

        Vuelo vuelo = null;
        System.out.print("Aeropuerto de origen (codigo): ");
        String codigoOrigen = scan.nextLine();
        Aeropuerto origen = buscarAeropuerto(codigoOrigen);

        try {
            if (origen != null) {
                System.out.print("Aeropuerto de destino: ");
                String codigoDestino = scan.nextLine();
                Aeropuerto destino = buscarAeropuerto(codigoDestino);

                if (destino != null) {
                    System.out.print("Fecha y hora de partida (AAAA-MM-DD HH:mm): ");
                    String fechaHora = scan.nextLine();
                    LocalDateTime salida = LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    System.out.print("Distancia: ");
                    double distancia = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Duración en minutos: ");
                    double duracion = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Avion: ");
                    String codigoAvion = scan.nextLine();
                    Avion avion = buscarAvion(codigoAvion);

                    if (avion != null) {
                        System.out.print("Precio: ");
                        double precio = scan.nextInt();
                        scan.nextLine();

                        vuelo = new Vuelo(codigo, precio, origen, destino, distancia, avion, salida, duracion);
                    } else {
                        throw new AvionInexistenteException("El avion en cuestion no existe.");
                    }

                } else {
                    throw new AeropuertoInexistenteException("El aeropuerto de destino no existe.");
                }
            } else {
                throw new AeropuertoInexistenteException("El aeropuerto de origen no existe.");
            }
        } catch (AeropuertoInexistenteException | AvionInexistenteException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        return vuelo;
    }

    public void modificarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICAR VUELO");
        System.out.print("Código de vuelo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {

            if (vuelo != null) {
                System.out.println("1. Fecha y hora de salida");
                System.out.println("2. Distancia");
                System.out.println("3. Duracion");
                System.out.println("4. Avión");
                System.out.println("5. Precio");
                System.out.println("6. Estado del vuelo");
                System.out.print("Seleccione el campo a modificar: ");
                int opcion = scan.nextInt();
                scan.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Nueva fecha y hora de partida (AAAA-MM-DD HH:mm): ");
                        String fechaHora = scan.nextLine();
                        LocalDateTime salida = LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        vuelo.setSalida(salida);
                        break;

                    case 2:
                        System.out.print("Nueva distancia: ");
                        double distancia = scan.nextDouble();
                        scan.nextLine();
                        vuelo.setDistanciaKm(distancia);
                        break;

                    case 3:
                        System.out.print("Nueva duracion: ");
                        double duracion = scan.nextDouble();
                        scan.nextLine();
                        vuelo.setDuracion(duracion);
                        break;

                    case 4:
                        System.out.print("Nuevo avion (codigo): ");
                        String codigoAvion = scan.nextLine();
                        Avion avion = buscarAvion(codigoAvion);
                        if (avion != null) {
                            vuelo.setAvion(avion);
                        } else {
                            throw new AvionInexistenteException("No se encontro el avion buscado.");
                        }
                        break;

                    case 5:
                        System.out.print("Nuevo precio: ");
                        double precio = scan.nextDouble();
                        scan.nextLine();
                        vuelo.setPrecio(precio);
                        break;

                    case 6:
                        System.out.print("Estado del vuelo: ");
                        String estado = scan.nextLine();
                        if (estado.equals("Demorado")) {
                            vuelo.setDemorado();
                        } else if (estado.equals("En horario")) {
                            vuelo.setEnHorario();
                        } else if (estado.equals("Cancelado")) {
                            vuelo.setCancelado();
                        } else {
                            System.out.println("ERROR: ESTADO INVÁLIDO");
                        }
                        break;

                    default:
                        System.out.println("ERROR: OPCIÓN INVÁLIDA");
                        break;
                }

                System.out.println("VUELO MODIFICADO CON ÉXITO");
            } else {
                throw new VueloInexistenteException("El vuelo que se quiere modificar no existe.");
            }
        } catch (VueloInexistenteException | AvionInexistenteException | InputMismatchException |
                 java.time.format.DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Código de vuelo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {
            if (vuelo != null) {
                LinkedList<Vuelo> vuelosCiudad = vuelos.get(vuelo.getDestino().getCiudad());
                vuelosCiudad.remove(vuelo);
                System.out.println("VUELO ELIMINADO CON ÉXITO");
            } else {
                throw new VueloInexistenteException("El vuelo a eliminar no se encontro.");
            }
        } catch (VueloInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarVuelos() {
        String[] lista_destinos = vuelos.keySet().toArray(new String[0]);
        for (String destino : lista_destinos) {
            System.out.println("----------------");
            System.out.println("Destino: " + destino);
            mostrarVuelosDestino(vuelos.get(destino));
        }
    }

    private void mostrarVuelosDestino(LinkedList<Vuelo> vuelos_destino) {
        int i = 0;
        for (Vuelo vue : vuelos_destino) {
            System.out.println("#" + i);
            i += 1;
            System.out.println(vue.toString());
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       AVIONES      ||||||||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public void agregarAvionesTeclado() {
        Scanner scan = new Scanner(System.in);
        char continuar = 's';

        while (continuar == 's') {
            boolean avionExistente = true;

            while (avionExistente) {
                System.out.print("ID: ");
                String id = scan.nextLine();
                Avion avion = buscarAvion(id);
                if (avion != null) {
                    System.out.println("El avion ya existe, intente de nuevo cargando uno diferente");
                } else {
                    avionExistente = false;
                    avion = cargarAvionPorTeclado(id);
                    agregarAvion(avion);
                }
            }

            System.out.println("Quiere seguir ingresando aviones? 's'/'n'");
            continuar = scan.nextLine().charAt(0);
        }
    }

    private Avion cargarAvionPorTeclado(String id) {

        Scanner scan = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();
        System.out.print("Distancia: ");
        double distancia = scan.nextDouble();
        scan.nextLine();
        System.out.print("Cantidad de pasajeros: ");
        int cantidad = scan.nextInt(); //////// CHEQUEAR QUE NO SE ROMPA ESTA MIERDA SI PONES UN STRING EN EL INT ************************************
        scan.nextLine();

        return new Avion(id, nombre, distancia, cantidad);
    }

    public void agregarAvion(Avion avion) {
        try {
            if (buscarAvion(avion.getId()) == null) {
                aviones.add(avion);
                System.out.println("AVIÓN AGREGADO CON ÉXITO");
            } else {
                throw new AvionExistenteException();
            }

        } catch (AvionExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarAvion() {
        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICAR AVION");
        System.out.print("ID del avión: ");
        String id = scan.nextLine();
        Avion avion = buscarAvion(id);

        if (avion != null) {
            System.out.println("1. Nombre");
            System.out.println("2. Distancia");
            System.out.println("3. Cantidad de pasajeros");
            System.out.print("Seleccione el campo a modificar: ");
            int opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nuevo nombre: ");
                    String nombre = scan.nextLine();
                    avion.setNombre(nombre);
                    break;

                case 2:
                    System.out.print("Nueva distancia: ");
                    double distancia = scan.nextDouble();
                    scan.nextLine();
                    avion.setDistancia(distancia);
                    break;

                case 3:
                    System.out.print("Nueva cantidad de pasajeros: ");
                    int cantidad = scan.nextInt();
                    scan.nextLine();
                    avion.setCantidadPasajeros(cantidad);
                    break;

                default:
                    System.out.println("ERROR: OPCIÓN INVÁLIDA");
                    break;
            }

            System.out.println("AVIÓN MODIFICADO CON ÉXITO");
        } else {
            System.out.println("ERROR: AVIÓN NO ENCONTRADO");
        }
    }

    public void eliminarAvion(String id) {
        Avion avion = buscarAvion(id);

        try {
            if (avion != null) {
                aviones.remove(avion);
                System.out.println("AVIÓN ELIMINADO CON ÉXITO");
            } else {
                throw new AvionInexistenteException();
            }
        } catch (AvionInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAviones() {
        System.out.println("AVIONES");
        for (Avion avion : aviones) {
            System.out.println(avion.toString());
        }
    }

    private Avion buscarAvion(String id) {
        Avion avion = null;

        for (Avion a : aviones) {
            if (a.getId().equals(id)) {
                avion = a;
            }
        }

        return avion;
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       AEROPUERTOS      |||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    private Aeropuerto buscarAeropuerto(String codigo) {
        Aeropuerto aeropuerto = null;

        for (Aeropuerto a : aeropuertos) {
            if (a.getCodigo().equals(codigo)) {
                aeropuerto = a;
            }
        }

        return aeropuerto;
    }

    public void agregarAeropuertosTeclado() {
        Scanner scan = new Scanner(System.in);
        char continuar = 's';

        while (continuar == 's') {
            boolean aeropuertoExistente = true;

            while (aeropuertoExistente) {
                System.out.print("Codigo: ");
                String codigo = scan.nextLine();
                Aeropuerto aeropuerto = buscarAeropuerto(codigo);
                if (aeropuerto != null) {
                    System.out.println("El aeropuerto ya existe, intente de nuevo cargando uno diferente");
                } else {
                    aeropuertoExistente = false;
                    aeropuerto = cargarAeropuertoPorTeclado(codigo);
                    agregarAeropuerto(aeropuerto);
                }
            }

            System.out.println("Quiere seguir ingresando aeropuertos? 's'/'n'");
            continuar = scan.nextLine().charAt(0);
        }
    }

    private Aeropuerto cargarAeropuertoPorTeclado(String codigo) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Ciudad: ");
        String ciudad = scan.nextLine();
        System.out.print("País: ");
        String pais = scan.nextLine();
        scan.nextLine();

        return new Aeropuerto(codigo, ciudad, pais);
    }

    public void agregarAeropuerto(Aeropuerto aeropuerto) {
        try {
            if (buscarAeropuerto(aeropuerto.getCodigo()) == null) {
                aeropuertos.add(aeropuerto);
                System.out.println("AEROPUERTO AGREGADO CON ÉXITO");
            } else {
                throw new AeropuertoExistenteException();
            }

        } catch (AeropuertoExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarAeropuerto() {
        Scanner scan = new Scanner(System.in);
        System.out.println("MODIFICAR AEROPUERTO");
        System.out.print("Código del aeropuerto: ");
        String codigo = scan.nextLine();
        Aeropuerto aeropuerto = buscarAeropuerto(codigo);

        if (aeropuerto != null) {
            System.out.println("1. Ciudad");
            System.out.println("2. País");
            System.out.print("Seleccione el campo a modificar: ");
            int opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nueva ciudad: ");
                    String ciudad = scan.nextLine();
                    aeropuerto.setCiudad(ciudad);
                    break;

                case 2:
                    System.out.print("Nuevo país: ");
                    String pais = scan.nextLine();
                    scan.nextLine();
                    aeropuerto.setPais(pais);
                    break;

                default:
                    System.out.println("ERROR: OPCIÓN INVÁLIDA");
                    break;
            }

            System.out.println("AEROPUERTO MODIFICADO CON ÉXITO");
        } else {
            System.out.println("ERROR: AEROPUERTO NO ENCONTRADO");
        }
    }

    public void eliminarAeropuerto(String codigo) {
        Aeropuerto aeropuerto = buscarAeropuerto(codigo);
        try {
            if (aeropuerto != null) {
                aeropuertos.remove(aeropuerto);
                System.out.println("AEROPUERTO ELIMINADO CON ÉXITO");
            } else {
                throw new AeropuertoInexistenteException();
            }
        } catch (AeropuertoInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarAeropuerto() {
        System.out.println("AEROPUERTOS");
        for (Aeropuerto aeropuerto: aeropuertos) {
            System.out.println(aeropuerto.toString());
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       CLIENTES          |||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    public void mostrarClientes() { //ADMIN FUNC
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    public boolean existeCliente(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getPasaporte().equals(cliente.getPasaporte())) {
                return true;
            }
        }
        for (Cliente c : clientes) {
            if (c.getNombreDeUsuario().equals(cliente.getNombreDeUsuario())) {
                return true;
            }
        }
        return false;
    }

    public Cliente agregarClientePorTeclado() {
        Scanner scanner = new Scanner(System.in);

        String nombre = "";
        String apellido = "";
        String pasaporte = "";
        String usuario = "";
        String contrasena = "";

        boolean nombreValido = false;
        boolean apellidoValido = false;
        boolean pasaporteValido = false;
        boolean usuarioValido = false;
        boolean contrasenaValida = false;

        while (!nombreValido) {
            System.out.println("Ingrese el nombre del cliente:");
            nombre = scanner.nextLine();

            // Comprobar si el nombre contiene números
            if (nombre.matches(".*\\d.*")) {
                System.out.println("El nombre no puede contener números.");
            } else {
                nombreValido = true;
            }
        }

        while (!apellidoValido) {
            System.out.println("Ingrese el apellido del cliente:");
            apellido = scanner.nextLine();

            // Comprobar si el apellido contiene números
            if (apellido.matches(".*\\d.*")) {
                System.out.println("El apellido no puede contener números.");
            } else {
                apellidoValido = true;
            }
        }

        while (!pasaporteValido) {
            System.out.println("Ingrese el pasaporte del cliente:");
            pasaporte = scanner.nextLine();

            // formato: A1234567)
            if (!pasaporte.matches("[A-Z]\\d{7}")) {
                System.out.println("El pasaporte ingresado no es válido.");
            } else {
                pasaporteValido = true;
            }
        }

        while (!usuarioValido) {
            System.out.println("Ingrese el usuario del cliente:");
            usuario = scanner.nextLine();

            if (usuario.contains(" ")) {
                System.out.println("El usuario no puede contener espacios en blanco.");
            } else {
                usuarioValido = true;
            }
        }

        while (!contrasenaValida) {
            System.out.println("Ingrese la contraseña del cliente:");
            contrasena = scanner.nextLine();

            if (contrasena.length() < 6) {
                System.out.println("La contraseña debe tener al menos 6 caracteres.");
            } else {
                contrasenaValida = true;
            }
        }
        Cliente cliente = new Cliente(nombre, apellido, pasaporte, usuario, contrasena);

        return cliente;
    }
    public void agregarCliente(Cliente cliente) {
        try {
            if (existeCliente(cliente)) {
                throw new ClienteExistenteException();
            }
            clientes.add(cliente);
        } catch (ClienteExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarCliente(String pasaporte) {
        Cliente clienteAEliminar = null;
        for (Cliente cliente : clientes) {
            if (cliente.getPasaporte().equals(pasaporte)) {
                clienteAEliminar = cliente;
                break;
            }
        }
        try {
            if (clienteAEliminar == null) {
                throw new ClienteInexistenteException();
            }
            clientes.remove(clienteAEliminar);
        } catch (ClienteInexistenteException e) {
            System.out.println(e.getMessage());
        }


    }

    public void modificarCliente(String nombreDeUsuario) {
        Cliente clienteAModificar = null;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                if (cliente.getNombreDeUsuario().equals(nombreDeUsuario)) {
                    clienteAModificar = cliente;
                    break;
                }
            }
        }
        if (clienteAModificar == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("¿Qué campo desea modificar?");
        System.out.println("1 - Nombre");
        System.out.println("2 - Apellido");
        System.out.println("3 - Pasaporte");
        System.out.println("4 - Modificar todos los campos");

        int opcion = input.nextInt();
        input.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nuevo nombre para el cliente:");
                String nuevoNombre = input.nextLine();
                clienteAModificar.setNombre(nuevoNombre);
                break;
            case 2:
                System.out.println("Ingrese el nuevo apellido para el cliente:");
                String nuevoApellido = input.nextLine();
                clienteAModificar.setApellido(nuevoApellido);
                break;
            case 3:
                System.out.println("Ingrese el nuevo pasaporte para el cliente:");
                String nuevoPasaporte = input.nextLine();
                clienteAModificar.setPasaporte(nuevoPasaporte);
                break;
            case 4:
                System.out.println("Ingrese el nuevo nombre para el cliente:");
                nuevoNombre = input.nextLine();
                System.out.println("Ingrese el nuevo apellido para el cliente:");
                nuevoApellido = input.nextLine();
                System.out.println("Ingrese el nuevo pasaporte para el cliente:");
                nuevoPasaporte = input.nextLine();
                clienteAModificar.setNombre(nuevoNombre);
                clienteAModificar.setApellido(nuevoApellido);
                clienteAModificar.setPasaporte(nuevoPasaporte);
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       JSON              |||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
//    public void cargarJson(String pathname) {
//        File file = new File(pathname);
//
//        try {
//            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
//
//            Gson gson = new GsonBuilder() // El Gson builder para poder cargar el LocalDateTime que rompe el archivo
//                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//                    .create();
//
//            gson.toJson(this, Aerolinea.class, bufferedWriter);
//
//            bufferedWriter.close();
//        } catch (IOException e) {
//            System.out.println("|X| ERROR CARGANDO AEROLINEA A JSON |X|");
//            System.out.println(e.getMessage());
//        }
//
//    }
//
//    public static Aerolinea leerJson(String pathname) {
//        Aerolinea aerolinea = null;
//        File file = new File(pathname);
//        if (file.exists()) {
//            if (file.canRead()) {
//                try {
//                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
//
//                    Gson gson = new GsonBuilder() // El Gson builder para poder leer el LocalDateTime que rompe el archivo
//                            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
//                            .create();
//
//                    aerolinea = gson.fromJson(bufferedReader, Aerolinea.class);
//
//                    bufferedReader.close();
//                } catch (IOException e) {
//                    System.out.println("|X| ERROR E/S LEYENDO EL JSON |X|");
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                System.out.println("|X| ERROR LEYENDO EL JSON, NO TIENE PERMISOS |X|");
//            }
//        } else {
//            System.out.println("|X| ERROR LEYENDO EL JSON, NO EXISTE EL ARCHIVO |X|");
//        }
//
//        return aerolinea;
//    }
}