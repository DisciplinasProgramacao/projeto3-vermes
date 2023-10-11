import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Estacionamento {

    private String nome;
    private LinkedList<Cliente> clientes;
    private LinkedList<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private LinkedList<UsoDeVaga> usos;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new LinkedList<>();
        this.usos = new LinkedList<>();
        gerarVagas();
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = buscarCliente(idCli);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        }
    }

    public void addCliente(Cliente cliente) {
        if (buscarCliente(cliente.getId()) == null) {
            clientes.add(cliente);
        }
    }

    private Cliente buscarCliente(String idCli) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.getId().equals(idCli)) {
                return cliente;
            }
        }
        return null;
    }

    private void gerarVagas() {
        vagas = new LinkedList<>();
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                int numeroVaga = i * vagasPorFileira + j + 1;
                vagas.add(new Vaga(i, numeroVaga));
            }
        }
    }

    public void estacionar(String placa) {
    }

    public double sair(String placa) {
        
    }

    public double totalArrecadado() {

    }

    public double arrecadacaoNoMes(int mes) {
 
    }

    public double valorMedioPorUso() {
        if (usos.isEmpty()) {
            return 0.0;
        }
        return totalArrecadado() / usos.size();
    }
public String top5Clientes(int mes) {
    List<Cliente> topClientes = new ArrayList<>();
    for (Cliente cliente : clientes) {
        double arrecadacao = cliente.arrecadadoNoMes(mes);
        for (int i = 0; i <= topClientes.size(); i++) {
            if (i == topClientes.size() || arrecadacao > topClientes.get(i).arrecadadoNoMes(mes)) {
                topClientes.add(i, cliente);
                break;
            }
        }
        if (topClientes.size() > 5) {
            topClientes.remove(topClientes.size() - 1);
        }
    }

    String resultado = "";
    for (int i = 0; i < topClientes.size(); i++) {
        Cliente cliente = topClientes.get(i);
        resultado += (i + 1) + ". Cliente: " + cliente.getNome() +
                ", Arrecadação no mês: R$" + cliente.arrecadadoNoMes(mes) + "\n";
    }

    return resultado;
}

    }

