package Aerolinea;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String cont = "y";
        Scanner scan = new Scanner(System.in);
        int num_ej;

        while (cont.equals("y")) {
            System.out.println("Escriba el ejercicio que quieras realizar: ");
            num_ej = scan.nextInt();
            scan.nextLine();

            switch (num_ej) {
                case 1:
                    System.out.println("Ejercicio 1.");

                    Aerolinea a1 = new Aerolinea();
                    a1.addVuelosHC();
                    a1.mostrarVuelos();

                    break;

                case 2:
                    System.out.println("Ejercicio 2.");

                    break;

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

            }

            System.out.println("--- Deseas continuar viendo ejercicios? (y/n)");
            cont = scan.nextLine();

        }

        scan.close();
    }

}