package Interactor;

import Excepciones.PlanesIgualesException;
import Excepciones.UpdateAfiliadoException;
import Modelo.Afiliado;
import Modelo.Plan;
import Repositorio.IAfiliadoRepositorio;

public class CambiarPlanAfiliadoUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public CambiarPlanAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public Afiliado modificarPlanAfiliado(Afiliado afiliado, Plan planNuevo) throws PlanesIgualesException, UpdateAfiliadoException {
        Afiliado afiliadoAModificar = repositorioAfiliado.findUnicoByNumero(afiliado.getNumeroAfiliado());
        if (!afiliado.getPlan().mostrarPlan().equalsIgnoreCase(planNuevo.mostrarPlan())) {
            afiliadoAModificar.cambiarPlan(planNuevo);
            if (repositorioAfiliado.update(afiliadoAModificar)) return afiliadoAModificar;
            throw  new UpdateAfiliadoException();
        }
        throw new PlanesIgualesException();
    }
}
