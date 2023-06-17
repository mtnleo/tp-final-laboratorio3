package Aerolinea;

public class ClienteExistenteException extends RuntimeException {
    public ClienteExistenteException() {
        super("El cliente ya existe.");
    }
}
