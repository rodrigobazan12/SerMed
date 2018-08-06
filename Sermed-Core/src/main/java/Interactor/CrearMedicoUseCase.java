package Interactor;

import Excepciones.MedicoExisteException;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;

public class CrearMedicoUseCase {


    private IMedicoRepositorio repositorioMedico;

    public CrearMedicoUseCase(IMedicoRepositorio repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public boolean crearMedico(Medico medico) throws MedicoExisteException {
        if (!validarMedicoExiste(medico)) {
            return repositorioMedico.persist(medico);
        }
        throw new MedicoExisteException();
    }

    public boolean validarMedicoExiste(Medico medico) {
        return repositorioMedico.findByMatricula(medico.getMatricula()) != null;
    }
}
