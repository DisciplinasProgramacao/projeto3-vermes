/**
 * A enumeração `Servico` representa os diferentes serviços disponíveis em um estacionamento,
 * cada um associado a um valor e um tempo específicos.
 */
public enum Servico {

    /** Serviço de manobrista com valor e tempo associados. */
    MANOBRISTA(5.0, 0.0),

    /** Serviço de lavagem com valor e tempo associados. */
    LAVAGEM(20.0, 60.0),

    /** Serviço de polimento com valor e tempo associados. */
    POLIMENTO(45.0, 120.0);

    /** O valor do serviço. */
    private double valor;

    /** O tempo do serviço. */
    private double tempo;

    /**
     * Cria um novo objeto `Servico` com o valor e tempo fornecidos.
     *
     * @param valor O valor associado ao serviço.
     * @param tempo O tempo associado ao serviço.
     */
    Servico(double valor, double tempo) {
        this.valor = valor;
        this.tempo = tempo;
    }

    /**
     * Obtém o valor associado ao serviço.
     *
     * @return O valor do serviço.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Obtém o tempo associado ao serviço.
     *
     * @return O tempo do serviço.
     */
    public double getTempo() {
        return tempo;
    }
}
