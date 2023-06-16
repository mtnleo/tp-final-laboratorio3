package Aerolinea;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String cont = "y";
        Scanner scan = new Scanner(System.in);
        int num_ej;
        Aerolinea aerolinea = new Aerolinea();

        // MENÚ ADMIN
        System.out.println("Seleccione una opción:");
        System.out.println("1. Gestionar vuelos");
        System.out.println("2. Gestionar aviones");

        int opcionPrincipal = scan.nextInt();

        switch (opcionPrincipal) {
            case 1:
                System.out.println("Seleccione una opción:");
                System.out.println("1. Agregar vuelo");
                System.out.println("2. Modificar vuelo");
                System.out.println("3. Eliminar vuelo");
                System.out.println("4. Mostrar lista de vuelos");

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
                                System.out.println("Opción inválida");
                                break;
                        }
                        break;

                    case 2:
                        System.out.println("Seleccione una opción:");
                        System.out.println("1. Agregar avión");
                        System.out.println("2. Modificar avión");
                        System.out.println("3. Eliminar avión");
                        System.out.println("4. Mostrar lista de aviones");

                case 6:
                    System.out.println("Cargar JSON prueba");
                    Aerolinea aJson = new Aerolinea();
                    aJson.addVuelosHC();
                    aJson.cargarJson("Aerolinea.json");
                    System.out.println("Leer de JSON prueba");
                    Aerolinea aLeerJson = Aerolinea.leerJson("Aerolinea.json");
                    aLeerJson.mostrarVuelos();

                default:
                    System.out.println("|X| ESCRIBA UN EJERCICIO VALIDO |X|");
                    break;

                        /*switch (opcionAviones) {
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
                                System.out.println("Opción inválida");
                                break;
                        }
                        break;

                    default:
                        System.out.println("Opción inválida");
                        break;*/
                }

//        // MENÚ USUARIO
//
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