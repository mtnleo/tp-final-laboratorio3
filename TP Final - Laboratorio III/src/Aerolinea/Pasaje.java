package Aerolinea;
import java.util.UUID;

public class Pasaje {
    private String numero;
    private Vuelo vuelo;
    private Cliente cliente;
    private char grupo;

    public Pasaje() {
    }

    public Pasaje(Pasaje pasaje) {
        this.numero = pasaje.numero;
        this.vuelo = pasaje.vuelo;
        this.cliente = pasaje.cliente;
        this.grupo = pasaje.grupo;
    }

    public Pasaje(String numero, Vuelo vuelo, Cliente cliente, char grupo) {
        this.numero = UUID.randomUUID().toString().replaceAll("-", "");
        this.vuelo = vuelo;
        this.cliente = cliente;
        this.grupo = grupo;
    }

    @Override
    public String toString() {
        return "Pasaje{" +
                "numero='" + numero + '\'' +
                ", vuelo=" + vuelo +
                ", cliente=" + cliente +
                ", grupo=" + grupo +
                '}';
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }
}