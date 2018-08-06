package Repositorio;


import Modelo.Enfermero;

import java.util.List;

public interface IEnfermeroRepositorio {
    boolean persist(Enfermero unEnfermero);

    Enfermero findById(Integer id);

    Enfermero findByMatricula(Integer matricula);

    List<Enfermero> findAll();

    List<Enfermero> findByApellido(String apellido);

    boolean update(Enfermero Enfermero);
}
