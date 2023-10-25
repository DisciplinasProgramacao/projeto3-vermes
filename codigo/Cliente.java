import java.util.List;

public class Cliente {

    private String nome;
    private String id;
    private Veiculo[] veiculos;
    private Historico historico;

    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new Veiculo[50];
        this.historico = new Historico();
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void addVeiculo(Veiculo veiculo) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] == null) {
                veiculos[i] = veiculo;
                break;
            }
        }
    }

    public Veiculo possuiVeiculo(String placa) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null && (veiculos[i].getPlaca()).equals(placa)) {
                return veiculos[i];
            }
        }
        return null;
    }

	public int totalDeUsos() {
		int totalUsos = 0;
		for (Veiculo veiculo : veiculos) {
			if (veiculo != null) {
				totalUsos += veiculo.totalDeUsos();
			}
		}
		return totalUsos;
	}
	
	public double arrecadadoPorVeiculo(String placa) {
		for (Veiculo veiculo : veiculos) {
			if (veiculo != null && veiculo.getPlaca().equals(placa)) {
				return veiculo.totalArrecadado();
			}
		}
		return 0.0; 
	}
	
    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                totalArrecadado += veiculos[i].totalArrecadado();
            }
        }
        return totalArrecadado;
    }

    public double arrecadadoNoMes(int mes) {
        double arrecadadoMes = 0.0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                arrecadadoMes += veiculos[i].arrecadadoNoMes(mes);
            }
        }
        return arrecadadoMes;
    }

    public String historico() {
        StringBuilder historicoCompleto = new StringBuilder();

        for (int i = 0; i < veiculos.length; i++) {
            Veiculo veiculo = veiculos[i];
            if (veiculo != null) {
                int totalUsos = veiculo.totalDeUsos();

                for (int j = 0; j < totalUsos; j++) {
                    UsoDeVaga usoDeVaga = veiculo.getUsoDeVaga(j);

                    historicoCompleto.append("\n")
                            .append("Placa do Veículo: ").append(veiculo.getPlaca()).append(" | ")
                            .append("Vaga utilizada: ").append(usoDeVaga.getVaga().toString()).append(" | ")
                            .append("Data de Entrada: ").append(usoDeVaga.getEntrada()).append(" | ")
                            .append("Data de Saída: ").append(usoDeVaga.getSaida()).append(" | ")
                            .append("Valor Total Pago: ").append(usoDeVaga.getValorPago()).append(" | ");
                }
            }
        }

        return historicoCompleto.length() > 0 ? historicoCompleto.toString() : "Não possui histórico.";
    }

    public List<Registro> getHistorico() {
        return historico.getHistorico();
    }
}
