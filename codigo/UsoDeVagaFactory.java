import java.io.Serializable;

public class UsoDeVagaFactory implements Serializable {

    public static UsoDeVaga criarUsoDeVaga(Vaga vaga) throws VagaIndisoponivelException {
        return new UsoDeVaga(vaga);
    }
}
