package InteractorTest;

import Excepciones.EnfermeroNoExisteException;
import Interactor.ConsultarEnfermeroUseCase;
import Mockito.MockitoExtension;
import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
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
public class ConsultarEnfermeroUnitTest {

    @Mock
    IEnfermeroRepositorio repositorioEnfermero;

    @Spy
    List<Enfermero> list = crearEnfermerosArray();

    @Test
    public void consultarEnfermeros_ExistenDatos_ColeccionConDatos() {
        when(repositorioEnfermero.findAll()).thenReturn(list);

        ConsultarEnfermeroUseCase consultarEnfermeroUseCase = new ConsultarEnfermeroUseCase(repositorioEnfermero);
        List<Enfermero> Enfermeros = consultarEnfermeroUseCase.consultarEnfermeros();

        assertThat(Enfermeros, not(IsEmptyCollection.empty()));
        Assertions.assertEquals(Enfermeros.size(), list.size());
        verify(repositorioEnfermero).findAll();

    }

    @Test
    public void consultarEnfermeros_NoExistenDatos_ColeccionVacia() {
        when(repositorioEnfermero.findAll()).thenReturn(new ArrayList<>());

        ConsultarEnfermeroUseCase consultarEnfermeroUseCase = new ConsultarEnfermeroUseCase(repositorioEnfermero);
        List<Enfermero> Enfermeros = consultarEnfermeroUseCase.consultarEnfermeros();

        assertThat(Enfermeros, IsEmptyCollection.empty());
        verify(repositorioEnfermero).findAll();
    }

    @Test
    public void consultarEnfermerosPorApellido_CriterioCadenaVacia_DevolverTodos(){
        when(repositorioEnfermero.findByApellido("")).thenReturn(list);

        ConsultarEnfermeroUseCase consultarEnfermeroUseCase = new ConsultarEnfermeroUseCase(repositorioEnfermero);

        List<Enfermero> Enfermeros = consultarEnfermeroUseCase.consultarEnfermerosPorApellido("");

        Assertions.assertEquals(list, Enfermeros);
        assertThat(Enfermeros, not(IsEmptyCollection.empty()));
        verify(repositorioEnfermero).findByApellido("");
    }

    @Test void consultarEnfermerosPorApellido_CriterioCadenaConDatos_DevolverAlgunos(){
        when(repositorioEnfermero.findByApellido("torr")).thenReturn(crearEnfermeroFiltroApellidoArray());

        ConsultarEnfermeroUseCase consultarEnfermeroUseCase = new ConsultarEnfermeroUseCase(repositorioEnfermero);

        List<Enfermero> Enfermeros = consultarEnfermeroUseCase.consultarEnfermerosPorApellido("torr");

        assertThat(Enfermeros, hasSize(2));
        verify(repositorioEnfermero).findByApellido("torr");

    }
    @Test
    void consultarEnfermeroMatricula_MatriculaExiste_RetornaEnfermero() throws EnfermeroNoExisteException {
        when(repositorioEnfermero.findByMatricula(190252)).thenReturn(new Enfermero(2,"Torres", "German", 190252, "674678"));

        ConsultarEnfermeroUseCase consultarEnfermeroUseCase=new ConsultarEnfermeroUseCase(repositorioEnfermero);
        Enfermero EnfermeroBuscado=consultarEnfermeroUseCase.consultarEnfermeroPorMatricula(190252);

        Assertions.assertEquals(2,EnfermeroBuscado.getIdEnfermero().intValue());
        verify(repositorioEnfermero).findByMatricula(190252);

    }

    @Test
    void consultarEnfermeroMatricula_MatriculaNoExiste_EnfermeroNoExisteException()
    {
        when(repositorioEnfermero.findByMatricula(190123)).thenReturn(null);

        ConsultarEnfermeroUseCase consultarEnfermeroUseCase=new ConsultarEnfermeroUseCase(repositorioEnfermero);


        Assertions.assertThrows(EnfermeroNoExisteException.class, () -> consultarEnfermeroUseCase.consultarEnfermeroPorMatricula(190123));

    }


    private List<Enfermero> crearEnfermerosArray() {
        List<Enfermero> losEnfermeros=new ArrayList<Enfermero>();
        losEnfermeros.add(new Enfermero(1,"Vega", "Romina", 1044, "4813148"));
        losEnfermeros.add(new Enfermero(2,"Torres", "German", 190252, "674678"));
        losEnfermeros.add(new Enfermero(4,"Torrielli", "Edgar", 123465, "674678"));
        losEnfermeros.add(new Enfermero(3,"Ruitti", "Javier", 192256, "679414"));
        return losEnfermeros;
    }

    private List<Enfermero> crearEnfermeroFiltroApellidoArray(){
        List<Enfermero> losEnfermeros=new ArrayList<Enfermero>();
        losEnfermeros.add(new Enfermero(2,"Torres", "German", 190252, "674678"));
        losEnfermeros.add(new Enfermero(4,"Torrielli", "Edgar", 123465, "674678"));
        return losEnfermeros;
    }
}
