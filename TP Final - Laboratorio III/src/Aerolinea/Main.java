package Aerolinea;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String cont = "y";
        Scanner scan = new Scanner(System.in);
        int num_ej;
        Aerolinea aerolinea = new Aerolinea("Pepito Airlines");

        // MENÚ ADMIN
        System.out.println("1. Gestionar vuelos");
        System.out.println("2. Gestionar aviones");
        System.out.println("3. Gestionar aeropuertos");
        System.out.println("4. Gestionar clientes");
        System.out.print("Seleccione una opción: ");
        int opcionPrincipal = scan.nextInt();

        switch (opcionPrincipal) {
            case 1:
                System.out.println("1. Agregar vuelo");
                System.out.println("2. Modificar vuelo");
                System.out.println("3. Eliminar vuelo");
                System.out.println("4. Mostrar lista de vuelos");
                System.out.println("Seleccione una opción: ");
                int opcionVuelos = scan.nextInt();

                switch (opcionVuelos) {
                    case 1:
                        aerolinea.agregarVuelo();
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
                    default:
                        System.out.println("ERROR: OPCIÓN INVÁLIDA");
                        break;
                }
                break;

            case 2:
                System.out.println("1. Agregar avión");
                System.out.println("2. Modificar avión");
                System.out.println("3. Eliminar avión");
                System.out.println("4. Mostrar lista de aviones");
                System.out.println("Seleccione una opción:");
                int opcionAviones = scan.nextInt();

                switch (opcionAviones) {
                    case 1:
                        aerolinea.agregarAvion();
                        break;
                    case 2:
                        aerolinea.modificarAvion();
                        break;
                    case 3:
                        aerolinea.eliminarAvion();
                        break;
                    case 4:
                        aerolinea.mostrarAviones();
                        break;
                    default:
                        System.out.println("ERROR: OPCIÓN INVÁLIDA");
                        break;
                }
                break;

            case 3:
                System.out.println("1. Agregar aeropuerto");
                System.out.println("2. Modificar aeropuerto");
                System.out.println("3. Eliminar aeropuerto");
                System.out.println("4. Mostrar lista de aeropuertos");
                System.out.println("Seleccione una opción:");
                int opcionAeropuertos = scan.nextInt();

                switch (opcionAeropuertos) {
                    case 1:
                        System.out.println("Agregar aeropuerto");
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
                    default:
                        System.out.println("ERROR: OPCIÓN INVÁLIDA");
                        break;
                }
                break;

            case 4:
                System.out.println("1. Agregar cliente");
                System.out.println("2. Modificar cliente");
                System.out.println("3. Eliminar cliente");
                System.out.println("4. Mostrar lista de clientes");
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
                    default:
                        System.out.println("ERROR: OPCIÓN INVÁLIDA");
                        break;
                }
                break;

            default:
                System.out.println("ERROR: OPCIÓN INVÁLIDA");
                break;
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