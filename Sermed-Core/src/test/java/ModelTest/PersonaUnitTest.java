package ModelTest;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Mockito.MockitoExtension;
import Modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class PersonaUnitTest {

    @Test
    void instanciarPersona_AtributosObligatorios_PersonaInstanciada() throws PersonaIncompletaException, NumeroAfiliadoIncorrectoException, DniConPuntosException {
            Persona unaPersona=Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),"9 de julio 44",new TipoDocumento(1, "DNI"),
                    "30672405",new Sangre(1,"A","RH+"),null, null, "190000",null,0);
            Assertions.assertNotNull(unaPersona);
    }

    @Test
    void instanciaPersona_SinAtributosObligatorios_PersonaIncompletaException() {

        Assertions.assertThrows(PersonaIncompletaException.class, () -> Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                "30672405",null,null, null, "190000",null,0));
    }

    @Test
    void instanciaPersona_DniSinPuntos_InstanciaPersona() throws PersonaIncompletaException, NumeroAfiliadoIncorrectoException, DniConPuntosException {
        Persona unaPersona=Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),"9 de julio 44",new TipoDocumento(1, "DNI"),
                "30672405",new Sangre(1,"A","RH+"),null, null, "190000",null,0);
        Assertions.assertNotNull(unaPersona);
    }

    @Test
    void instanciaPersona_DniConPuntos_NoInstanciaPersona() throws PersonaIncompletaException, NumeroAfiliadoIncorrectoException, DniConPuntosException {
        Assertions.assertThrows(DniConPuntosException.class, ()->Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),"9 de julio 44",new TipoDocumento(1, "DNI"),
                "30.672.405",new Sangre(1,"A","RH+"),null, null, "190000",null,0));
    }
}
