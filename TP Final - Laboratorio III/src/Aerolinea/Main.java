package Aerolinea;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        boolean cont = true;
        Scanner scan = new Scanner(System.in);

        /// AEROLINEA DE PRUEBA
        Aerolinea aerolinea = new Aerolinea("Pepito Airlines");
        aerolinea.addVuelosHC();

        while (cont) {
            System.out.println("1. MENU ADMIN");
            System.out.println("2. MENU USER");
            System.out.println("3. SALIR");

            int opcionMenu = scan.nextInt();
            scan.nextLine();

            switch (opcionMenu) {

                case 1:

                    boolean cAdmin = true;

                    while (cAdmin) {

                        // MENÚ ADMIN
                        System.out.println("1. Gestionar vuelos");
                        System.out.println("2. Gestionar aviones");
                        System.out.println("3. Gestionar aeropuertos");
                        System.out.println("4. Gestionar clientes");
                        System.out.println("5. Salir");
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
                                    System.out.println("5. Volver");
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
                                while(cClientes){
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
                                            aerolinea.agregarCliente(cliente);
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
                                cAdmin = false;
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
                        System.out.println("7. Atras");

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
                                            boolean cFiltrarVuelos = true;
                                            // ya tengo el aeropuerto de origen para realizar todas las busquedas
                                            while (cFiltrarVuelos) {
                                                System.out.println("1. Filtrar por destino");
                                                System.out.println("2. Filtrar por fecha");
                                                System.out.println("3. Ordenar por precio");
                                                System.out.println("4. Volver atras");

                                                int buscarVueloMenu = scan.nextInt();
                                                scan.nextLine();

                                                LinkedList<Vuelo> listaMostrarVuelos = aerolinea.obtenerVuelosOrigen(ciudadOrigen);

                                                switch (buscarVueloMenu) {
                                                    case 1:
                                                        System.out.print("Escriba la ciudad donde quiera viajar: ");
                                                        String ciudadDestino = scan.nextLine();

                                                        listaMostrarVuelos = aerolinea.obtenerVuelosDestinoOrigen(ciudadDestino, ciudadOrigen);
                                                        if (listaMostrarVuelos != null) {
                                                            aerolinea.mostrarVuelosPorLista(listaMostrarVuelos);
                                                        }
                                                        else {
                                                            System.out.println("No se encontraron vuelos.");
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

                                                        }
                                                        catch (DateTimeParseException e) {
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
                                                        cFiltrarVuelos = false;
                                                        cBuscarVuelos = false;
                                                        break;
                                                }
                                            }

                                        }
                                        else {
                                            throw new AeropuertoInexistenteException("El aeropuerto de origen ingresado no se encontro.");
                                        }
                                    } catch (AeropuertoInexistenteException e) {
                                        System.out.println(e.getMessage());
                                    }

                                }
                                break;

                            case 2:
                                System.out.println("2. Comprar Pasaje");
                                break;

                            case 3:
                                System.out.println("3. Mis Pasajes");
                                break;

                            case 4:
                                System.out.println("4. Ver estado de vuelo");
                                break;

                            case 5:
                                System.out.println("5. Mi perfil");
                                break;

                            case 6:
                                System.out.println("6. Socios");
                                break;

                            case 7:
                                cUser = false;
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

    }
}