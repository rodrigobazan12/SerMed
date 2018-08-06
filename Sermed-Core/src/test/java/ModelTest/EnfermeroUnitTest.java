package ModelTest;

import Modelo.Enfermero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnfermeroUnitTest {

    @Test
    void mostrarEnfermero_EnfermeroCompleto_MuestraFormateado(){
        Enfermero elEnfermero=new Enfermero(1,"Vega", "Romina", 1044, "4813148");

        String mostrarEnfermero=elEnfermero.mostrarEnfermero();

        Assertions.assertEquals("Vega, Romina. Matricula 1044. Tel. 4813148",mostrarEnfermero);

    }

    @Test
    void modificarEnfermero_DatosModificados_AssertTrue() throws Exception{
        Enfermero EnfermeroOriginal = new Enfermero(1,"Vega", "Romina", 1044, "4813148");
        Enfermero nuevosDatos = new Enfermero(6, "Vega", "Romina", 123, "123123");

        EnfermeroOriginal.modificarDatos(nuevosDatos);

        Assertions.assertEquals(EnfermeroOriginal.mostrarEnfermero(), nuevosDatos.mostrarEnfermero());
        Assertions.assertEquals(1, EnfermeroOriginal.getIdEnfermero().intValue());
    }

    @Test
    void modificarEnfermero_DatosNulos_Excepcion(){
        Enfermero EnfermeroOriginal = new Enfermero(1,"Vega", "Romina", 1044, "4813148");
        Enfermero nuevosDatos = new Enfermero(6, null, null, 123, "123123");

        Assertions.assertThrows(Exception.class,()->EnfermeroOriginal.modificarDatos(nuevosDatos));
        Assertions.assertEquals(1, EnfermeroOriginal.getIdEnfermero().intValue());
    }



}
