import java.util.List;
import java.io.Serializable;

/**
 * A classe `Cliente` representa um cliente que possui veículos e um histórico de uso de vagas.
 */
public class Cliente implements Serializable {

    private String nome;
    private String id;
    private Veiculo[] veiculos;
    private Historico historico;

    /**
     * Cria um novo objeto `Cliente` com um nome e um ID.
     *
     * @param nome O nome do cliente.
     * @param id   O ID único do cliente.
     */
    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new Veiculo[50];
        this.historico = new Historico();
    }

    /**
     * Obtém o ID do cliente.
     *
     * @return O ID do cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Adiciona um veículo à lista de veículos do cliente.
     *
     * @param veiculo O veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo veiculo, Servico servico) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] == null) {
                veiculos[i] = veiculo;
                break;
            }
        }
        veiculo.setServico(servico);  // Define o serviço no veículo
    }

    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     *
     * @param placa A placa do veículo a ser verificada.
     * @return O veículo com a placa especificada, se encontrado; caso contrário, retorna null.
     */
    public Veiculo possuiVeiculo(String placa) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null && veiculos[i].getPlaca().equals(placa)) {
                return veiculos[i];
            }
        }
        return null;
    }

    /**
     * Obtém o total de usos de todas os veículos do cliente.
     *
     * @return O número total de usos de todas os veículos do cliente.
     */
    public int totalDeUsos() {
        int totalUsos = 0;
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null) {
                totalUsos += veiculo.totalDeUsos();
            }
        }
        return totalUsos;
    }

    /**
     * Calcula a arrecadação total por um veículo com a placa especificada.
     *
     * @param placa A placa do veículo.
     * @return A arrecadação total do veículo com a placa especificada.
     */
    public double arrecadadoPorVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                return veiculo.totalArrecadado();
            }
        }
        return 0.0;
    }

    /**
     * Calcula a arrecadação total de todos os veículos do cliente.
     *
     * @return A arrecadação total de todos os veículos do cliente.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                totalArrecadado += veiculos[i].totalArrecadado();
            }
        }
        return totalArrecadado;
    }

    /**
     * Calcula a arrecadação total no mês especificado.
     *
     * @param mes O mês para o qual a arrecadação deve ser calculada.
     * @return A arrecadação total no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double arrecadadoMes = 0.0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                arrecadadoMes += veiculos[i].arrecadadoNoMes(mes);
            }
        }
        return arrecadadoMes;
    }

    /**
     * Obtém o histórico completo de uso de vagas dos veículos do cliente.
     *
     * @return Uma representação em formato de string do histórico de uso de vagas.
     */
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
                            .append("Valor Total Pago: ").append(usoDeVaga.valorPago()).append(" | ");
                }
            }
        }

        return historicoCompleto.length() > 0 ? historicoCompleto.toString() : "Não possui histórico.";
    }

    /**
     * Obtém o histórico de uso de vagas como uma lista de registros.
     *
     * @return Uma lista de registros do histórico de uso de vagas.
     */
    public List<Registro> getHistorico() {
        return historico.getHistorico();
    }
    public int obterNumeroUtilizacoesNoMes(int mesCorrente) {
        return 0;
    }
}
