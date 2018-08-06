package InteractorTest;

import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Interactor.CrearPlanUseCase;
import Mockito.MockitoExtension;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.HashMap;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPlanUnitTest {

    @Mock
    IPlanRepositorio repositorioPlan;

    @Test
    public void crearPlan_PlanYaExiste_NoSeCreaPlan() throws PlanIncompletoException {
        Plan plan = Plan.instancia(1, "Plan Basico", factoryListaPrecios());
        when(repositorioPlan.findUnicoByNombre("Plan Basico")).thenReturn(Plan.instancia(1, "Plan Basico", factoryListaPrecios()));
        CrearPlanUseCase crearPlanUseCase = new CrearPlanUseCase(repositorioPlan);
        Assertions.assertThrows(PlanExisteException.class, ()->crearPlanUseCase.crearPlan(plan));

    }

    @Test
    public void crearPlan_PlanNoExiste_SeCreaPlan() throws PlanIncompletoException, PlanExisteException {
        Plan plan = Plan.instancia(1, "Plan Basico", factoryListaPrecios());
        when(repositorioPlan.findUnicoByNombre("Plan Basico")).thenReturn(null);
        when(repositorioPlan.persist(plan)).thenReturn(true);

        CrearPlanUseCase crearPlanUseCase = new CrearPlanUseCase(repositorioPlan);
        boolean resultado = crearPlanUseCase.crearPlan(plan);
        Assertions.assertTrue(resultado);

    }

    private HashMap<String, Double> factoryListaPrecios(){
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);

        return listaPrecios;
    }
}
