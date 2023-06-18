package Aerolinea;

public class AvionExistenteException extends RuntimeException {
    public AvionExistenteException(String message) {
        super(message);
    }
    public AvionExistenteException() {
        super("El avion ya existe.");
    }
}
