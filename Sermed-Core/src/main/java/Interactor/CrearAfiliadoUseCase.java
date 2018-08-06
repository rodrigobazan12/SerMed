package Interactor;

import Excepciones.AfiliadoExisteException;
import Excepciones.TitularEnAfiliadoActivoException;
import Modelo.Afiliado;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class CrearAfiliadoUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public CrearAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public boolean crearAfiliado(Afiliado afiliadoNuevo) throws TitularEnAfiliadoActivoException, AfiliadoExisteException {
        if (!validarAfiliadoExiste(afiliadoNuevo)) {
            Afiliado afiliadoQueContieneAlTitularNuevo = buscarAfiliadoQueContienePersona(afiliadoNuevo.getTitular());
            if (afiliadoQueContieneAlTitularNuevo == null) return repositorioAfiliado.persist(afiliadoNuevo);
            if (afiliadoQueContieneAlTitularNuevo.getActivo()) throw new TitularEnAfiliadoActivoException();
            else {
                afiliadoNuevo.asignarTitular(afiliadoQueContieneAlTitularNuevo.devolverPersona(afiliadoNuevo.getTitular()));
                return repositorioAfiliado.persist(afiliadoNuevo);
            }
        }
        throw new AfiliadoExisteException();
    }

    private Afiliado buscarAfiliadoQueContienePersona(Persona titular) {
        final Afiliado afiliado = repositorioAfiliado.findAll().stream().filter(a -> a.contienePersona(titular)).findAny().orElse(null);
        return afiliado;
    }

    private boolean validarAfiliadoExiste(Afiliado afiliado) {
        return repositorioAfiliado.findUnicoByNumero(afiliado.getNumeroAfiliado()) != null;
    }

}
