import java.io.Serializable;

public class UsoDeVagaFactory implements Serializable {

    public static UsoDeVaga criarUsoDeVaga(Vaga vaga) {
        return new UsoDeVaga(vaga);
    }
}
