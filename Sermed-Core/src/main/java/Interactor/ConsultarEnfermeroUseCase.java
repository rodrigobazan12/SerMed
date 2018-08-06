package Interactor;

import Excepciones.EnfermeroNoExisteException;
import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;

import java.util.ArrayList;
import java.util.List;

public class ConsultarEnfermeroUseCase {
    private IEnfermeroRepositorio repositorioEnfermero;

    public ConsultarEnfermeroUseCase(IEnfermeroRepositorio repositorioEnfermero) {
        this.repositorioEnfermero = repositorioEnfermero;
    }

    public List<Enfermero> consultarEnfermeros() {
        return this.repositorioEnfermero.findAll();
    }

    public List<Enfermero> consultarEnfermerosPorApellido(String apellido) {
        return this.repositorioEnfermero.findByApellido(apellido);
    }

    public Enfermero consultarEnfermeroPorMatricula(int matricula) throws EnfermeroNoExisteException {
        Enfermero buscado = this.repositorioEnfermero.findByMatricula(matricula);
        if(buscado != null)
            return buscado;
        throw new EnfermeroNoExisteException();
    }
}
