package InteractorTest;

import Excepciones.MedicoExisteException;
import Interactor.CrearMedicoUseCase;
import Mockito.MockitoExtension;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearMedicoUnitTest {

    @Mock
    IMedicoRepositorio repositorioMedico;

    @Test
    public void crearMedico_MedicoNoExiste_GuardarMedico() throws MedicoExisteException {
        Medico medico = new Medico(1,"torres","geerman",12015,"as212321");
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        when(repositorioMedico.findByMatricula(12015)).thenReturn(null);
        when(repositorioMedico.findById(1)).thenReturn(null);
        when(repositorioMedico.persist(any(Medico.class))).thenReturn(true);

        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        Assertions.assertEquals(true, resultado);
    }

    @Test
    public void crearMedico_MedicoSiExiste_NoGuardaMedico() {
        when(repositorioMedico.findByMatricula(190202)).thenReturn(new Medico(1,"torres","geerman",12015,"as212321"));
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = new Medico(1, "vega", "romina", 190202, "674678");

        Assertions.assertThrows(MedicoExisteException.class, () -> crearMedicoUseCase.crearMedico(medico));

    }

}




