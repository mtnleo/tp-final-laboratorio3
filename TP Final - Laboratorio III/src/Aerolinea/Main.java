package Aerolinea;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        /// AEROLINEA DE PRUEBA
        Aerolinea aerolinea = new Aerolinea("Pepito Airlines");
        aerolinea = Aerolinea.leerJson("Aerolinea.json");
        //aerolinea.agregarCliente(new Gold("Ana", "Torres", "P123", "anitaloquita", "anita"));
        aerolinea.actualizarHashsetSubclases();


        Scanner scan = new Scanner(System.in);
        boolean cont = true;

        while (cont) {
            System.out.println("BIENVENIDO A " + aerolinea.getNombre().toUpperCase() + " - - - - - - ✈");
            System.out.println("1. REGISTRARSE");
            System.out.println("2. LOGIN");
            System.out.print("Seleccione una opción o presione 0 para finalizar: ");
            int primeraOpcion = scan.nextInt();
            scan.nextLine();

            if (primeraOpcion != 0) {
                switch (primeraOpcion) {
                    case 1:
                        Cliente clientito = aerolinea.agregarClientePorTeclado(); // lo cargamos
                        try {
                            if (!aerolinea.existeCliente(clientito)) {
                                aerolinea.agregarCliente(new Estandar(clientito)); // ACA CAPAZ QUE HAY QUE AGREGAR LA INICIALIZACION, BUT IDK
                                aerolinea.cargarJson("Aerolinea.json"); // agregarlo al archivo de una
                            } else {
                                throw new ClienteExistenteException("El cliente que intento cargar ya existe.");
                            }
                        } catch (ClienteExistenteException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        int opcionMenu = 0;
                        System.out.print("INGRESE NOMBRE DE USUARIO O PRESIONE 0 PARA FINALIZAR: ");
                        String username = scan.nextLine();

                        Cliente usuario = aerolinea.buscarUsuario(username);

                        if (usuario != null) {
                            System.out.print("INGRESE CONTRASEÑA O PRESIONE 0 PARA FINALIZAR: ");
                            String pass = scan.nextLine();
                            if (usuario.getContrasena().equals(pass)) {
                                opcionMenu = 2;
                            } else if (pass.equals("0")) {
                                cont = false;
                            } else {
                                System.out.println("EL NOMBRE Y CONTRASEÑA NO COINCIDEN. POR FAVOR VUELVA A INTENTARLO.");
                            }
                        } else if (username.equals("admin")) {
                            System.out.print("INGRESE CONTRASEÑA O PRESIONE 0 PARA FINALIZAR: ");
                            String pass = scan.nextLine();
                            if (pass.equals("admin")) {
                                opcionMenu = 1;
                            } else if (pass.equals("0")) {
                                cont = false;
                            } else {
                                System.out.println("EL NOMBRE Y CONTRASEÑA NO COINCIDEN. POR FAVOR VUELVA A INTENTARLO.");
                            }
                        } else if (username.equals("0")) {
                            cont = false;
                        } else {
                            System.out.println("EL NOMBRE DE USUARIO NO EXISTE. POR FAVOR VUELVA A INTENTARLO.");
                            cont = false;
                        }

                        while (opcionMenu != 0) {
                            switch (opcionMenu) {
                                case 1:
                                    boolean cAdmin = true;
                                    while (cAdmin) {
                                        System.out.println("1. Gestionar vuelos");
                                        System.out.println("2. Gestionar aviones");
                                        System.out.println("3. Gestionar aeropuertos");
                                        System.out.println("4. Gestionar clientes");
                                        System.out.println("5. Salir y guardar cambios");
                                        System.out.print("Seleccione una opción: ");
                                        int opcionPrincipal = scan.nextInt();
                                        scan.nextLine();
                                        switch (opcionPrincipal) {
                                            case 1:
                                                boolean cVuelos = true;
                                                while (cVuelos) {
                                                    System.out.println("1. Agregar vuelo");
                                                    System.out.println("2. Modificar vuelo");
                                                    System.out.println("3. Eliminar vuelo");
                                                    System.out.println("4. Mostrar lista de vuelos");
                                                    System.out.println("5. Buscar vuelo por codigo");
                                                    System.out.println("6. Volver");
                                                    System.out.println("Seleccione una opción: ");
                                                    int opcionVuelos = scan.nextInt();
                                                    scan.nextLine();

                                                    switch (opcionVuelos) {
                                                        case 1:
                                                            aerolinea.agregarVueloTeclado();
                                                            break;
                                                        case 2:
                                                            aerolinea.modificarVuelo();
                                                            break;
                                                        case 3:
                                                            aerolinea.eliminarVuelo();
                                                            break;
                                                        case 4:
                                                            aerolinea.mostrarVuelos();
                                                            break;
                                                        case 5:
                                                            System.out.print("Escribe el codigo del vuelo: ");
                                                            String adminBuscarVuelo = scan.nextLine();

                                                            try {
                                                                Vuelo adminVueloBuscado = aerolinea.buscarVuelo(adminBuscarVuelo);

                                                                if (adminVueloBuscado != null) {
                                                                    System.out.println("----------------------");
                                                                    System.out.println(adminVueloBuscado.toString());
                                                                    System.out.println("----------------------");
                                                                } else {
                                                                    throw new VueloInexistenteException("El vuelo buscado no se encontro.");
                                                                }
                                                            } catch (VueloInexistenteException e) {
                                                                System.out.println(e.getMessage());
                                                            }

                                                            break;
                                                        case 6:
                                                            cVuelos = false;
                                                            break;
                                                        default:
                                                            System.out.println("ERROR: OPCIÓN INVÁLIDA");
                                                            break;
                                                    }
                                                }
                                                break;

                                            case 2:
                                                boolean cAviones = true;
                                                while (cAviones) {
                                                    System.out.println("1. Agregar avión");
                                                    System.out.println("2. Modificar avión");
                                                    System.out.println("3. Eliminar avión");
                                                    System.out.println("4. Mostrar lista de aviones");
                                                    System.out.println("5. Volver");
                                                    System.out.println("Seleccione una opción:");
                                                    int opcionAviones = scan.nextInt();
                                                    scan.nextLine();

                                                    switch (opcionAviones) {
                                                        case 1:
                                                            aerolinea.agregarAvionesTeclado();
                                                            break;
                                                        case 2:
                                                            aerolinea.modificarAvion();
                                                            break;
                                                        case 3:
                                                            System.out.print("Ingrese la ID del avion a eliminar: ");
                                                            String idEliminar = scan.nextLine();
                                                            aerolinea.eliminarAvion(idEliminar);
                                                            break;
                                                        case 4:
                                                            aerolinea.mostrarAviones();
                                                            break;
                                                        case 5:
                                                            cAviones = false;
                                                            break;
                                                        default:
                                                            System.out.println("ERROR: OPCIÓN INVÁLIDA");
                                                            break;
                                                    }
                                                }
                                                break;

                                            case 3:
                                                boolean cAeropuertos = true;
                                                while (cAeropuertos) {
                                                    System.out.println("1. Agregar aeropuerto");
                                                    System.out.println("2. Modificar aeropuerto");
                                                    System.out.println("3. Eliminar aeropuerto");
                                                    System.out.println("4. Mostrar lista de aeropuertos");
                                                    System.out.println("5. Volver");
                                                    System.out.println("Seleccione una opción:");
                                                    int opcionAeropuertos = scan.nextInt();
                                                    scan.nextLine();

                                                    switch (opcionAeropuertos) {
                                                        case 1:
                                                            aerolinea.agregarAeropuertosTeclado();
                                                            break;
                                                        case 2:
                                                            aerolinea.modificarAeropuerto();
                                                            break;
                                                        case 3:
                                                            System.out.print("Ingrese el código del aeropuerto a eliminar: ");
                                                            String codigoEliminar = scan.nextLine();
                                                            aerolinea.eliminarAeropuerto(codigoEliminar);
                                                            break;
                                                        case 4:
                                                            aerolinea.mostrarAeropuerto();
                                                            break;
                                                        case 5:
                                                            cAeropuertos = false;
                                                            break;
                                                        default:
                                                            System.out.println("ERROR: OPCIÓN INVÁLIDA");
                                                            break;
                                                    }
                                                }
                                                break;

                                            case 4:
                                                boolean cClientes = true;
                                                while (cClientes) {
                                                    System.out.println("1. Agregar cliente");
                                                    System.out.println("2. Modificar cliente");
                                                    System.out.println("3. Eliminar cliente");
                                                    System.out.println("4. Mostrar lista de clientes");
                                                    System.out.println("5. Volver");
                                                    System.out.println("Seleccione una opción:");
                                                    int opcionClientes = scan.nextInt();

                                                    switch (opcionClientes) {
                                                        case 1:
                                                            System.out.println("Agregar cliente");
                                                            Cliente cliente = aerolinea.agregarClientePorTeclado();
                                                            try {
                                                                aerolinea.existeCliente(cliente);
                                                            } catch (ClienteExistenteException e) {
                                                                e.printStackTrace();
                                                            }
                                                            aerolinea.agregarCliente(new Estandar(cliente));
                                                            break;
                                                        case 2:
                                                            System.out.println("Modificar cliente");
                                                            scan.nextLine();
                                                            System.out.println("Ingrese el nombre de usuario del cliente a modificar:");
                                                            String nombreUsuarioModificar = scan.nextLine();
                                                            aerolinea.modificarCliente(nombreUsuarioModificar);

                                                            break;
                                                        case 3:
                                                            scan.nextLine();
                                                            System.out.println("Eliminar cliente");
                                                            System.out.println("Ingrese el pasaporte del cliente a eliminar:");
                                                            String pasaporteEliminar = scan.nextLine();
                                                            aerolinea.eliminarCliente(pasaporteEliminar);
                                                            break;

                                                        case 4:
                                                            System.out.println("Mostrar cliente");

                                                            aerolinea.mostrarClientes();

                                                            break;
                                                        case 5:
                                                            cClientes = false;
                                                            break;

                                                        default:
                                                            System.out.println("ERROR: OPCIÓN INVÁLIDA");
                                                            break;
                                                    }
                                                }
                                                break;

                                            case 5:
                                                aerolinea.cargarJson("Aerolinea.json");
                                                cAdmin = false;
                                                opcionMenu = 0;
                                                break;

                                            default:
                                                System.out.println("ERROR: OPCIÓN INVÁLIDA");
                                                break;
                                        }
                                    }
                                    break;

                                case 2:
                                    System.out.println("USER");
                                    boolean cUser = true;
                                    while (cUser) {
                                        System.out.println("1. Buscar Vuelos");
                                        System.out.println("2. Comprar Pasaje");
                                        System.out.println("3. Mis pasajes");
                                        System.out.println("4. Ver estado de vuelo");
                                        System.out.println("5. Mi perfil");
                                        System.out.println("6. Socios");
                                        System.out.println("7. Guardar y salir");

                                        int menuUser = scan.nextInt();
                                        scan.nextLine();

                                        switch (menuUser) {
                                            case 1:
                                                boolean cBuscarVuelos = true;

                                                while (cBuscarVuelos) {
                                                    try {
                                                        System.out.print("Escriba la ciudad de origen: ");
                                                        String ciudadOrigen = scan.nextLine();

                                                        Aeropuerto origen = aerolinea.buscarAeropuertoCiudad(ciudadOrigen);
                                                        if (origen != null) {

                                                            LinkedList<Vuelo> listaMostrarVuelos = aerolinea.obtenerVuelosOrigen(ciudadOrigen);

                                                            boolean cFiltrarVuelos = true;
                                                            // ya tengo el aeropuerto de origen para realizar todas las busquedas
                                                            while (cFiltrarVuelos) {
                                                                System.out.println("1. Filtrar por destino");
                                                                System.out.println("2. Filtrar por fecha");
                                                                System.out.println("3. Ordenar por precio");
                                                                System.out.println("4. Buscar vuelo por codigo");
                                                                System.out.println("5. Volver atras");

                                                                int buscarVueloMenu = scan.nextInt();
                                                                scan.nextLine();

                                                                switch (buscarVueloMenu) {
                                                                    case 1:
                                                                        System.out.print("Escriba la ciudad donde quiera viajar: ");
                                                                        String ciudadDestino = scan.nextLine();

                                                                        listaMostrarVuelos = aerolinea.obtenerVuelosDestinoOrigen(ciudadDestino, ciudadOrigen);
                                                                        if (listaMostrarVuelos != null) {
                                                                            aerolinea.mostrarVuelosPorLista(listaMostrarVuelos);
                                                                        } else {
                                                                            System.out.println("No se encontraron vuelos.");
                                                                            listaMostrarVuelos = aerolinea.obtenerVuelosOrigen(ciudadOrigen); // se carga de nuevo la lista para que pueda seguir buscando
                                                                        }

                                                                        break;

                                                                    case 2:
                                                                        try {
                                                                            System.out.print("Escriba la fecha minima (d/m/AAAA): ");
                                                                            String fechaMinima = scan.nextLine();
                                                                            LocalDate fechaMinimaLD = LocalDate.parse(fechaMinima, DateTimeFormatter.ofPattern("d/M/yyyy"));

                                                                            System.out.print("Escriba la fecha maxima (d/m/AAAA): ");
                                                                            String fechaMaxima = scan.nextLine();
                                                                            LocalDate fechaMaximaLD = LocalDate.parse(fechaMaxima, DateTimeFormatter.ofPattern("d/M/yyyy"));

                                                                            LocalDateTime fechaMinimaLDT = fechaMinimaLD.atStartOfDay();
                                                                            LocalDateTime fechaMaximaLDT = fechaMaximaLD.atTime(23, 59, 59);

                                                                            listaMostrarVuelos = aerolinea.obtenerVuelosFechaSalida(listaMostrarVuelos, fechaMinimaLDT, fechaMaximaLDT);
                                                                            aerolinea.mostrarVuelosPorLista(listaMostrarVuelos);

                                                                        } catch (DateTimeParseException e) {
                                                                            System.out.println("El formato de fecha ingresado es inválido.");
                                                                            System.out.println(e.getMessage());
                                                                        }

                                                                        break;

                                                                    case 3:
                                                                        System.out.println("3. Ordenar por precio");
                                                                        TreeSet<Vuelo> setMostrarVuelos = new TreeSet<Vuelo>(listaMostrarVuelos); // lo paso a un treeset para que lo ordene

                                                                        listaMostrarVuelos = new LinkedList<Vuelo>(setMostrarVuelos); // lo paso a la lista de nuevo para que si sigue filtrando ya queden ordenados

                                                                        aerolinea.mostrarVuelosPorLista(listaMostrarVuelos);

                                                                        break;

                                                                    case 4:
                                                                        System.out.print("Escriba el codigo del vuelo buscado: ");
                                                                        String codigoVueloBuscado = scan.nextLine();

                                                                        try {
                                                                            Vuelo vueloBuscadoPorCodigo = aerolinea.buscarVuelo(codigoVueloBuscado);
                                                                            if (vueloBuscadoPorCodigo != null) {
                                                                                System.out.println("-------------------");
                                                                                System.out.println(vueloBuscadoPorCodigo.toString());
                                                                                System.out.println("-------------------");
                                                                            } else {
                                                                                throw new VueloInexistenteException("El codigo del vuelo ingresado no corresponde a ningun vuelo cargado.");
                                                                            }
                                                                        } catch (VueloInexistenteException e) {
                                                                            System.out.println(e.getMessage());
                                                                        }

                                                                        break;

                                                                    case 5:
                                                                        cFiltrarVuelos = false;
                                                                        cBuscarVuelos = false;
                                                                        break;
                                                                }
                                                            }

                                                        } else {
                                                            throw new AeropuertoInexistenteException("El aeropuerto de origen ingresado no se encontro.");
                                                        }
                                                    } catch (AeropuertoInexistenteException e) {
                                                        System.out.println(e.getMessage());
                                                    }

                                                }
                                                break;

                                            case 2:
                                                System.out.println("------------------ Comprar Pasaje ------------------");
                                                boolean compra = true;

                                                while (compra) {
                                                    System.out.println("Escriba el codigo del vuelo que quiera comprar ('0' para salir): ");
                                                    String codigoComprar = scan.nextLine();

                                                    if (!codigoComprar.equals("0")) {

                                                        try {
                                                            Vuelo vueloToComprar = aerolinea.buscarVuelo(codigoComprar);

                                                            if (vueloToComprar != null) {
                                                                System.out.println("Es este el vuelo que quiere comprar? \n---------------------");
                                                                System.out.println(vueloToComprar.toStringCorto());
                                                                System.out.println("---------------------\n" + "Presione 's' para finalizar la compra.");


                                                                char confirmarCompra = scan.nextLine().toLowerCase().charAt(0);
                                                                if (confirmarCompra == 's') {
                                                                    int asientoAsignado = vueloToComprar.comprobarEspacioVuelo(); // retrona 0 si no tiene espacio, cualquier otro numero si tiene

                                                                    if (asientoAsignado != 0) {
                                                                        int cantidadValijas = 0;

                                                                        boolean elegirEquipaje = true;
                                                                        while (elegirEquipaje) {
                                                                            System.out.println("Seleccione la cantidad de valijas (maximo 2): ");
                                                                            System.out.println("0 = Solo carry-on (+ $0)\n" +
                                                                                    "1 = Una valija (+ $70)\n" +
                                                                                    "2 = Dos valijas (+ $140)");

                                                                            cantidadValijas = scan.nextInt();
                                                                            scan.nextLine();

                                                                            if (cantidadValijas >= 0 && cantidadValijas <= 2) {
                                                                                elegirEquipaje = false;
                                                                            } else {
                                                                                System.out.println("El numero elegido no es valido, intente nuevamente.");
                                                                            }
                                                                        }

                                                                        Pasaje pasajeNuevo = new Pasaje(vueloToComprar, usuario.getPasaporte(), vueloToComprar.getPrecio() + (70 * cantidadValijas), cantidadValijas, asientoAsignado, vueloToComprar.getSalida());
                                                                        usuario.agregarPasajeCliente(pasajeNuevo);

                                                                        usuario.setMillas(usuario.getMillas() + vueloToComprar.getAvion().getDistancia() * .05); // SE SUMAN MILLAS + 5% DE LAS DEL VUELO
                                                                        aerolinea.verificarNivel(usuario);

                                                                        vueloToComprar.setPasajesVendidos(vueloToComprar.getPasajesVendidos() + 1);

                                                                        System.out.println("COMPRA EXITOSA, PUEDES VER TUS PASAJES EN 'MIS PASAJES'");
                                                                        compra = false;

                                                                    } else {
                                                                        compra = false;
                                                                        System.out.println("Se ha producido un error, el vuelo seleccionado esta lleno. Por favor selecciona otro");
                                                                    }
                                                                } else {
                                                                    System.out.println("COMPRA CANCELADA.");
                                                                    compra = false;
                                                                }

                                                            } else {
                                                                throw new VueloInexistenteException("El codigo del vuelo ingresado no corresponde a ningun vuelo disponible.");
                                                            }
                                                        } catch (VueloInexistenteException e) {
                                                            System.out.println(e.getMessage());
                                                        }
                                                    } else {
                                                        compra = false;
                                                    }
                                                }

                                                break;

                                            case 3:
                                                System.out.println("------------------- Mis Pasajes -------------------");

                                                usuario.mostrarPasajes();

                                                break;

                                            case 4:
                                                System.out.println("------------------- Ver estado de vuelo -------------------");

                                                System.out.print("Escribe el codigo del vuelo: ");
                                                String codigoVueloVerEstado = scan.nextLine();

                                                try {
                                                    Vuelo vueloVerEstado = aerolinea.buscarVuelo(codigoVueloVerEstado);
                                                    if (vueloVerEstado != null) {
                                                        System.out.println(vueloVerEstado.toStringCorto());
                                                        System.out.println("ESTADO: " + vueloVerEstado.getEstadoVuelo());
                                                    } else {
                                                        throw new VueloInexistenteException("El vuelo buscado no se encontro.");
                                                    }
                                                } catch (VueloInexistenteException e) {
                                                    System.out.println(e.getMessage());
                                                }

                                                break;

                                            case 5:
                                                Cliente c = aerolinea.buscarUsuario(username);
                                                aerolinea.verPerfil(c);
                                                c = aerolinea.buscarUsuario(username);
                                                if(c == null) {
                                                    cUser = false;
                                                    opcionMenu = 0;
                                                }

                                                break;

                                            case 6:
                                                usuario.mostrarInterfazSocio();
                                                break;

                                            case 7:
                                                aerolinea.cargarJson("Aerolinea.json"); // agregarlo al archivo de una
                                                cUser = false;
                                                opcionMenu = 0;
                                                break;

                                            default:
                                                System.out.println("Ingrese una opcion valida");
                                                break;
                                        }
                                    }

                                    break;
                                case 3:
                                    System.out.println("Gracias vuelva prontos");
                                    cont = false;
                                    break;
                            }
                        }
                    default:
                        break;
                }
            } else {
                cont = false;
            }

        }
    }
}