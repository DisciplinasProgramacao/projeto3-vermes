import java.io.Serializable;

public interface UsoDeVagaFactory {
    UsoDeVaga criar(Vaga vaga) throws VagaIndisoponivelException;
}
