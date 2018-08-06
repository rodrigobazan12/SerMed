package Interactor;

import Excepciones.EnfermeroIncompletoException;
import Excepciones.MatriculasIgualesException;
import Excepciones.UpdateEnfermeroException;
import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;

public class ModificarEnfermeroUseCase {


    private IEnfermeroRepositorio repositorioEnfermero;

    public ModificarEnfermeroUseCase(IEnfermeroRepositorio repositorioEnfermero) {
        this.repositorioEnfermero = repositorioEnfermero;
    }

    public Enfermero modificarEnfermero(Enfermero nuevosDatos) throws MatriculasIgualesException, EnfermeroIncompletoException, UpdateEnfermeroException {
        Enfermero elEnfermeroAmodificar = repositorioEnfermero.findById(nuevosDatos.getIdEnfermero());
        if (elEnfermeroAmodificar.getMatricula() == nuevosDatos.getMatricula() || repositorioEnfermero.findByMatricula(nuevosDatos.getMatricula()) == null) {
            elEnfermeroAmodificar.modificarDatos(nuevosDatos);
            if (repositorioEnfermero.update(elEnfermeroAmodificar))
                return elEnfermeroAmodificar;
            throw new UpdateEnfermeroException();
        } else
            throw new MatriculasIgualesException();
    }

}
