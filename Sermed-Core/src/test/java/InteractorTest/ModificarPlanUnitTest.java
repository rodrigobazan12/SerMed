package InteractorTest;

import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Excepciones.UpdatePlanException;
import Interactor.ModificarPlanUseCase;
import Mockito.MockitoExtension;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModificarPlanUnitTest {

    @Mock
    IPlanRepositorio repositorioPlan;

    @Test
    public void modificarPlan_DatosPlan_DevuelveTrue() throws PlanIncompletoException, PlanExisteException, UpdatePlanException {
        ModificarPlanUseCase modificarPlanUseCase = new ModificarPlanUseCase(repositorioPlan);

        when(repositorioPlan.findById(1)).thenReturn(Plan.instancia(1, "Plan Basico",factoryListaPreciosOriginal()));
        Plan planAModificar = repositorioPlan.findById(1);
        when(repositorioPlan.findUnicoByNombre("Plan Nuevo Basico")).thenReturn(null);
        when(repositorioPlan.update(planAModificar)).thenReturn(true);


        Plan nuevosDatos = Plan.instancia(1, "Plan Nuevo Basico",factoryListaPreciosModificada());
        Plan planModificado = modificarPlanUseCase.modificarPlan(nuevosDatos);

        Assertions.assertEquals(nuevosDatos.mostrarPlan(), planModificado.mostrarPlan());
        Assertions.assertEquals(1, planModificado.getIdPlan().intValue());
    }

    @Test
    public void modificarPlan_PlanExiste_NoActualiza() throws PlanIncompletoException {
        ModificarPlanUseCase modificarPlanUseCase = new ModificarPlanUseCase(repositorioPlan);
        when(repositorioPlan.findById(1)).thenReturn(Plan.instancia(1, "Plan Basico",factoryListaPreciosOriginal()));
        when(repositorioPlan.findUnicoByNombre("Plan Especial")).thenReturn(Plan.instancia(2, "Plan Especial", factoryListaPreciosOriginal()));

        Plan planNuevo = Plan.instancia(1, "Plan Especial", factoryListaPreciosModificada());
        Assertions.assertThrows(PlanExisteException.class, () -> modificarPlanUseCase.modificarPlan(planNuevo));
    }


    @Test
    public void modificarPlan_NoModificaNombre_ActualizaPlan() throws PlanIncompletoException, PlanExisteException, UpdatePlanException {
        ModificarPlanUseCase modificarPlanUseCase = new ModificarPlanUseCase(repositorioPlan);
        when(repositorioPlan.findById(1)).thenReturn(Plan.instancia(1, "Plan Basico",factoryListaPreciosOriginal()));
        when(repositorioPlan.findUnicoByNombre("Plan Basico")).thenReturn(Plan.instancia(1, "Plan Basico", factoryListaPreciosOriginal()));
        when(repositorioPlan.update(any(Plan.class))).thenReturn(true);
        Plan planNuevo = Plan.instancia(1, "Plan Nuevo", factoryListaPreciosModificada());
        Plan planModificado = modificarPlanUseCase.modificarPlan(planNuevo);

        Assertions.assertEquals(planNuevo.mostrarPlan(), planModificado.mostrarPlan());

    }


    private HashMap<String, Double> factoryListaPreciosOriginal(){
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

    private HashMap<String, Double> factoryListaPreciosModificada(){
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 400);
        listaPrecios.put("2", (double) 500);
        listaPrecios.put("3", (double) 600);
        listaPrecios.put("4", (double) 700);
        listaPrecios.put("5", (double) 800);
        listaPrecios.put("6", (double) 900);
        listaPrecios.put("7", (double) 1000);

        return listaPrecios;
    }
}
