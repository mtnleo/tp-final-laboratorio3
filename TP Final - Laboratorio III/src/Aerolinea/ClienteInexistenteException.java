package Aerolinea;

public class ClienteInexistenteException extends RuntimeException {
    public ClienteInexistenteException() {
        super("El cliente buscado no existe.");
    }
}
