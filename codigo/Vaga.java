import java.io.Serializable;

/**
 * A classe `Vaga` representa uma vaga de estacionamento, identificada por uma combinação de fila e número.
 */
public class Vaga implements Serializable {

    /** Indica se a vaga está disponível ou ocupada. */
    private boolean disponivel;

    /** A identificação única da vaga, composta por fila e número. */
    private String id;

    /**
     * Cria uma nova vaga com base na fila e número especificados.
     *
     * @param fila O número da fila.
     * @param numero O número da vaga na fila.
     */
    public Vaga(int fila, int numero) {
        id = Integer.toString(fila) + " - " + Integer.toString(numero);
        disponivel = true;
    }

    /**
     * Estaciona um veículo na vaga, marcando-a como indisponível.
     *
     * @return true se o veículo foi estacionado com sucesso, false se a vaga já estiver ocupada.
     */
    public boolean estacionar() {
        if (disponivel) {
            disponivel = false;
            return true;
        }
        return false;
    }

    /**
     * Libera a vaga, marcando-a como disponível.
     *
     * @return true se a vaga foi liberada com sucesso, false se a vaga já estiver disponível.
     */
    public boolean sair() {
        if (!disponivel) {
            disponivel = true;
            return true;
        }
        return false;
    }

    /**
     * Verifica se a vaga está disponível.
     *
     * @return true se a vaga estiver disponível, false se estiver ocupada.
     */
    public boolean disponivel() {
        return disponivel;
    }

    /**
     * Retorna uma representação em formato de string da vaga, indicando sua identificação única.
     *
     * @return A identificação única da vaga.
     */
    @Override
    public String toString() {
        return id;
    }
}
