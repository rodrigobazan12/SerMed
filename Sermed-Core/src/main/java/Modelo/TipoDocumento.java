package Modelo;

public class TipoDocumento {

    private int idTipoDocumento;
    private String nombre;

    public TipoDocumento(int idTipoDocumento, String nombre) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
