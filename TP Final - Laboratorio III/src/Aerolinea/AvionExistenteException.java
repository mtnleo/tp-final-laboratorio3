package Aerolinea;

public class AvionExistenteException extends RuntimeException {
    public AvionExistenteException() {
        super("El avion ya existe.");
    }
}
