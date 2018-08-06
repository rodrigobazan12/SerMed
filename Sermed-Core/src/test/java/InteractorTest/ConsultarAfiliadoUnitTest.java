package InteractorTest;

import Excepciones.*;
import Interactor.ConsultarAfiliadoUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IAfiliadoRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarAfiliadoUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    @Spy
    List<Afiliado> listaAfiliados = crearAfiliadoArray();


    @Test
    public void consultarAfilidos_ExistenDatos_ColeccionConDatos() {
        when(repositorioAfiliado.findAll()).thenReturn(listaAfiliados);

        ConsultarAfiliadoUseCase consultarAfiliadoUseCase = new ConsultarAfiliadoUseCase(repositorioAfiliado);
        List<Afiliado> afiliados = consultarAfiliadoUseCase.consultarAfiliados();

        assertEquals(afiliados.size(), listaAfiliados.size());
        assertThat(afiliados, not(IsEmptyCollection.empty()));
        verify(repositorioAfiliado).findAll();

    }

    @Test
    public void consultarAfilidos_NoExistenDatos_ColeccionVacia() {
        when(repositorioAfiliado.findAll()).thenReturn(new ArrayList<>());

        ConsultarAfiliadoUseCase consultarAfiliadoUseCase = new ConsultarAfiliadoUseCase(repositorioAfiliado);
        List<Afiliado> afiliados = consultarAfiliadoUseCase.consultarAfiliados();

        assertThat(afiliados, IsEmptyCollection.empty());
        verify(repositorioAfiliado).findAll();

    }

    @Test
    public void consultarAfiliadosPorNumero_CriterioCadenaVacia_DevolverTodos() {
        when(repositorioAfiliado.findByNumero("")).thenReturn(listaAfiliados);

        ConsultarAfiliadoUseCase consultarAfiliadoUseCase = new ConsultarAfiliadoUseCase(repositorioAfiliado);
        List<Afiliado> afiliados = consultarAfiliadoUseCase.consultarAfiliadosPorNumero("");

        assertEquals(afiliados, listaAfiliados);
        assertThat(afiliados, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarAfiliadosPorNumero_CriterioCadenaConDatos_DevolverAlgunos() {
        when(repositorioAfiliado.findByNumero("01")).thenReturn(crearAfiliadosFiltroNumero());

        ConsultarAfiliadoUseCase consultarAfiliadoUseCase = new ConsultarAfiliadoUseCase(repositorioAfiliado);
        List<Afiliado> afiliados = consultarAfiliadoUseCase.consultarAfiliadosPorNumero("01");

        assertThat(afiliados, hasSize(2));
        assertThat(afiliados, not(IsEmptyCollection.empty()));
    }


    @Test
    public void consultarAfiliadoNumero_NumeroExiste_RetornaAfiliado() throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoNoExisteException, PlanIncompletoException, AfiliadoSinPlanException {
        when(repositorioAfiliado.findUnicoByNumero("000001")).thenReturn(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", factoryPersona().get(0), factoryPersona(), true, null, null, factoryPlan()));
        ConsultarAfiliadoUseCase consultarAfiliadoUseCase = new ConsultarAfiliadoUseCase(repositorioAfiliado);
        Afiliado afiliado = consultarAfiliadoUseCase.consultarAfiliadoPorNumero("000001");
        Assertions.assertEquals(1, afiliado.getIdAfiliado().intValue());

    }

    @Test
    public void consultarAfiliadoNumero_NumeroNoExiste_RetornaAfiliadoNoExisteException() {
        when(repositorioAfiliado.findUnicoByNumero("000001")).thenReturn(null);
        ConsultarAfiliadoUseCase consultarAfiliadoUseCase = new ConsultarAfiliadoUseCase(repositorioAfiliado);
        assertThrows(AfiliadoNoExisteException.class, () -> consultarAfiliadoUseCase.consultarAfiliadoPorNumero("000001"));
    }


    private List<Afiliado> crearAfiliadoArray() {
        List<Afiliado> afiliados = new ArrayList<>();
        try {
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", factoryPersona().get(0), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000003", factoryPersona().get(1), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000004", factoryPersona().get(2), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000005", factoryPersona().get(3), factoryPersona(), true, null, null, factoryPlan()));
            return afiliados;
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } finally {
            return afiliados;
        }

    }

    private List<Persona> factoryPersona() {
        List<Persona> personas = new ArrayList<>();
        try {
            personas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "000002", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "000003", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "000004", factoryAntecedenteMedico(), 0));
            return personas;
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return null;
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }

    private List<Afiliado> crearAfiliadosFiltroNumero() {
        List<Afiliado> afiliados = new ArrayList<>();
        try {
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", factoryPersona().get(0), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000100", factoryPersona().get(1), factoryPersona(), true, null, null, factoryPlan()));
            return afiliados;
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } finally {
            return afiliados;
        }
    }

    private Plan factoryPlan() throws PlanIncompletoException {
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);

        return Plan.instancia(1,"Plan Basico",listaPrecios);
    }

}
