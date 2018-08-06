package InteractorTest;

import Excepciones.*;
import Interactor.DesafiliarPersonaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IAfiliadoRepositorio;
import Repositorio.IPersonaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DesafiliarPersonaUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    @Mock
    IPersonaRepositorio repositorioPersona;

    @Test
    public void desafiliarPersona_personaAfiliada_devuelveTrue() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, PlanIncompletoException, AfiliadoSinPlanException, PersonaNoAfiliadaException {
        Persona persona = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null, factoryPlan());
        when(repositorioAfiliado.update(afiliado)).thenReturn(true);
        when(repositorioPersona.update(persona)).thenReturn(true);

        DesafiliarPersonaUseCase desafiliarPersonaUseCase = new DesafiliarPersonaUseCase(repositorioAfiliado, repositorioPersona);

        boolean resultado = desafiliarPersonaUseCase.desafiliarPersona(persona, afiliado);

        Assertions.assertEquals(true, resultado);
        Assertions.assertEquals(3, afiliado.getMiembros().size());
        Assertions.assertEquals(0, persona.getNroOrden());
        Assertions.assertEquals("", persona.getNumeroAfiliado());
    }

    @Test
    public void desafiliarPersona_PersonaNoAfiliada_PersonaNoAfiliadaException() throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, PersonaIncompletaException, DniConPuntosException, PlanIncompletoException, AfiliadoSinPlanException {
        Persona persona = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "1400005", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null, factoryPlan());

        DesafiliarPersonaUseCase desafiliarPersonaUseCase = new DesafiliarPersonaUseCase(repositorioAfiliado, repositorioPersona);

        Assertions.assertThrows(PersonaNoAfiliadaException.class, () -> desafiliarPersonaUseCase.desafiliarPersona(persona, afiliado));


    }


    private Persona factoryPersona() {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return null;
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Persona> factoryPersonaMiembros() {
        try {
            List<Persona> personas = new ArrayList<>();
            personas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            personas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            personas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            personas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            return personas;
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (NumeroAfiliadoIncorrectoException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return new ArrayList<>();
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

        return Plan.instancia(1, "Plan Basico", listaPrecios);
    }
}
