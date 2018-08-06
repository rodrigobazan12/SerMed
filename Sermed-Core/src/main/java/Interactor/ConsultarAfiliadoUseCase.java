package Interactor;

import Excepciones.AfiliadoNoExisteException;
import Modelo.Afiliado;
import Repositorio.IAfiliadoRepositorio;

import java.util.List;

public class ConsultarAfiliadoUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public ConsultarAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {

        this.repositorioAfiliado = repositorioAfiliado;
    }

    public List<Afiliado> consultarAfiliados() {
        return (List<Afiliado>) repositorioAfiliado.findAll();
    }

    public List<Afiliado> consultarAfiliadosPorNumero(String numero) {
        return (List<Afiliado>) repositorioAfiliado.findByNumero(numero);
    }

    public Afiliado consultarAfiliadoPorNumero(String numeroAfiliado) throws AfiliadoNoExisteException {
        Afiliado buscado = repositorioAfiliado.findUnicoByNumero(numeroAfiliado);
        if(buscado == null)
            throw new AfiliadoNoExisteException();
        else
            return buscado;
    }
}
