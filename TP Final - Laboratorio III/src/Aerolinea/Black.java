package Aerolinea;

import java.util.LinkedList;

public class Black extends Cliente implements Socio {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private final int porcentajeDescuento;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Black(Cliente cliente) {
        super(cliente);
        this.porcentajeDescuento = 30;
    }

    public Black(String nombre, String apellido, String pasaporte) {
        super(nombre, apellido, pasaporte);
        this.porcentajeDescuento = 30;
    }

    public Black(String nombre, String apellido, String pasaporte, String nombreDeUsuario, String contrasena) {
        super(nombre, apellido, pasaporte, nombreDeUsuario, contrasena);
        this.porcentajeDescuento = 30;
    }

    public Black(String nombre, String apellido, String pasaporte, LinkedList<Pasaje> pasajes) {
        super(nombre, apellido, pasaporte, pasajes);
        this.porcentajeDescuento = 30;
    }

    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////

    @Override
    public void mostrarBenificiosSocio() {
        System.out.println("---------- SOCIO -----------");
        System.out.println("Nivel: Black");
        System.out.println("Descuento en pasajes: " + porcentajeDescuento + "%");
        System.out.println("Felicitaciones! Estas en el nivel mas alto de todos");
    }

    @Override
    public double calcularPrecioDescuento(double precioOriginal) {
        return precioOriginal - precioOriginal * (porcentajeDescuento / 100.0);
    }
}