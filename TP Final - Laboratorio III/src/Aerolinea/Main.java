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

        // HARDCODE PARA VER QUE FUNQUE TODOO
        aerolinea.addVuelosHC(); // borrar despues
        // borrarrrrrrrrrrrrrrrrr

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

                    switch (opcionAeropuertos) {
                        case 1:
                            break;
                        case 2:
                            System.out.println("Modificar aeropuerto");
                            break;
                        case 3:
                            System.out.println("Eliminar aeropuerto");
                            break;
                        case 4:
                            System.out.println("Mostrar aeropuerto");
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
                            break;
                        case 2:
                            System.out.println("Modificar cliente");
                            break;
                        case 3:
                            System.out.println("Eliminar cliente");
                            break;
                        case 4:
                            System.out.println("Mostrar cliente");
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