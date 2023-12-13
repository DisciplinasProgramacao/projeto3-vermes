import java.io.Serializable;
import java.util.*;
/**
 * Classe que representa o relatório do estacionamento.
 * Implementa a interface Observer para ser notificado quando a arrecadação do estacionamento é atualizada.
 * Implementa a interface Serializable para que possa ser salva em arquivo.
 * @see Observer 
 * @see Serializable
 */
public class Relatorio implements Observer, Serializable{
    private Map<Cliente, Double> top5Clientes;
    private double arrecadacaoMensal;

    public Relatorio() {
        this.top5Clientes = new LinkedHashMap<>(); // LinkedHashMap para manter a ordem de inserção
        this.arrecadacaoMensal = 0.0;
    }

     
    @Override
     /**
      * Método que atualiza o observador.
      * @param cliente Cliente que será atualizado.
      * @param novaArrecadacao Nova arrecadação do estacionamento.
      */
    public void updateArrecadacao(Cliente cliente, double novaArrecadacao) {
        top5Clientes.put(cliente, novaArrecadacao);

        // Ordena o Top5 pelos valores de arrecadação em ordem decrescente
        top5Clientes = sortByValue(top5Clientes);

        // Mantém apenas os 5 primeiros clientes no Top5
        if (top5Clientes.size() > 5) {
            Iterator<Map.Entry<Cliente, Double>> iterator = top5Clientes.entrySet().iterator();
            Map.Entry<Cliente, Double> entry = null;
            for (int i = 0; i < 5 && iterator.hasNext(); i++) {
                entry = iterator.next();
            }
            if (entry != null) {
                iterator.remove();
            }
        }

        // Atualiza a arrecadação mensal
        arrecadacaoMensal += novaArrecadacao;
    }
    /**
     * Método que retorna o Top5 de clientes do estacionamento.
     * @return Map com os 5 clientes que mais arrecadaram no estacionamento.
     */
    public Map<Cliente, Double> getTop5Clientes() {
        return top5Clientes;
    }
    /**
     * Método que retorna a arrecadação mensal do estacionamento.
     * @return Arrecadação mensal do estacionamento.
     */
    public double getArrecadacaoMensal() {
        return arrecadacaoMensal;
    }

    /**
     * Método utilitário que ordena um Map por seus valores.
     * @param <K> uma chave associada a um cliente
     * @param <V> um valor que represente a arrecadação do cliente que extenda qualquer superclasse de Comparable
     * @param map Map que será ordenado.
     * @return Map ordenado.
     */
    private <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
 }
}
