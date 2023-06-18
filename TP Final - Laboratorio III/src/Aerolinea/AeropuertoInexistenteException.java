package Aerolinea;

public class AeropuertoInexistenteException extends RuntimeException{
    public AeropuertoInexistenteException() {
        super("El aeropuerto en cuestion no existe");
    }

    public AeropuertoInexistenteException(String message) {
        super(message);
    }
}
