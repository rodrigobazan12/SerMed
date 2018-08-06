package InteractorTest;

import Excepciones.EnfermeroIncompletoException;
import Excepciones.MatriculasIgualesException;
import Excepciones.UpdateEnfermeroException;
import Interactor.ModificarEnfermeroUseCase;
import Mockito.MockitoExtension;
import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModificarEnfermeroUnitTest {

    @Mock
    IEnfermeroRepositorio repositorioEnfermero;

    @Test
    public void modificarEnfermero_DatosEnfermero_AssertTrue() throws MatriculasIgualesException, EnfermeroIncompletoException, UpdateEnfermeroException {

        ModificarEnfermeroUseCase modificarEnfermeroUseCase = new ModificarEnfermeroUseCase(repositorioEnfermero);

        when(repositorioEnfermero.findById(1)).thenReturn(new Enfermero(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioEnfermero.findByMatricula(123)).thenReturn(null);
        when(repositorioEnfermero.update(any(Enfermero.class))).thenReturn(true);

        Enfermero nuevosDatos = new Enfermero(1, "Vega", "Romina", 123, "123123");

        Enfermero enfermeroModificado = modificarEnfermeroUseCase.modificarEnfermero(nuevosDatos);

        Assertions.assertEquals(nuevosDatos.mostrarEnfermero(),enfermeroModificado.mostrarEnfermero());
        Assertions.assertEquals(1,enfermeroModificado.getIdEnfermero().intValue());
    }

    @Test
    public void modificarEnfermero__MatriculaExiste_NoActualiza() throws MatriculasIgualesException, EnfermeroIncompletoException, UpdateEnfermeroException {

        ModificarEnfermeroUseCase modificarEnfermeroUseCase = new ModificarEnfermeroUseCase(repositorioEnfermero);

        when(repositorioEnfermero.findById(1)).thenReturn(new Enfermero(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioEnfermero.findByMatricula(192256)).thenReturn(new Enfermero(3,"Ruitti", "Javier", 192256, "679414"));

        Enfermero romiNueva = new Enfermero(1,"Vega", "Romina", 192256, "4813148");

        Assertions.assertThrows(MatriculasIgualesException.class, () -> modificarEnfermeroUseCase.modificarEnfermero(romiNueva));

    }

}
