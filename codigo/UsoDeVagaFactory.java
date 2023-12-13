import java.io.Serializable;
/**
 * Interface que representa a fábrica de uso de vaga.
 * 
 */
public interface UsoDeVagaFactory {
    /**
     * Método que cria um uso de vaga.
     * @param vaga Vaga que será ocupada pelo objeto criado.
     * @return Objeto do tipo UsoDeVaga.
     * @throws VagaIndisoponivelException Exceção lançada quando a vaga passada como parâmetro já está ocupada.
     */
    UsoDeVaga criar(Vaga vaga) throws VagaIndisoponivelException;
}
