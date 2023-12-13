
/**
 * Interface que define o método de atualização de um observador.
 */
public interface Observer {
    /**
     * Método que atualiza o observador.
     * @param cliente Cliente que será atualizado.
     * @param novaArrecadacao Nova arrecadação do estacionamento.
     */
    void updateArrecadacao(Cliente cliente, double novaArrecadacao);
}