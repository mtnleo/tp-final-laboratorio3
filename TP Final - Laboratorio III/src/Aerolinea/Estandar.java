package Aerolinea;

import java.util.LinkedList;

public class Estandar extends Cliente {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private double porcentajeDescuento;


    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Estandar(Cliente cliente) {
        super(cliente);
        this.porcentajeDescuento = 0;
    }

    public Estandar(String nombre, String apellido, String pasaporte) {
        super(nombre, apellido, pasaporte);
        this.porcentajeDescuento = 0;
    }

    public Estandar(String nombre, String apellido, String pasaporte, String nombreDeUsuario, String contrasena) {
        super(nombre, apellido, pasaporte, nombreDeUsuario, contrasena);
        this.porcentajeDescuento = 0;
    }

    public Estandar(String nombre, String apellido, String pasaporte, LinkedList<Pasaje> pasajes) {
        super(nombre, apellido, pasaporte, pasajes);
        this.porcentajeDescuento = 0;
    }


    ////////////////////////////////////////////
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////



    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////
}
