package Aerolinea;

public class Aeropuerto {
    private String codigo;
    private String ciudad;
    private String pais;

    public Aeropuerto() {
    }

    public Aeropuerto(Aeropuerto aeropuerto) {

        this.codigo = aeropuerto.codigo;
        this.ciudad = aeropuerto.ciudad;
        this.pais = aeropuerto.pais;
    }

    public Aeropuerto(String codigo, String ciudad, String pais) {

        this.codigo = codigo;
        this.ciudad = ciudad;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Aeropuerto{" +
                "codigo='" + codigo + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
