package Repositorio;

import Modelo.Medico;

import java.util.List;

public interface IMedicoRepositorio {

    boolean persist(Medico unMedico);

    Medico findById(Integer id);

    Medico findByMatricula(Integer matricula);

    List<Medico> findAll();

    List<Medico> findByApellido(String apellido);

    boolean update(Medico medico);
}
