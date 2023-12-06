import java.io.Serializable;
import java.util.*;

public class Relatorio implements Observer, Serializable{
    private Map<Cliente, Double> top5Clientes;
    private double arrecadacaoMensal;

    public Relatorio() {
        this.top5Clientes = new LinkedHashMap<>(); // LinkedHashMap para manter a ordem de inserção
        this.arrecadacaoMensal = 0.0;
    }

    @Override
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

    public Map<Cliente, Double> getTop5Clientes() {
        return top5Clientes;
    }

    public double getArrecadacaoMensal() {
        return arrecadacaoMensal;
    }

    // Método utilitário para ordenar um Map por valores
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

