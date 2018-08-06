package InteractorTest;

import Excepciones.*;
import Interactor.BuscarPersonaEntreAfiliadosUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IAfiliadoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarPersonaEntreAfiliadosUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    @Spy
    List<Afiliado> afiliadosList = crearAfiliadoArray();

    @Test
    public void buscarPersonaEntreAfiliados_PersonaNoSeEncuentra_DevuelveFalso() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        when(repositorioAfiliado.findAll()).thenReturn(afiliadosList);
        Persona laPersona = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "99999999", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001-00", null, 0);

        BuscarPersonaEntreAfiliadosUseCase buscarPersona = new BuscarPersonaEntreAfiliadosUseCase(repositorioAfiliado);

        boolean resultado = buscarPersona.existePersona(laPersona);
        Assertions.assertFalse(resultado);


    }

    @Test
    public void buscarPersonaEntreAfiliados_PersonaSeEncuentra_DevuelveTrue() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        when(repositorioAfiliado.findAllActivos()).thenReturn(afiliadosList);
        Persona laPersona = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001-00", null, 0);

        BuscarPersonaEntreAfiliadosUseCase buscarPersona = new BuscarPersonaEntreAfiliadosUseCase(repositorioAfiliado);

        boolean resultado = buscarPersona.existePersona(laPersona);

        Assertions.assertTrue(resultado);

    }


    private List<Afiliado> crearAfiliadoArray() {
        List<Afiliado> afiliados = new ArrayList<>();
        try {

            Persona titular1 = Persona.instancia(1, "Perez", "Juan", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "10101010", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001-00", null, 0);
            List<Persona> personasAfiliado1 = new ArrayList<>();
            personasAfiliado1.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001-01", null, 0));
            personasAfiliado1.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "000001-02", null, 0));


            Persona titular2 = Persona.instancia(1, "Paez", "Martin", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "20202020", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000002-00", null, 0);
            List<Persona> personasAfiliado2 = new ArrayList<>();
            personasAfiliado2.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "000002-01", null, 0));
            personasAfiliado2.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "000002-02", null, 0));


            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", titular1, personasAfiliado1, true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000003", titular2, personasAfiliado2, true, null, null, factoryPlan()));
            return afiliados;

        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } catch (PersonaIncompletaException e) {
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
