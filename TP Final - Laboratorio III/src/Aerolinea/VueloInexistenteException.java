package Aerolinea;

public class VueloInexistenteException extends RuntimeException{
    public VueloInexistenteException() {
        super("El vuelo en cuestion no existe.");
    }

    public VueloInexistenteException(String message) {
        super(message);
    }
}
