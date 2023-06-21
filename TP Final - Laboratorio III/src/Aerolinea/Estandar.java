package Aerolinea;

import java.util.LinkedList;

public class Estandar extends Cliente {

    ////////////////////////////////////////////
    // ATRIBUTOS ----------------------------
    ////////////////////////////////////////////


    ////////////////////////////////////////////
    // CONSTRUCTORES ----------------------------
    ////////////////////////////////////////////

    public Estandar(Cliente cliente) {
        super(cliente);
    }

    public Estandar(String nombre, String apellido, String pasaporte) {
        super(nombre, apellido, pasaporte);
    }

    public Estandar(String nombre, String apellido, String pasaporte, String nombreDeUsuario, String contrasena) {
        super(nombre, apellido, pasaporte, nombreDeUsuario, contrasena);
    }

    public Estandar(String nombre, String apellido, String pasaporte, LinkedList<Pasaje> pasajes) {
        super(nombre, apellido, pasaporte, pasajes);
    }


    ////////////////////////////////////////////
    // GETTERS AND SETTERS ---------------------
    ////////////////////////////////////////////



    ////////////////////////////////////////////
    // METODOS ----------------------------
    ////////////////////////////////////////////
}
