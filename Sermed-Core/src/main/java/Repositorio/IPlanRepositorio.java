package Repositorio;

import Modelo.Plan;

import java.util.Collection;

public interface IPlanRepositorio {
    Plan findUnicoByNombre(String nombre);
    Collection<Plan> findByNombre(String nombre);

    boolean persist(Plan plan);

    Collection<Plan> findAll();

    Plan findById(Integer idPlan);

    boolean update(Plan plan);
}
