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

    public String getNome(){
        return nome;
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {

        Cliente quem = new Cliente("idCli",idCli);
		Cliente cliente = busca(quem);
        if (cliente != null) {
            cliente.addVeiculo(veiculo);
        }
    }

 public boolean addCliente(Cliente cliente) {
        if ((busca(cliente)) == null) {
            clientes.add(cliente);
            return true;
        }
        else {
            return false;
        }
    }


	private Cliente busca(Cliente quem){
		for (Cliente cliente : clientes) {
            if (cliente != null && cliente.equals(quem)) {
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

public void estacionar(String placa) throws EstacionamentoLotadoException {
    boolean estacionado = false;
    for (Vaga vaga : vagas) {
        if (vaga.disponivel()) {
            estacionado = true;
            if (clientes.isEmpty()) {
                System.out.println("Não há clientes cadastrados. O veículo com a placa " + placa + " não pode ser estacionado.");
                return;
            }
            Cliente cliente = clientes.getFirst();
            Veiculo novoVeiculo = new Veiculo(placa);
            vaga.estacionar();

            cliente.addVeiculo(novoVeiculo);
            novoVeiculo.estacionar(vaga);

            return;
        }
    }

    if (!estacionado) {
        throw new EstacionamentoLotadoException("Estacionamento lotado. Não há vagas disponíveis para estacionar o veículo com a placa " + placa);
    }
}

     public double sair(String placa) {
        for (Cliente cliente : clientes) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                double valorPago = veiculo.sair();
                System.out.println("Valor a ser pago: " + valorPago);
                return valorPago;
            }
        }

        System.out.println("Veículo não encontrado.");
        return 0.0;
    }

    public double totalArrecadado() {
        double total = 0.0;
        for (UsoDeVaga uso : usos) {
            total += uso.getValorPago();
        }
        return total;
    }

        public double arrecadacaoNoMes(int mes) {
		double totalMes = 0;
		for (Cliente cliente : clientes) {
			if (cliente != null) {
				totalMes += cliente.arrecadadoNoMes(mes);
			}
		}
		return totalMes;
	}


    public double valorMedioPorUso() {
        if (usos.isEmpty()) {
            return 0.0;
        }
        return totalArrecadado() / usos.size();
    }
    
public String top5Clientes(int mes) {
    List<Cliente> topClientes = new LinkedList<>();
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
        resultado += (i + 1) + ". Cliente: " + cliente +
                ", Arrecadação no mês: R$" + cliente.arrecadadoNoMes(mes) + "\n";
    }

    return resultado;
}

    }
