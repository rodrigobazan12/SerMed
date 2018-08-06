package Repositorio;

import Modelo.Persona;
import Modelo.TipoDocumento;

public interface IPersonaRepositorio {
    boolean persist(Persona any);

    Persona findById(int idPersona);

    Persona findByDocumentoAndTipoDocumento(String documento, String tipoDocumento);

    boolean update(Persona personaDatosNuevos);
}
