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

   
   

    public void estacionar(String placa) {
        Veiculo veiculo = buscarVeiculo(placa);
        if (veiculo != null) {
            for (Vaga vaga : vagas) {
                if (vaga.estacionar()) {
                    veiculo.estacionar(vaga);
                    UsoDeVaga uso = new UsoDeVaga(vaga);
                    uso.setEntrada(LocalDateTime.now());
                    usos.add(uso);
                    break;
                }
            }
        }
    }

    public double sair(String placa) {
        Veiculo veiculo = buscarVeiculo(placa);
        if (veiculo != null) {
            for (UsoDeVaga uso : usos) {
                if (uso.getVaga().getVeiculo().equals(veiculo)) {
                    uso.setSaida(LocalDateTime.now());
                    double valorPago = uso.sair();
                    return valorPago;
                }
            }
        }
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
        double total = 0.0;
        for (UsoDeVaga uso : usos) {
            LocalDate dataSaida = uso.getSaida().toLocalDate();
            if (dataSaida.getMonthValue() == mes) {
                total += uso.getValorPago();
            }
        }
        return total;
    }

	public void addVeiculo(Veiculo veiculo, String idCli) {
			
		}
	
		public void addCliente(Cliente cliente) {
			
		}
	
		private void gerarVagas() {
			
		}
	
		public double valorMedioPorUso() {
			
		}
	
		public String top5Clientes(int mes) {
			
		}
	
		
	
		
	
	}


