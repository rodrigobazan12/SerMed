package Interactor;

import Modelo.Afiliado;
import ModeloReporte.FichaAfiliadoDTO;
import Repositorio.IAfiliadoRepositorio;

public class GenerarFichaAfiliadoUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public GenerarFichaAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public FichaAfiliadoDTO generarFichaAfiliadoParaReporte(String numeroAfiliado) {
        FichaAfiliadoDTO fichaGenerada=new FichaAfiliadoDTO();
        fichaGenerada.armarFicha(repositorioAfiliado.findUnicoByNumero(numeroAfiliado));
        return fichaGenerada;


    }


}
