package Modelo;

public class AntecedenteMedico {

    private int idAntecedenteMedico;
    private Afeccion afeccion;
    private String observacion;

    public AntecedenteMedico(int idAntecedenteMedico, Afeccion afeccion, String observacion) {
        this.idAntecedenteMedico = idAntecedenteMedico;
        this.afeccion = afeccion;
        this.observacion = observacion;
    }
}
