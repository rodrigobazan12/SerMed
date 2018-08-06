package Repositorio;

import Modelo.Afiliado;

import java.util.Collection;

public interface IAfiliadoRepositorio {
    boolean persist(Afiliado afiliado);

    Afiliado findById(Integer idAfiliado);

    Collection<Afiliado> findAll();

    Collection<Afiliado> findByNumero(String numero);

    Afiliado findUnicoByNumero(String numero);

    boolean update(Afiliado afiliado);

    Collection<Afiliado> findAllActivos();

    Collection<Afiliado> findAllInactivos();
}
