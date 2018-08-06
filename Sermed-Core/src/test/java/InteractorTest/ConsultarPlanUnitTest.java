package InteractorTest;

import Excepciones.PlanIncompletoException;
import Interactor.ConsultarPlanUseCase;
import Mockito.MockitoExtension;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarPlanUnitTest {

    @Mock
    IPlanRepositorio repositorioPlan;

    @Spy
    List<Plan> listaPlanes = crearPlanesArray();

    @Test
    public void consultarPlan_ExistenDatos_ColeccionConDatos(){
        when(repositorioPlan.findAll()).thenReturn(listaPlanes);
        ConsultarPlanUseCase consultarPlanUseCase = new ConsultarPlanUseCase(repositorioPlan);
        List<Plan> planes = consultarPlanUseCase.consultarPlanes();
        assertEquals(planes.size(), 4);
        assertThat(planes, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarPlan_NoExistenDatos_ColeccionDeDatosVacia(){
        when(repositorioPlan.findAll()).thenReturn(new ArrayList<>());
        ConsultarPlanUseCase consultarPlanUseCase = new ConsultarPlanUseCase(repositorioPlan);
        List<Plan> planes = consultarPlanUseCase.consultarPlanes();
        assertThat(planes, IsEmptyCollection.empty());
    }

    @Test
    public void consultarPlanPorNombre_CriterioCadenaVacia_DevolverTodos(){
        when(repositorioPlan.findByNombre("")).thenReturn(listaPlanes);

        ConsultarPlanUseCase consultarPlanUseCase= new ConsultarPlanUseCase(repositorioPlan);
        List<Plan> planes=consultarPlanUseCase.consultarPlanesPorNombre("");

        assertEquals(4,planes.size());
    }

    @Test
    public void consultarPlanPorNombre_CriterioCadenaConDatos_DevolverAlgunos(){
        when(repositorioPlan.findByNombre("Basico")).thenReturn(listaPlanesFiltrado());

        ConsultarPlanUseCase consultarPlanUseCase= new ConsultarPlanUseCase(repositorioPlan);
        List<Plan> planes=consultarPlanUseCase.consultarPlanesPorNombre("Basico");

        assertEquals(2,planes.size());
    }

    private Collection<Plan> listaPlanesFiltrado() {
        //return listaPlanes.stream().filter(p->p.getNombre().contains("Basico")).collect(Collectors.toList());
        try {
            List<Plan> planes = new ArrayList<>();
            planes.add(Plan.instancia(1, "Plan Basico", factoryListaPrecios()));
            planes.add(Plan.instancia(2, "Plan Basico con tarjeta", factoryListaPrecios()));
            return planes;
        } catch (PlanIncompletoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    private List<Plan> crearPlanesArray(){
        try {
            List<Plan> planes = new ArrayList<>();
            planes.add(Plan.instancia(1, "Plan Basico", factoryListaPrecios()));
            planes.add(Plan.instancia(2, "Plan Basico con tarjeta", factoryListaPrecios()));
            planes.add(Plan.instancia(3, "Plan Especial", factoryListaPrecios()));
            planes.add(Plan.instancia(4, "Plan Especial con tarjeta", factoryListaPrecios()));
            return planes;
        } catch (PlanIncompletoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
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
