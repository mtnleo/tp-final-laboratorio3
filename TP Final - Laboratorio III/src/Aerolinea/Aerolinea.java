package Aerolinea;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Aerolinea {

    ////////////////////////////////////////////
    // ATRIBUTOS
    ////////////////////////////////////////////

    String nombre;
    private final HashMap<String, LinkedList<Vuelo>> vuelos; // TreeMap -> K: Destino | V: LinkedList <Vuelo> (Ordenados por precio)
    private HashSet<Cliente> clientes;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Avion> getAviones() {
        return aviones;
    }

    public ArrayList<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    ////////////////////////////////////////////
    // MÉTODOS ----------------------------
    ////////////////////////////////////////////

    public <T> void mostrarColeccion(Collection<T> coleccion) {
        for (T elemento : coleccion) {
            System.out.println(elemento.toString());
        }
    }

    public LinkedList<Vuelo> obtenerVuelosFechaSalida(LinkedList<Vuelo> vuelosModificar, LocalDateTime minima, LocalDateTime maxima) {
        LinkedList<Vuelo> listaRetornar = new LinkedList<>();

        Iterator<Vuelo> iterator = vuelosModificar.iterator();
        while (iterator.hasNext()) {
            Vuelo vuelo = iterator.next();
            if (vuelo.getSalida().isAfter(minima) && vuelo.getSalida().isBefore(maxima)) {
                listaRetornar.add(vuelo);
            }
        }

        if (listaRetornar.isEmpty()) {
            System.out.println("⚠ No se encontraron vuelos que coincidan con la búsqueda ⚠");
        }

        return listaRetornar;
    }


    public LinkedList<Vuelo> obtenerVuelosOrigen(String ciudadOrigen) {
        LinkedList<Vuelo> listaAgregarVuelos = new LinkedList<Vuelo>();

        String[] keysDestinos = vuelos.keySet().toArray(new String[0]);

        for (String destino: keysDestinos) {
            LinkedList<Vuelo> vuelosPorKey = vuelos.get(destino);

            for (Vuelo vue: vuelosPorKey) {
                if (vue.getOrigen().getCiudad().equals(ciudadOrigen)) {
                    listaAgregarVuelos.add(vue);
                }
            }
        }

        return listaAgregarVuelos;
    }

    public LinkedList<Vuelo> obtenerVuelosDestinoOrigen(String destino, String origen) {
        LinkedList<Vuelo> vuelosDestinoOrigen = new LinkedList<Vuelo>();
        if (vuelos.containsKey(destino)) {
            vuelosDestinoOrigen = new LinkedList<>(vuelos.get(destino)); // asi se pasa por copia (y no por referencia que rompe todoo)
        }
        try {
            if (vuelos.get(destino) != null) { // aca compruebo de nuevo en este caso si es null, que en lo de arriba no puedo porque ya crea la lista por default
                Iterator<Vuelo> iterator = vuelosDestinoOrigen.iterator();
                while (iterator.hasNext()) {
                    Vuelo vuelo = iterator.next();
                    if (!vuelo.getOrigen().getCiudad().equals(origen)) {
                        iterator.remove();
                    }
                }
            }
            else {
                throw new AeropuertoInexistenteException("⚠ Aeropuerto no encontrado ⚠");
            }
        }
        catch (AeropuertoInexistenteException e) {
            System.out.println(e.getMessage());
        }

        return vuelosDestinoOrigen;
    }

    public void mostrarVuelosPorLista(LinkedList<Vuelo> vuelosMostrar) {
        if (!vuelosMostrar.isEmpty()) {
            for (Vuelo vue : vuelosMostrar) {
                System.out.println("----------------");
                System.out.println(vue.toStringCorto());
            }
            System.out.println("----------------");
        }
        else {
            System.out.println("⚠ No se encontraron vuelos ⚠");
        }
    }


    public void agregarVueloTeclado() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- - - - - AGREGAR VUELO - - - - -");
        System.out.print("Código: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {
            if (vuelo == null) {
                vuelo = cargarVueloPorTeclado(codigo);
                agregarVuelo(vuelo);
                // AGREGAR VUELO A LA COLECCION
                System.out.println("⊹ Vuelo agregado con éxito ⊹");
            } else {
                throw new VueloExistenteException("⚠ El vuelo ya existe ⚠");
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

                System.out.println("⊹ Vuelo agregado con éxito ⊹");
            } else {
                throw new VueloExistenteException("⚠ El vuelo " + vuelo.getCodigoVuelo() + " ya existe ⚠");
            }
        } catch (VueloExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public Vuelo buscarVuelo(String codigo) {
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
        System.out.print("Código del aeropuerto de origen: ");
        String codigoOrigen = scan.nextLine();
        Aeropuerto origen = buscarAeropuerto(codigoOrigen);

        try {
            if (origen != null) {
                System.out.print("Código del aeropuerto de destino: ");
                String codigoDestino = scan.nextLine();
                Aeropuerto destino = buscarAeropuerto(codigoDestino);

                if (destino != null) {
                    System.out.print("Fecha y hora de partida (aaaa-mm-dd hh:mm): ");
                    String fechaHora = scan.nextLine();
                    LocalDateTime salida = LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    System.out.print("Distancia: ");
                    double distancia = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Duración en minutos: ");
                    double duracion = scan.nextDouble();
                    scan.nextLine();

                    System.out.print("Avión: ");
                    String codigoAvion = scan.nextLine();
                    Avion avion = buscarAvion(codigoAvion);

                    if (avion != null) {
                        System.out.print("Precio: ");
                        double precio = scan.nextInt();
                        scan.nextLine();

                        vuelo = new Vuelo(codigo, precio, origen, destino, distancia, avion, salida, duracion);
                    } else {
                        throw new AvionInexistenteException("⚠ El avión no existe ⚠");
                    }

                } else {
                    throw new AeropuertoInexistenteException("⚠ El aeropuerto de destino no existe ⚠");
                }
            } else {
                throw new AeropuertoInexistenteException("⚠ El aeropuerto de origen no existe ⚠");
            }
        } catch (AeropuertoInexistenteException | AvionInexistenteException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        return vuelo;
    }

    public void modificarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- - - - - MODIFICAR VUELO - - - - -");
        System.out.print("Código de vuelo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {

            if (vuelo != null) {
                System.out.println("1. Fecha y hora de salida");
                System.out.println("2. Distancia");
                System.out.println("3. Duración");
                System.out.println("4. Avión");
                System.out.println("5. Precio");
                System.out.println("6. Estado del vuelo");
                System.out.print("Seleccione el campo a modificar: ");
                int opcion = scan.nextInt();
                scan.nextLine();

                switch (opcion) {
                    case 1:
                        System.out.print("Nueva fecha y hora de partida (aaaa-mm-dd hh:mm): ");
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
                        System.out.print("Nueva duración: ");
                        double duracion = scan.nextDouble();
                        scan.nextLine();
                        vuelo.setDuracion(duracion);
                        break;

                    case 4:
                        System.out.print("Código del nuevo avión: ");
                        String codigoAvion = scan.nextLine();
                        Avion avion = buscarAvion(codigoAvion);
                        if (avion != null) {
                            vuelo.setAvion(avion);
                        } else {
                            throw new AvionInexistenteException("⚠ Avión no encontrado ⚠");
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
                            System.out.println("⚠ Por favor ingrese un estado válido ⚠");
                        }
                        break;

                    default:
                        System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                        break;
                }

                System.out.println("⊹ Vuelo modificado con éxito ⊹");
            } else {
                throw new VueloInexistenteException("⚠ Vuelo no encontrado ⚠");
            }
        } catch (VueloInexistenteException | AvionInexistenteException | InputMismatchException |
                 java.time.format.DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarVuelo() {
        Scanner scan = new Scanner(System.in);
        System.out.print("\n- - - - - ELIMINAR VUELO - - - - -");
        System.out.print("Código de vuelo: ");
        String codigo = scan.nextLine();
        Vuelo vuelo = buscarVuelo(codigo);

        try {
            if (vuelo != null) {
                LinkedList<Vuelo> vuelosCiudad = vuelos.get(vuelo.getDestino().getCiudad());
                vuelosCiudad.remove(vuelo);
                System.out.println("⊹ Vuelo eliminado con éxito ⊹");
            } else {
                throw new VueloInexistenteException("⚠ Vuelo no encontrado ⚠");
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

    public void agregarAvionesTeclado() {
        Scanner scan = new Scanner(System.in);
        char continuar = 's';

        while (continuar == 's') {
            boolean avionExistente = true;

            while (avionExistente) {
                System.out.print("\nID: ");
                String id = scan.nextLine();
                Avion avion = buscarAvion(id);
                if (avion != null) {
                    System.out.println("⚠ El avión ya existe ⚠");
                } else {
                    avionExistente = false;
                    avion = cargarAvionPorTeclado(id);
                    agregarAvion(avion);
                }
            }

            System.out.print("¿Desea seguir agregando aviones? (s/n): ");
            continuar = scan.nextLine().charAt(0);
        }
    }

    private Avion cargarAvionPorTeclado(String id) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = scan.nextLine();
        double distancia = 0;
        int cantidad = 0;
        boolean inputValido = false;

        while (!inputValido) {
            try {
                System.out.print("Distancia: ");
                distancia = scan.nextDouble();
                scan.nextLine();
                inputValido = true;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                scan.nextLine();
            }
        }

        inputValido = false;

        while (!inputValido) {
            try {
                System.out.print("Cantidad de pasajeros: ");
                cantidad = scan.nextInt();
                inputValido = true;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                scan.nextLine();
            }
        }
        return new Avion(id, nombre, distancia, cantidad);
    }

    public void agregarAvion(Avion avion) {
        try {
            if (buscarAvion(avion.getId()) == null) {
                aviones.add(avion);
                System.out.println("⊹ Avión agregado con éxito ⊹");
            } else {
                throw new AvionExistenteException();
            }

        } catch (AvionExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

public void modificarAvion() {
    Scanner scan = new Scanner(System.in);
    System.out.println("\n- - - - - MODIFICAR AVIÓN - - - - -");
    System.out.print("ID del avión: ");
    String id = scan.nextLine();
    Avion avion = buscarAvion(id);

    if (avion != null) {
        boolean opcionValida = false;
        int opcion = 0;

        while (!opcionValida) {
            try {
                System.out.println("1. Nombre");
                System.out.println("2. Distancia");
                System.out.println("3. Cantidad de pasajeros");
                System.out.print("Seleccione el campo a modificar: ");

                opcion = scan.nextInt();
                scan.nextLine();
                opcionValida = true;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                scan.nextLine();
            }
        }

        switch (opcion) {
            case 1:
                try {
                    System.out.print("Nuevo nombre: ");
                    String nombre = scan.nextLine();
                    avion.setNombre(nombre);
                    System.out.println("⊹ Avión modificado con éxito ⊹");
                } catch (Exception e) {
                    System.out.println("⚠ No se pudo modificar el campo seleccionado ⚠");
                }
                break;

            case 2:
                try {
                    double distancia = leerNumeroDouble(scan, "Nueva distancia: ");
                    avion.setDistancia(distancia);
                    System.out.println("⊹ Avión modificado con éxito ⊹");
                } catch (Exception e) {
                    System.out.println("⚠ No se pudo modificar el campo seleccionado ⚠");
                }
                break;

            case 3:
                try {
                    int cantidad = leerNumeroEntero(scan, "Nueva cantidad de pasajeros: ");
                    avion.setCantidadPasajeros(cantidad);
                    System.out.println("⊹ Avión modificado con éxito ⊹");
                } catch (Exception e) {
                    System.out.println("⚠ No se pudo modificar el campo seleccionado ⚠");
                }
                break;

            default:
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                break;
        }
    } else {
        System.out.println("⚠ Avión no encontrado ⚠");
    }
}

    private double leerNumeroDouble(Scanner scan, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return scan.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                scan.nextLine();
            }
        }
    }

    private int leerNumeroEntero(Scanner scan, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                scan.nextLine();
            }
        }
    }

    public void eliminarAvion(String id) {
        Avion avion = buscarAvion(id);

        try {
            if (avion != null) {
                aviones.remove(avion);
                System.out.println("⊹ Avión eliminado con éxito ⊹");
            } else {
                throw new AvionInexistenteException();
            }
        } catch (AvionInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public Avion buscarAvion(String id) {
        return buscarElementoPorCampo(aviones, "id", id);
    }

    private <T> T buscarElementoPorCampo(Collection<T> coleccion, String campo, String valor) {
        for (T elemento : coleccion) {
            try {
                Field field = elemento.getClass().getDeclaredField(campo);
                field.setAccessible(true);
                Object fieldValue = field.get(elemento);

                if (fieldValue != null && fieldValue.toString().equals(valor)) {
                    return elemento;
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                System.out.println("⚠ El campo ingresado no existe o hay un problema en el acceso ⚠");
                System.out.println(e.getMessage());
            }
        }

        return null;
    }

    ///BUSCAR
    public Aeropuerto buscarAeropuerto(String codigo) { // por codigo de aeropuerto
        return buscarElementoPorCampo(aeropuertos, "codigo", codigo);
    }

///BUSCAR POR CIUDAD
    public Aeropuerto buscarAeropuertoCiudad(String ciudad) { // por ciudad
        Aeropuerto aeropuerto = null;

        for (Aeropuerto a : aeropuertos) {
            if (a.getCiudad().equals(ciudad)) {
                aeropuerto = a;
            }
        }

        return aeropuerto;
    }

    ///CARGAR AEROPUERTO(S)
    public void agregarAeropuertosTeclado() {
        Scanner scan = new Scanner(System.in);
        char continuar = 's';

        while (continuar == 's') {
            boolean aeropuertoExistente = true;

            while (aeropuertoExistente) {
                System.out.print("Código: ");
                String codigo = scan.nextLine();
                Aeropuerto aeropuerto = buscarAeropuerto(codigo);
                if (aeropuerto != null) {
                    System.out.println("⚠ El aeropuerto ya existe ⚠");
                } else {
                    aeropuertoExistente = false;
                    aeropuerto = cargarAeropuertoPorTeclado(codigo);
                    agregarAeropuerto(aeropuerto);
                }
            }

            System.out.print("¿Desea seguir agregando aeropuertos? (s/n): ");
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
                System.out.println("⊹ Aeropuerto agregado con éxito ⊹");
            } else {
                throw new AeropuertoExistenteException();
            }

        } catch (AeropuertoExistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void modificarAeropuerto() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- - - - - MODIFICAR AEROPUERTO - - - - -");
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
                    System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                    break;
            }

            System.out.println("⊹ Aeropuerto modificado con éxito ⊹");
        } else {
            System.out.println("⚠ Aeropuerto no encontrado ⚠");
        }
    }

    public void eliminarAeropuerto(String codigo) {
        Aeropuerto aeropuerto = buscarAeropuerto(codigo);
        try {
            if (aeropuerto != null) {
                aeropuertos.remove(aeropuerto);
                System.out.println("⊹ Aeropuerto eliminado con éxito ⊹");
            } else {
                throw new AeropuertoInexistenteException();
            }
        } catch (AeropuertoInexistenteException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente.toString());
        }
    }

    public boolean existeCliente(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getPasaporte().equalsIgnoreCase(cliente.getPasaporte()) || c.getNombreDeUsuario().equalsIgnoreCase(cliente.getNombreDeUsuario())) {
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
            System.out.print("Nombre: ");
            nombre = scanner.nextLine();

            // Comprobar si el nombre contiene números
            if (nombre.matches(".*\\d.*")) {
                System.out.println("⚠ El nombre no puede contener números ⚠");
            } else {
                nombreValido = true;
            }
        }

        while (!apellidoValido) {
            System.out.print("Apellido: ");
            apellido = scanner.nextLine();

            // Comprobar si el apellido contiene números
            if (apellido.matches(".*\\d.*")) {
                System.out.println("⚠ El apellido no puede contener números ⚠");
            } else {
                apellidoValido = true;
            }
        }

        while (!pasaporteValido) {
            System.out.print("N° de pasaporte (X1234567): ");
            pasaporte = scanner.nextLine();

            // formato: A1234567)
            if (!pasaporte.matches("[A-Z]\\d{7}")) {
                System.out.println("⚠ Pasaporte inválido ⚠");
            } else {
                pasaporteValido = true;
            }
        }

        while (!usuarioValido) {
            System.out.print("Nombre de usuario: ");
            usuario = scanner.nextLine();

            if (usuario.contains(" ")) {
                System.out.println("⚠ El usuario no puede contener espacios ⚠");
            } else {
                usuarioValido = true;
            }
        }

        while (!contrasenaValida) {
            System.out.print("Contraseña: ");
            contrasena = scanner.nextLine();

            if (contrasena.length() < 6) {
                System.out.println("⚠ La contraseña debe tener al menos 6 caracteres ⚠");
            } else {
                contrasenaValida = true;
            }
        }

        return new Cliente(nombre, apellido, pasaporte, usuario, contrasena);
    }
///AGREGAR
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
///ELIMINAR
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
///MODIFICAR
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
            System.out.println("⚠ Cliente no encontrado ⚠");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.println("\n- - - - - MODIFICAR CLIENTE - - - - -");
        System.out.println("1. Nombre");
        System.out.println("2. Apellido");
        System.out.println("3. Pasaporte");
        System.out.println("4. Modificar todos los campos");
        System.out.print("Seleccione el campo a modificar: ");

        int opcion;
        while (true) {
            try {
                opcion = input.nextInt();
                input.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                input.nextLine();
            }
        }

        switch (opcion) {
            case 1:
                while (true) {
                    try {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = input.nextLine();
                        clienteAModificar.setNombre(nuevoNombre);
                        break;
                    } catch (Exception e) {
                        System.out.println("⚠ No se pudo modificar el campo seleccionado ⚠");
                    }
                }
                break;
            case 2:
                while (true) {
                    try {
                        System.out.print("Nuevo apellido: ");
                        String nuevoApellido = input.nextLine();
                        clienteAModificar.setApellido(nuevoApellido);
                        break;
                    } catch (Exception e) {
                        System.out.println("⚠ No se pudo modificar el campo seleccionado ⚠");
                    }
                }
                break;
            case 3:
                while (true) {
                    try {
                        System.out.print("Nuevo N° de pasaporte: ");
                        String nuevoPasaporte = input.nextLine();
                        clienteAModificar.setPasaporte(nuevoPasaporte);
                        break;
                    } catch (Exception e) {
                        System.out.println("⚠ No se pudo modificar el campo seleccionado ⚠");
                    }
                }
                break;
            case 4:
                while (true) {
                    try {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = input.nextLine();
                        System.out.print("Nuevo apellido: ");
                        String nuevoApellido = input.nextLine();
                        System.out.print("Nuevo N° de pasaporte: ");
                        String nuevoPasaporte = input.nextLine();
                        clienteAModificar.setNombre(nuevoNombre);
                        clienteAModificar.setApellido(nuevoApellido);
                        clienteAModificar.setPasaporte(nuevoPasaporte);
                        break;
                    } catch (Exception e) {
                        System.out.println("⚠ No se pudo modificar el campo seleccionado ⚠");
                    }
                }
                break;
            default:
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                break;
        }
    }
///BUSCAR
    public Cliente buscarUsuario(String username) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombreDeUsuario().equals(username)) {
                return cliente;
            }
        }
        return null; // No se encontró el usuario
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||
    //       JSON              |||||||||||||||||||||||||||||||||||||||||||
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||

    ///ESCRITURA
    public void cargarJson(String pathname) {
        File file = new File(pathname);

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            Gson gson = new GsonBuilder() // El Gson builder para poder cargar el LocalDateTime que rompe el archivo
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .create();

            gson.toJson(this, Aerolinea.class, bufferedWriter);

            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("|X| ERROR CARGANDO AEROLINEA A JSON |X|");
            System.out.println(e.getMessage());
        }

    }
