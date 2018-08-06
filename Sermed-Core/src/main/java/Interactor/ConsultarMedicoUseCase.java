package Interactor;

import Excepciones.MedicoNoExisteException;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import java.util.List;

public class ConsultarMedicoUseCase {
    private IMedicoRepositorio repositorioMedico;

    public ConsultarMedicoUseCase(IMedicoRepositorio repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public List<Medico> consultarMedicos() {
        return this.repositorioMedico.findAll();
    }

    public List<Medico> consultarMedicosPorApellido(String apellido) {
        return this.repositorioMedico.findByApellido(apellido);
    }

    public Medico consultarMedicoPorMatricula(int matricula) throws MedicoNoExisteException {
        Medico buscado = this.repositorioMedico.findByMatricula(matricula);
        if(buscado != null)
            return buscado;
        throw new MedicoNoExisteException();
    }
}
