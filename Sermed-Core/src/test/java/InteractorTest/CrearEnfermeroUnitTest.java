package InteractorTest;

import Excepciones.EnfermeroExisteException;
import Interactor.CrearEnfermeroUseCase;
import Mockito.MockitoExtension;

import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearEnfermeroUnitTest {

    @Mock
    IEnfermeroRepositorio repositorioEnfermero;

    @Test
    public void crearEnfermero_EnfermeroNoExiste_GuardarEnfermero() throws EnfermeroExisteException {
        Enfermero Enfermero = new Enfermero(1,"torres","geerman",12015,"as212321");
        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        when(repositorioEnfermero.findByMatricula(12015)).thenReturn(null);
        when(repositorioEnfermero.findById(1)).thenReturn(null);
        when(repositorioEnfermero.persist(any(Enfermero.class))).thenReturn(true);

        boolean resultado = crearEnfermeroUseCase.crearEnfermero(Enfermero);

        Assertions.assertEquals(true, resultado);
    }

    @Test
    public void crearEnfermero_EnfermeroExiste_EnfermeroExisteException() throws EnfermeroExisteException {
        when(repositorioEnfermero.findByMatricula(190202)).thenReturn(new Enfermero(1, "vega", "romina", 190202, "674678"));
        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        Enfermero Enfermero = new Enfermero(1, "vega", "romina", 190202, "674678");

        Assertions.assertThrows(EnfermeroExisteException.class, ()->crearEnfermeroUseCase.crearEnfermero(Enfermero));
    }







}




