package Modelo;

import Excepciones.MedicoIncompletoException;

public class Medico {
    private int idMedico;
    private String apellido;
    private String nombre;
    private int matricula;
    private String telefono;

    public Medico(int idMedico, String apellido, String nombre, int matricula, String telefono) {
        this.idMedico = idMedico;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public int getMatricula() {
        return matricula;
    }

    public String mostrarMedico() {
        return this.apellido+", "+this.nombre+". Matricula "+this.matricula+". Tel. "+this.telefono;
    }

    public void modificarDatos(Medico nuevo) throws MedicoIncompletoException {
        if(nuevo.apellido==null || nuevo.nombre==null || nuevo.telefono==null){
            throw new MedicoIncompletoException();
        }
            this.apellido = nuevo.apellido;
            this.nombre = nuevo.nombre;
            this.telefono = nuevo.telefono;
            this.matricula = nuevo.matricula;


    }
}
