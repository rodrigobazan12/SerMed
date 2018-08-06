package ModeloReporte;

import Modelo.Afiliado;
import Modelo.Persona;
import Modelo.Plan;

import java.time.LocalDate;
import java.util.Collection;

public class FichaAfiliadoDTO {

    private LocalDate fechaAfiliacion;
    private String nroAfiliado;
    private Persona titular;
    private Collection<Persona> miembrosGrupoFamiliar;
    private Plan planAfiliado;

    public void armarFicha(Afiliado afiliado) {

        fechaAfiliacion=afiliado.getFechaAfiliacion();
        nroAfiliado=afiliado.getNumeroAfiliado();
        titular=afiliado.getTitular();
        miembrosGrupoFamiliar=afiliado.getMiembros();
        planAfiliado=afiliado.getPlan();

    }
}
