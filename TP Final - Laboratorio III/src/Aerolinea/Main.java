package Aerolinea;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean cont = true;
        Scanner scan = new Scanner(System.in);
        int num_ej;

        /// AEROLINEA DE PRUEBA
        Aerolinea aerolinea = new Aerolinea("Pepito Airlines");
        aerolinea.addVuelosHC();

        while (cont) {

            // MENÚ ADMIN
            System.out.println("1. Gestionar vuelos");
            System.out.println("2. Gestionar aviones");
            System.out.println("3. Gestionar aeropuertos");
            System.out.println("4. Gestionar clientes");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcionPrincipal = scan.nextInt();

            switch (opcionPrincipal) {
                case 1:
                    boolean cVuelos = true;
                    while(cVuelos) {
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
                    }}
                    break;

                case 2:
                    boolean cAviones = true;
                    while(cAviones){
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
                    }}
                    break;

                case 3:
                    boolean cAeropuertos = true;
                    while(cAeropuertos){
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
                    }}
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
                    }}
                    break;

                case 5:
                    cont = false;
                    break;

                default:
                    System.out.println("ERROR: OPCIÓN INVÁLIDA");
                    break;
            }
        }
//        // MENÚ USUARIO
//        while (cont.equals("y")) {
//            System.out.println("Escriba el ejercicio que quieras realizar: ");
//            num_ej = scan.nextInt();
//            scan.nextLine();
//
//            switch (num_ej) {
//                case 1:
//                    System.out.println("Ejercicio 1.");
//
//                    Aerolinea a1 = new Aerolinea();
//                    a1.addVuelosHC();
//                    a1.mostrarVuelos();
//
//                    break;
//
//                case 2:
//                    System.out.println("Ejercicio 2.");
//
//                    break;
//
//                default:
//                    System.out.println("|X| ESCRIBA UN EJERCICIO VALIDO |X|");
//
//                    break;
//
//            }
//
//            System.out.println("--- Deseas continuar viendo ejercicios? (y/n)");
//            cont = scan.nextLine();
//
//        }
//
//        scan.close();
    }
}