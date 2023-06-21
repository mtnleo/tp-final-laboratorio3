package Aerolinea;

import java.util.LinkedList;

public class Platinum extends Cliente implements Socio {
    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private int porcentajeDescuento;

    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Platinum(Cliente cliente) {
        super(cliente);
        this.porcentajeDescuento = 15;
    }

    public Platinum(String nombre, String apellido, String pasaporte) {
        super(nombre, apellido, pasaporte);
        this.porcentajeDescuento = 15;
    }

    public Platinum(String nombre, String apellido, String pasaporte, String nombreDeUsuario, String contrasena) {
        super(nombre, apellido, pasaporte, nombreDeUsuario, contrasena);
        this.porcentajeDescuento = 15;
    }

    public Platinum(String nombre, String apellido, String pasaporte, LinkedList<Pasaje> pasajes) {
        super(nombre, apellido, pasaporte, pasajes);
        this.porcentajeDescuento = 15;
    }

    ////////////////////////////////////////////
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////



    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////
}
