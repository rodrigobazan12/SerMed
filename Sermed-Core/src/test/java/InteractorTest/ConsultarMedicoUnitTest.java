package InteractorTest;

import Excepciones.MedicoNoExisteException;
import Interactor.ConsultarMedicoUseCase;
import Mockito.MockitoExtension;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ConsultarMedicoUnitTest {

    @Mock
    IMedicoRepositorio repositorioMedico;

    @Spy
    List<Medico> list = crearMedicosArray();

    @Test
    public void consultarMedicos_ExistenDatos_ColeccionConDatos() {
        when(repositorioMedico.findAll()).thenReturn(list);

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);
        List<Medico> medicos = consultarMedicoUseCase.consultarMedicos();

        assertThat(medicos, not(IsEmptyCollection.empty()));
        assertEquals(medicos.size(), list.size());
        verify(repositorioMedico).findAll();

    }

    @Test
    public void consultarMedicos_NoExistenDatos_ColeccionVacia() {
        when(repositorioMedico.findAll()).thenReturn(new ArrayList<>());

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);
        List<Medico> medicos = consultarMedicoUseCase.consultarMedicos();

        assertThat(medicos, IsEmptyCollection.empty());
        verify(repositorioMedico).findAll();
    }

    @Test
    public void consultarMedicosPorApellido_CriterioCadenaVacia_DevolverTodos(){
        when(repositorioMedico.findByApellido("")).thenReturn(list);

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);

        List<Medico> medicos = consultarMedicoUseCase.consultarMedicosPorApellido("");

        assertEquals(list, medicos);
        assertThat(medicos, not(IsEmptyCollection.empty()));
        verify(repositorioMedico).findByApellido("");
    }

    @Test void consultarMedicosPorApellido_CriterioCadenaConDatos_DevolverAlgunos(){
        when(repositorioMedico.findByApellido("torr")).thenReturn(crearMedicoFiltroApellidoArray());

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);

        List<Medico> medicos = consultarMedicoUseCase.consultarMedicosPorApellido("torr");

        assertThat(medicos, hasSize(2));
        verify(repositorioMedico).findByApellido("torr");

    }
    @Test
    void consultarMedicoMatricula_MatriculaExiste_RetornaMedico() throws MedicoNoExisteException {
        when(repositorioMedico.findByMatricula(190252)).thenReturn(new Medico(2,"Torres", "German", 190252, "674678"));

        ConsultarMedicoUseCase consultarMedicoUseCase=new ConsultarMedicoUseCase(repositorioMedico);
        Medico medicoBuscado=consultarMedicoUseCase.consultarMedicoPorMatricula(190252);

        assertEquals(2,medicoBuscado.getIdMedico());
        verify(repositorioMedico).findByMatricula(190252);

    }

    @Test
    void consultarMedicoMatricula_MatriculaNoExiste_MedicoNoExisteException()
    {
        when(repositorioMedico.findByMatricula(190123)).thenReturn(null);
        ConsultarMedicoUseCase consultarMedicoUseCase=new ConsultarMedicoUseCase(repositorioMedico);
        Assertions.assertThrows(MedicoNoExisteException.class,()->consultarMedicoUseCase.consultarMedicoPorMatricula(190123));
    }


    private List<Medico> crearMedicosArray() {
        List<Medico> losMedicos=new ArrayList<Medico>();
        losMedicos.add(new Medico(1,"Vega", "Romina", 1044, "4813148"));
        losMedicos.add(new Medico(2,"Torres", "German", 190252, "674678"));
        losMedicos.add(new Medico(4,"Torrielli", "Edgar", 123465, "674678"));
        losMedicos.add(new Medico(3,"Ruitti", "Javier", 192256, "679414"));
        return losMedicos;
    }

    private List<Medico> crearMedicoFiltroApellidoArray(){
        List<Medico> losMedicos=new ArrayList<Medico>();
        losMedicos.add(new Medico(2,"Torres", "German", 190252, "674678"));
        losMedicos.add(new Medico(4,"Torrielli", "Edgar", 123465, "674678"));
        return losMedicos;
    }
}
