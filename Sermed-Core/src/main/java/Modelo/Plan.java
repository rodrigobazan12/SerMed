package Modelo;

import Excepciones.PlanIncompletoException;

import java.util.HashMap;

public class Plan {


    private Integer idPlan;
    private String nombrePlan;
    private HashMap<String, Double> listaPrecios;

    private Plan(Integer idPlan, String nombrePlan, HashMap<String,Double> listaPrecios) {
        this.idPlan = idPlan;
        this.nombrePlan = nombrePlan;
        this.listaPrecios = listaPrecios;
    }

    public static Plan instancia(Integer idPlan, String nombrePlan, HashMap<String,Double> listaPrecios) throws PlanIncompletoException {
        if(nombrePlan == null || listaPrecios.isEmpty()){
            throw new PlanIncompletoException();
        }
        return new Plan(idPlan, nombrePlan, listaPrecios);
    }

    public String getNombre() {
        return this.nombrePlan;
    }

    public String mostrarPlan() {
        return this.nombrePlan;
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void modificarDatos(Plan nuevosDatos) throws PlanIncompletoException {
        if(nuevosDatos.nombrePlan == null || nuevosDatos.listaPrecios.isEmpty()){
            throw new PlanIncompletoException();
        }
        this.nombrePlan = nuevosDatos.nombrePlan;
        this.listaPrecios = nuevosDatos.listaPrecios;
    }
}