///LECTURA
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
                } catch (IOException e) {
                    System.out.println("|X| ERROR E/S LEYENDO EL JSON |X|");
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("|X| ERROR LEYENDO EL JSON, NO TIENE PERMISOS |X|");
            }
        } else {
            System.out.println("|X| ERROR LEYENDO EL JSON, NO EXISTE EL ARCHIVO |X|");
        }

        return aerolinea;
    }

      //////////////////////////////////////////////////
     // VERIFICAR NIVELES ////////////////////////////
    ////////////////////////////////////////////////

    ///ACTUALIZACION
    public void actualizarHashsetSubclases() {
        HashSet<Cliente> setSubclases = new HashSet<Cliente>();

        for (Cliente clien: clientes) {
            double millasCliente = clien.getMillas();
            if (millasCliente < 2500) {
                clien = new Estandar(clien);
            }
            else if (millasCliente < 5000) {
                clien = new Gold(clien);
            }
            else if (millasCliente < 10000) {
                clien = new Platinum(clien);
            }
            else {
                clien = new Black(clien);
            }

            setSubclases.add(clien);
        }

        clientes.clear();
        clientes = setSubclases;
    }

    ///VERIFICAR NIVEL DE SOCIO
    public Cliente verificarNivel (Cliente cliente) {
        if(cliente instanceof Estandar) {
            if(cliente.getMillas() >= 2500) {
                Gold aux = new Gold(cliente);
                clientes.remove(cliente);
                clientes.add(aux);
                System.out.println("⊹ ¡Felicitaciones! Ahora sos socio nivel GOLD ⊹");
                cliente = aux;
            }
        }
        else if(cliente instanceof Gold) {
            if(cliente.getMillas() >= 5000) {
                Platinum aux = new Platinum(cliente);
                clientes.remove(cliente);
                clientes.add(aux);
                System.out.println("⊹ ¡Felicitaciones! Ahora sos socio nivel PLATINUM ⊹");
                cliente = aux;
            }
        }
        else if(cliente instanceof Platinum) {
            if(cliente.getMillas() >= 10000) {
                Black aux = new Black(cliente);
                clientes.remove(cliente);
                clientes.add(aux);
                System.out.println("⊹ ¡Felicitaciones! Ahora sos socio nivel BLACK ⊹");
                cliente = aux;
            }
        }
        return cliente;
    }
