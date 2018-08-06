package InteractorTest;

import Excepciones.MatriculasIgualesException;
import Excepciones.MedicoIncompletoException;
import Excepciones.UpdateMedicoException;
import Interactor.ModificarMedicoUseCase;
import Mockito.MockitoExtension;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModificarMedicoUnitTest {

    @Mock
    IMedicoRepositorio repositorioMedico;

    @Test
    public void modificarMedico_DatosMedico_AssertTrue() throws UpdateMedicoException, MatriculasIgualesException, MedicoIncompletoException {

        ModificarMedicoUseCase modificarMedicoUseCase = new ModificarMedicoUseCase(repositorioMedico);

        when(repositorioMedico.findById(1)).thenReturn(new Medico(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioMedico.findByMatricula(123)).thenReturn(null);
        Medico medicoAModificar = repositorioMedico.findById(1);
        Medico nuevosDatos = new Medico(1, "Vega", "Romina", 123, "123123");
        when(repositorioMedico.update(medicoAModificar)).thenReturn(true);

        Medico medicoModificado = modificarMedicoUseCase.modificarMedico(nuevosDatos);

        Assertions.assertEquals(nuevosDatos.mostrarMedico(),medicoModificado.mostrarMedico());
        Assertions.assertEquals(1,medicoModificado.getIdMedico());

    }

    @Test
    void modificarMedico__MatriculaExiste_NoActualiza(){

        ModificarMedicoUseCase modificarMedicoUseCase = new ModificarMedicoUseCase(repositorioMedico);

        when(repositorioMedico.findById(1)).thenReturn(new Medico(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioMedico.findByMatricula(192256)).thenReturn(new Medico(3,"Ruitti", "Javier", 192256, "679414"));

        Medico romiNueva = new Medico(1,"Vega", "Romina", 192256, "4813148");

        Assertions.assertThrows(MatriculasIgualesException.class, () -> modificarMedicoUseCase.modificarMedico(romiNueva));


    }

}
