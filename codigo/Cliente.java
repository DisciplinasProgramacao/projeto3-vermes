import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 * A classe `Cliente` representa um cliente que possui veículos e um histórico de uso de vagas.
 */
public class Cliente implements Serializable {

    private String nome;
    private String id;
    private Veiculo[] veiculos;
    private Historico historico;
    private TipoDePlano tipoDePlano;
    private Turno turno;
    private static double taxaMensal;
    private static double taxaTurnista;

    /**
     * Cria um novo objeto `Cliente` com um nome e um ID.
     *
     * @param nome O nome do cliente.
     * @param id   O ID único do cliente.
     */
    public Cliente(String nome, String id, TipoDePlano tipoDePlano, double taxaMensal) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new Veiculo[50];
        this.historico = new Historico();
        this.tipoDePlano = tipoDePlano;
        Cliente.taxaMensal = taxaMensal;
    }

    // Construtor para clientes turnistas
    public Cliente(String nome, String id, TipoDePlano tipoDePlano, Turno turno) {
        this(nome, id, tipoDePlano, taxaMensal);
        this.turno = turno;
        this.taxaTurnista = 200;
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
     * Obtém o tipo de plano associado ao cliente.
     *
     * @return O tipo de plano do cliente.
     */
    public TipoDePlano getTipoDePlano() {
        return tipoDePlano;
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
     * @param servico O serviço associado ao veículo.
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
                totalArrecadado += veiculos[i].totalArrecadado() + calcularMensalidade() +  calcularTaxaTurnista();
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
                            .append("Valor Pago sem Serviços: ").append(usoDeVaga.valorPago()).append(" | ");
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

    /**
     * Obtém o número de utilizações no mês corrente.
     *
     * @param mesCorrente O mês corrente.
     * @return O número de utilizações no mês corrente.
     */
    public int obterNumeroUtilizacoesNoMes(int mesCorrente) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
    
        int anoAtual = cal.get(Calendar.YEAR);
    
        return historico.getHistorico()
                .stream()
                .filter(registro -> {
                    cal.setTime(registro.getData());
                    int mesRegistro = cal.get(Calendar.MONTH) + 1; // Adiciona 1 porque os meses são indexados de 0 a 11
                    int anoRegistro = cal.get(Calendar.YEAR);
                    return mesRegistro == mesCorrente && anoRegistro == anoAtual;
                })
                .mapToInt(registro -> 1) // Mapear cada registro para 1 (representando uma utilização)
                .sum();
    }

    /**
     * Define o plano associado ao cliente.
     *
     * @param plano O tipo de plano a ser associado ao cliente.
     */
    public void escolherPlano(TipoDePlano plano) {
        this.tipoDePlano = plano;
    }

    /**
     * Calcula a mensalidade do cliente com base no tipo de plano.
     *
     * @return A mensalidade do cliente.
     */
    public double calcularMensalidade() {
        if (tipoDePlano == TipoDePlano.MENSALISTA) {
            return taxaMensal;
        } else {
            // Cliente Turnista
            return 0; // Turnistas têm uma mensalidade fixa de R$200, não importa o horário
        }
    }

    /**
     * Calcula a taxa adicional para clientes turnistas.
     *
     * @return A taxa adicional para clientes turnistas.
     */
    public double calcularTaxaTurnista() {
        if (tipoDePlano == TipoDePlano.TURNISTA) {
            return taxaTurnista;
        } else {
            // Cliente Turnista
            return 0; // Turnistas têm uma mensalidade fixa de R$200, não importa o horário
        }
    }

    /**
     * Verifica se o cliente possui o tipo de plano Mensalista.
     *
     * @return true se o cliente for mensalista, false caso contrário.
     */
    public boolean isMensalista() {
        return tipoDePlano == TipoDePlano.MENSALISTA;
    }

    /**
     * Verifica se o cliente possui o tipo de plano Turnista.
     *
     * @return true se o cliente for turnista, false caso contrário.
     */
    public boolean isTurnista() {
        return tipoDePlano == TipoDePlano.TURNISTA;
    }

    /**
     * Obtém o turno associado ao cliente (apenas para clientes turnistas).
     *
     * @return O turno associado ao cliente.
     */
    public Turno getTurno() {
        return turno;
    }
}
