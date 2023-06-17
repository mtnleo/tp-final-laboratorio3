package Aerolinea;

public class AvionInexistenteException extends RuntimeException {
    public AvionInexistenteException() {
        super("El avion buscado no existe.");
    }
}
