package Aerolinea;

public class ClienteInexistenteException extends RuntimeException {
    public ClienteInexistenteException(String message) {
        super(message);
    }

    public ClienteInexistenteException() {
        super("El cliente buscado no existe.");
    }
}
