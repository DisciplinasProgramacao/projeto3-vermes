import java.io.Serializable;

public class VeiculoFactory implements Serializable {

    public static Veiculo criarVeiculo(String placa) {
        return new Veiculo(placa);
    }
}
