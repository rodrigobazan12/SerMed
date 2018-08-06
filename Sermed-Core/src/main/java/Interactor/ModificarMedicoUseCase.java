package Interactor;

import Excepciones.MatriculasIgualesException;
import Excepciones.MedicoIncompletoException;
import Excepciones.UpdateMedicoException;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;

public class ModificarMedicoUseCase {


    private IMedicoRepositorio repositorioMedico;

    public ModificarMedicoUseCase(IMedicoRepositorio repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public Medico modificarMedico(Medico nuevosDatos) throws MatriculasIgualesException, MedicoIncompletoException, UpdateMedicoException {
        Medico elMedicoAModificar = repositorioMedico.findById(nuevosDatos.getIdMedico());
        if (elMedicoAModificar.getMatricula() == nuevosDatos.getMatricula() || repositorioMedico.findByMatricula(nuevosDatos.getMatricula()) == null) {
            elMedicoAModificar.modificarDatos(nuevosDatos);
            if (this.repositorioMedico.update(elMedicoAModificar))
                return elMedicoAModificar;
            throw new UpdateMedicoException();
        } else
            throw new MatriculasIgualesException();

    }


}
