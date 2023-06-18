package Aerolinea;

public class AvionInexistenteException extends RuntimeException {
    public AvionInexistenteException(String message) {
        super(message);
    }

    public AvionInexistenteException() {
        super("El avion buscado no existe.");
    }
}
