package Interactor;

import Excepciones.EnfermeroExisteException;
import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;

public class CrearEnfermeroUseCase {


    private IEnfermeroRepositorio repositorioEnfermero;

    public CrearEnfermeroUseCase(IEnfermeroRepositorio repositorioEnfermero) {
        this.repositorioEnfermero = repositorioEnfermero;
    }

    public boolean crearEnfermero(Enfermero Enfermero) throws EnfermeroExisteException {
        if (!validarEnfermeroExiste(Enfermero)) {
            return repositorioEnfermero.persist(Enfermero);
        }
        throw new EnfermeroExisteException();
    }

    public boolean validarEnfermeroExiste(Enfermero Enfermero) {
        return repositorioEnfermero.findByMatricula(Enfermero.getMatricula()) != null;
    }
}
