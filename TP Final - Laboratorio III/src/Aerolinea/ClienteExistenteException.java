package Aerolinea;

public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException(String message) {
        super(message);
    }

    public ClienteExistenteException() {
        super("El cliente ya existe.");
    }
}
