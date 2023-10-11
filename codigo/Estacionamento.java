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

  

   
   

    public void estacionar(String placa) {
        for (Vaga vaga : vagas) {
            if (vaga.disponivel()) {
                if (clientes.isEmpty()) {
                    System.out.println("Não há clientes cadastrados. O veículo com a placa " + placa + " não pode ser estacionado.");
                    return;
                }
                Cliente cliente = clientes.getFirst();
                
                Veiculo novoVeiculo = new Veiculo(placa);
            
                vaga.estacionar();
                
                cliente.addVeiculo(novoVeiculo);
                
                return;
            }
        }
    
        System.out.println("Não há vagas disponíveis para estacionar o veículo com a placa " + placa);
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
            total += uso.valorPago();
        }
        return total;
    }

    public double arrecadacaoNoMes(int mes) {
        double total = 0.0;
        for (UsoDeVaga uso : usos) {
            LocalDate dataSaida = uso.getSaida().toLocalDate();
            if (dataSaida.getMonthValue() == mes) {
                total += uso.valorPago();
            }
        }
        return total;
    }

	
	
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


