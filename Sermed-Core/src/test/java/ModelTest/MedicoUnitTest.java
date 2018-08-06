package ModelTest;

import Modelo.Medico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicoUnitTest {

    @Test
    void mostrarMedico_MedicoCompleto_MuestraFormateado(){
        Medico elMedico=new Medico(1,"Vega", "Romina", 1044, "4813148");

        String mostrarMedico=elMedico.mostrarMedico();

        Assertions.assertEquals("Vega, Romina. Matricula 1044. Tel. 4813148",mostrarMedico);

    }

    @Test
    void modificarMedico_DatosModificados_AssertTrue() throws Exception{
        Medico medicoOriginal = new Medico(1,"Vega", "Romina", 1044, "4813148");
        Medico nuevosDatos = new Medico(6, "Vega", "Romina", 123, "123123");

        medicoOriginal.modificarDatos(nuevosDatos);

        Assertions.assertEquals(medicoOriginal.mostrarMedico(), nuevosDatos.mostrarMedico());
        Assertions.assertEquals(1, medicoOriginal.getIdMedico());
    }

    @Test
    void modificarMedico_DatosNulos_Excepcion(){
        Medico medicoOriginal = new Medico(1,"Vega", "Romina", 1044, "4813148");
        Medico nuevosDatos = new Medico(6, null, null, 123, "123123");

        Assertions.assertThrows(Exception.class,()->medicoOriginal.modificarDatos(nuevosDatos));
        Assertions.assertEquals(1, medicoOriginal.getIdMedico());
    }



}
