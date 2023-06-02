package Aerolinea;

public enum EstadoVuelo {
    EN_HORARIO("En horario"),
    DEMORADO("Demorado"),
    CANCELADO("Cancelado");

    private final String descripcion;

    private EstadoVuelo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}