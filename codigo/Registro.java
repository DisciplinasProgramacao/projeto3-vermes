import java.io.Serializable;
import java.util.Date;

/**
 * A classe `Registro` representa um registro de atividade relacionado a um veículo,
 * contendo informações como data, descrição e quilometragem.
 */
public class Registro implements Serializable {

    /** A data do registro. */
    private Date data;

    /** A descrição associada ao registro. */
    private String descricao;

    /** A quilometragem registrada. */
    private double quilometragem;

    /**
     * Cria um novo objeto `Registro` com os detalhes fornecidos.
     *
     * @param data A data do registro.
     * @param descricao A descrição associada ao registro.
     * @param quilometragem A quilometragem registrada.
     */
    public Registro(Date data, String descricao, double quilometragem) {
        this.data = data;
        this.descricao = descricao;
        this.quilometragem = quilometragem;
    }

    /**
     * Obtém a data do registro.
     *
     * @return A data do registro.
     */
    public Date getData() {
        return data;
    }

    /**
     * Obtém a descrição associada ao registro.
     *
     * @return A descrição do registro.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Obtém a quilometragem registrada no registro.
     *
     * @return A quilometragem registrada.
     */
    public double getQuilometragem() {
        return quilometragem;
    }

    /**
     * Obtém o mês do registro.
     *
     * @return O mês do registro.
     */
    public int getMes() {
        // A implementação deste método pode ser adicionada posteriormente, caso necessário.
        return 0;
    }
}
