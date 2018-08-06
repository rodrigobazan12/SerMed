package Modelo;

import Excepciones.EnfermeroIncompletoException;

public class Enfermero {
    private Integer idEnfermero;
    private String apellido;
    private String nombre;
    private Integer matricula;
    private String telefono;

    public Enfermero(Integer idEnfermero, String apellido, String nombre, Integer matricula, String telefono) {
        this.idEnfermero = idEnfermero;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }

    public Integer getIdEnfermero() {
        return idEnfermero;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String mostrarEnfermero() {
        return this.apellido + ", " + this.nombre + ". Matricula " + this.matricula + ". Tel. " + this.telefono;
    }

    public void modificarDatos(Enfermero nuevo) throws EnfermeroIncompletoException {
        if (nuevo.apellido == null || nuevo.nombre == null || nuevo.telefono == null) {
            throw new EnfermeroIncompletoException();
        }
        this.apellido = nuevo.apellido;
        this.nombre = nuevo.nombre;
        this.telefono = nuevo.telefono;
        this.matricula = nuevo.matricula;


    }
}
