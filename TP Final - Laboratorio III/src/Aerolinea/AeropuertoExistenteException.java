package Aerolinea;

public class AeropuertoExistenteException extends RuntimeException {
    public AeropuertoExistenteException(String message) {
        super(message);
    }
    public AeropuertoExistenteException() {
        super("El aeropuerto ya existe.");
    }
}
