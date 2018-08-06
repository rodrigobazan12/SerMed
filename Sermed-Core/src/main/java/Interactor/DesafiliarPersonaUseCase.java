package Interactor;

import Excepciones.PersonaNoAfiliadaException;
import Modelo.Afiliado;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;
import Repositorio.IPersonaRepositorio;

public class DesafiliarPersonaUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;
    private IPersonaRepositorio repositorioPersona;

    public DesafiliarPersonaUseCase(IAfiliadoRepositorio repositorioAfiliado, IPersonaRepositorio repositorioPersona) {
        this.repositorioAfiliado = repositorioAfiliado;
        this.repositorioPersona = repositorioPersona;
    }

    public boolean desafiliarPersona(Persona persona, Afiliado afiliado) throws PersonaNoAfiliadaException {
        if(afiliado.quitarPersona(persona)){
            persona.setNroOrden(0);
            persona.setNumeroAfiliado("");
            return this.repositorioAfiliado.update(afiliado) && this.repositorioPersona.update(persona);

        }
        throw new PersonaNoAfiliadaException();
    }
}
