package ModelTest;

import Excepciones.PlanIncompletoException;
import Modelo.Plan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PlanUnitTest {

    @Test
    void instanciarPlan_AtributosObligatorios_PlanInstanciado() throws PlanIncompletoException {
        Plan unPlan= Plan.instancia(1, "Plan Basico", factoryListaPrecios());
        Assertions.assertNotNull(unPlan);
    }

    @Test
    void instanciaPlan_SinAtributosObligatorios_PlanIncompletaException() {
        Assertions.assertThrows(PlanIncompletoException.class, () -> Plan.instancia(1, null, new HashMap<>()));
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
