package Interactor;

import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class BuscarPersonaEntreAfiliadosDeBajaUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public BuscarPersonaEntreAfiliadosDeBajaUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public boolean existePersonaPorDNI(Persona laPersona) {
        return repositorioAfiliado.findAllInactivos().stream().anyMatch(a -> a.contienePersona(laPersona));
    }
}
