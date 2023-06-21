package Aerolinea;

import java.util.LinkedList;

public class Gold extends Cliente implements Socio {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private int pocentajeDescuento;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Gold(Cliente cliente) {
        super(cliente);
        this.pocentajeDescuento = 5;
    }

    public Gold(String nombre, String apellido, String pasaporte) {
        super(nombre, apellido, pasaporte);
        this.pocentajeDescuento = 5;
    }

    public Gold(String nombre, String apellido, String pasaporte, String nombreDeUsuario, String contrasena) {
        super(nombre, apellido, pasaporte, nombreDeUsuario, contrasena);
        this.pocentajeDescuento = 5;
    }

    public Gold(String nombre, String apellido, String pasaporte, LinkedList<Pasaje> pasajes) {
        super(nombre, apellido, pasaporte, pasajes);
        this.pocentajeDescuento = 5;
    }

    ////////////////////////////////////////////
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////



    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////
}
