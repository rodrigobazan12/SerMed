package Interactor;

import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class BuscarPersonaEntreAfiliadosUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public BuscarPersonaEntreAfiliadosUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public boolean existePersona(Persona laPersona) {
        return repositorioAfiliado.findAllActivos().stream().anyMatch(a -> a.contienePersona(laPersona));
    }
}
