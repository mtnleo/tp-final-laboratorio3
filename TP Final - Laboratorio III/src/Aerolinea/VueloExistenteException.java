package Aerolinea;

public class VueloExistenteException extends RuntimeException {
    public VueloExistenteException(String message) {
        super(message);
    }

    public VueloExistenteException() {
        super("El vuelo en cuestion ya existe");
    }

}
