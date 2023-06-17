package Aerolinea;

public class AeropuertoExistenteException extends RuntimeException {
    public AeropuertoExistenteException() {
        super("El aeropuerto ya existe.");
    }
}
