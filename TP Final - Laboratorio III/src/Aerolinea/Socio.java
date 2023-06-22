package Aerolinea;

public interface Socio {
    void mostrarBenificiosSocio();
    double calcularPrecioDescuento(double precioOriginal);

    int porcentajeDescuento = 0;
}