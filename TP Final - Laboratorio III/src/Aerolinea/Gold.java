package Aerolinea;

import java.util.LinkedList;

public class Gold extends Cliente implements Socio {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private final int porcentajeDescuento;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Gold(Cliente cliente) {
        super(cliente);
        this.porcentajeDescuento = 5;
    }

    public Gold(String nombre, String apellido, String pasaporte) {
        super(nombre, apellido, pasaporte);
        this.porcentajeDescuento = 5;
    }

    public Gold(String nombre, String apellido, String pasaporte, String nombreDeUsuario, String contrasena) {
        super(nombre, apellido, pasaporte, nombreDeUsuario, contrasena);
        this.porcentajeDescuento = 5;
    }

    public Gold(String nombre, String apellido, String pasaporte, LinkedList<Pasaje> pasajes) {
        super(nombre, apellido, pasaporte, pasajes);
        this.porcentajeDescuento = 5;
    }

    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////

    @Override
    public void mostrarBenificiosSocio() {
        System.out.println("---------- SOCIO -----------");
        System.out.println("Nivel: Gold");
        System.out.println("Descuento en pasajes: " + porcentajeDescuento + "%");
        System.out.println("Millas hasta el proximo nivel: " + (5000 - getMillas()));
    }

    @Override
    public double calcularPrecioDescuento(double precioOriginal) {
        return precioOriginal - precioOriginal * (porcentajeDescuento / 100.0);
    }
}