///VER EL PERFIL
    public void verPerfil(Cliente cliente) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n- - - - - MI PERFIL - - - - -");
        System.out.println(cliente.getNombre() + " " + cliente.getApellido());
        System.out.println("Pasaporte: " + cliente.getPasaporte());
        System.out.println("Millas: " + cliente.getMillas());

        if (cliente instanceof Estandar) {
            System.out.println("Clase: Estándar");
        } else if (cliente instanceof Gold) {
            System.out.println("Clase: Gold");
        } else if (cliente instanceof Platinum) {
            System.out.println("Clase: Platinum");
        } else if (cliente instanceof Black) {
            System.out.println("Clase: Black");
        }

        System.out.println("1. Cambiar contraseña");
        System.out.println("2. Eliminar mi cuenta");
        System.out.println("3. Volver");
        System.out.print("Ingrese una opción: ");
        int opcion = scan.nextInt();
        scan.nextLine();

        switch (opcion) {
            case 1:
                boolean cont1 = true;
                while (cont1) {
                    System.out.print("Nueva contraseña: ");
                    String nuevaPass = scan.nextLine();
                    System.out.print("Confirme la nueva contraseña: ");
                    if (scan.nextLine().equals(nuevaPass)) {
                        cliente.setContrasena(nuevaPass);
                        System.out.println("⊹ Contraseña modificada con éxito ⊹");
                        cont1 = false;
                    } else {
                        System.out.println("⚠ Las contraseñas deben coincidir ⚠");
                    }
                }
                break;
            case 2:
                clientes.remove(cliente);
                System.out.println("⊹ Usuario eliminado ⊹");
                break;
            case 3:
                break;
            default:
                System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                break;
        }
    }


    public void comprarPasaje(Cliente usuario) {
        Scanner scan = new Scanner(System.in);
        boolean opcionValida = false;

        System.out.println("- - - - - COMPRAR PASAJES - - - - -");
        boolean compra = true;

        while (compra) {
            System.out.print("Ingrese el código de vuelo o presione 0 para salir: ");
            String codigoComprar = scan.nextLine();

            if (!codigoComprar.equals("0")) {

                try {
                    Vuelo vueloToComprar = buscarVuelo(codigoComprar);

                    if (vueloToComprar != null) {
                        double precioVuelo = usuario.obtenerPrecioFinal(vueloToComprar);

                        System.out.println("¿Está segurx de que desea comprar el siguiente vuelo? \n---------------------");
                        System.out.println("Precio = $" + precioVuelo);
                        System.out.println(vueloToComprar.toStringCortoSinPrecio());
                        System.out.print("---------------------\n" + "Presione s para finalizar la compra: ");


                        char confirmarCompra = scan.nextLine().toLowerCase().charAt(0);
                        if (confirmarCompra == 's') {
                            int asientoAsignado = vueloToComprar.comprobarEspacioVuelo(); // retorna 0 si no tiene espacio, cualquier otro numero si tiene

                            if (asientoAsignado != 0) {
                                int cantidadValijas = 0;

                                boolean elegirEquipaje = true;
                                while (elegirEquipaje) {
                                    System.out.print("Seleccione la cantidad de valijas (Máximo 2): ");
                                    System.out.println("0 = Solo carry-on (+ $0)\n" +
                                            "1 = Una valija (+ $70)\n" +
                                            "2 = Dos valijas (+ $140)");

                                    opcionValida = false;
                                    while (!opcionValida) {
                                        try {
                                            cantidadValijas = scan.nextInt();
                                            scan.nextLine();
                                            opcionValida = true;

                                        } catch (InputMismatchException e) {
                                            System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                                            scan.nextLine();
                                        }
                                    }


                                    if (cantidadValijas >= 0 && cantidadValijas <= 2) {
                                        elegirEquipaje = false;
                                    } else {
                                        System.out.println("⚠ Por favor ingrese una opción válida ⚠");
                                    }
                                }

                                Pasaje pasajeNuevo = new Pasaje(vueloToComprar, usuario.getPasaporte(), precioVuelo + (70 * cantidadValijas), cantidadValijas, asientoAsignado, vueloToComprar.getSalida());
                                usuario.agregarPasajeCliente(pasajeNuevo);

                                usuario.setMillas(usuario.getMillas() + vueloToComprar.getAvion().getDistancia() * .05); // SE SUMAN MILLAS + 5% DE LAS DEL VUELO
                                usuario = verificarNivel(usuario);

                                vueloToComprar.setPasajesVendidos(vueloToComprar.getPasajesVendidos() + 1);

                                System.out.println("⊹ Compra exitosa - Podés ver tus pasajes en la sección 'Mis pasajes' ⊹");
                                compra = false;

                            } else {
                                compra = false;
                                System.out.println("⚠ El vuelo seleccionado se encuentra lleno ⚠");
                            }
                        } else {
                            System.out.println("⊹ Compra cancelada ⊹");
                            compra = false;
                        }

                    } else {
                        throw new VueloInexistenteException("⚠ Vuelo no encontrado ⚠");
                    }
                } catch (VueloInexistenteException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                compra = false;
            }
        }
    }
}