package Interactor;

import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import Excepciones.UpdatePersonaException;
import Modelo.Persona;
import Repositorio.IPersonaRepositorio;

public class ModificarPersonaUseCase {
    private IPersonaRepositorio repositorioPersona;

    public ModificarPersonaUseCase(IPersonaRepositorio repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    public Persona modificarPersona(Persona personaDatosNuevos) throws PersonaIncompletaException, PersonaExisteException, UpdatePersonaException {
            Persona personaAModificar = repositorioPersona.findById(personaDatosNuevos.getIdPersona());
            if (personaAModificar.obtenerDocumentoCompleto().equals(personaDatosNuevos.obtenerDocumentoCompleto()) || repositorioPersona.findByDocumentoAndTipoDocumento(personaDatosNuevos.getDocumento(), personaDatosNuevos.getTipoDocumento().getNombre()) == null) {
                personaAModificar.modificarDatos(personaDatosNuevos);
                if(repositorioPersona.update(personaAModificar))
                    return personaAModificar;
                throw new UpdatePersonaException();
            }
            throw new PersonaExisteException();

    }
}
