package InteractorTest;

import Excepciones.*;
import Interactor.CambiarPlanAfiliadoUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IAfiliadoRepositorio;
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
public class ModificarPlanAfiliadoUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    @Test
    public void cambiarPlan_PlanDistinto_CambiaPlan() throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException, PlanesIgualesException, UpdateAfiliadoException {
        Plan planNuevo= Plan.instancia(2, "Plan Nuevo", listaPrecios());
        Afiliado afiliadoAModificar = Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null, factoryPlan());
        when(repositorioAfiliado.findUnicoByNumero(afiliadoAModificar.getNumeroAfiliado())).thenReturn(afiliadoAModificar);
        when(repositorioAfiliado.update(afiliadoAModificar)).thenReturn(true);
        CambiarPlanAfiliadoUseCase cambiarPlanUseCase = new CambiarPlanAfiliadoUseCase(repositorioAfiliado);
        Afiliado afiliadoModificado = cambiarPlanUseCase.modificarPlanAfiliado(afiliadoAModificar, planNuevo);
        Assertions.assertEquals(afiliadoModificado.mostrarPlanAfiliado(), planNuevo.mostrarPlan());
    }


    @Test
    public void cambiarPlan_PlanIgual_PlanesIgualesException() throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
        Plan planNuevo= Plan.instancia(2, "Plan Basico", listaPrecios());
        Afiliado afiliadoAModificar = Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null, factoryPlan());
        when(repositorioAfiliado.findUnicoByNumero(afiliadoAModificar.getNumeroAfiliado())).thenReturn(afiliadoAModificar);

        CambiarPlanAfiliadoUseCase cambiarPlanUseCase = new CambiarPlanAfiliadoUseCase(repositorioAfiliado);

        Assertions.assertThrows(PlanesIgualesException.class, () -> cambiarPlanUseCase.modificarPlanAfiliado(afiliadoAModificar, planNuevo));

    }

    private Persona factoryPersona() throws PersonaIncompletaException {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
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

    private HashMap<String, Double> listaPrecios() throws PlanIncompletoException {
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 480);
        listaPrecios.put("2", (double) 580);
        listaPrecios.put("3", (double) 650);
        listaPrecios.put("4", (double) 700);
        listaPrecios.put("5", (double) 850);
        listaPrecios.put("6", (double) 900);
        listaPrecios.put("7", (double) 1050);

        return listaPrecios;
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

}
