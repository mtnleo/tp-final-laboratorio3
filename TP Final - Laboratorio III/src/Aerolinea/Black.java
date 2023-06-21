package Aerolinea;

import java.util.LinkedList;

public class Black extends Cliente implements Socio {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////
    private int porcentajeDescuento;

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
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////



    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////
}
