import java.io.Serializable;

/**
 * Classe que representa um veículo estacionado.
 */
public class Veiculo implements Serializable {

    private String placa;
    private UsoDeVaga[] usos;
    private static final int MAX_USO = 1000;
    private int qtUso = 0;
    private Servico servico;

    /**
     * Construtor para criar um novo veículo com uma placa.
     *
     * @param placa A placa do veículo.
     */
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[MAX_USO];
    }

    /**
 * Estaciona o veículo em uma vaga.
 *
 * @param vaga A vaga em que o veículo será estacionado.
 * @param cliente O cliente associado ao veículo.
 * @throws VagaIndisoponivelException Lança a exceção.
 * @throws LotadoException Lança a exceção
 */
public void estacionar(Vaga vaga, Cliente cliente) throws LotadoException, VagaIndisoponivelException {
    if (qtUso < MAX_USO) {
        UsoDeVagaFactory factory;

        // Escolhe a fábrica com base no tipo de cliente
        if (cliente.isMensalista()) {
            factory = new MensalistaFactory();
        } else if (cliente.isTurnista()) {
            factory = new TurnistaFactory(cliente.getTurno());
        } else {
            factory = new HoristaFactory();
        }

        try {
            // Cria uma instância de UsoDeVaga usando a fábrica
            UsoDeVaga novoUso = factory.criar(vaga);
            usos[qtUso] = novoUso;
            qtUso++;

            // Adiciona o uso de vaga ao cliente
            adicionarUsoDeVagaAoCliente(cliente, novoUso);

        } catch (VagaIndisoponivelException e) {
            e.printStackTrace(); 
        }

    } else {
        throw new LotadoException();
    }
}

// Método para adicionar o uso de vaga ao cliente
private void adicionarUsoDeVagaAoCliente(Cliente cliente, UsoDeVaga usoDeVaga) {
    if (cliente.isMensalista()) {
        cliente.addUsoDeVagaMensalista((Mensalista) usoDeVaga);
    } else if (cliente.isTurnista()) {
        cliente.addUsoDeVagaTurnista((Turnista) usoDeVaga);
    } else {
        cliente.addUsoDeVagaHorista((Horista) usoDeVaga);
    }
}


    /**
     * Retira o veículo da vaga e calcula o valor a ser pago.
     *
     * @return O valor a ser pago pelo uso da vaga.
     * @param cliente Recebe um Cliente para sair. 
     * @throws ServicoNaoExecutadoException Lança a excessão.
     */
    public double sair(Cliente cliente) throws ServicoNaoExecutadoException {
        if (qtUso > 0) {
            UsoDeVaga ultimoUso = usos[qtUso - 1];
            if (ultimoUso != null) {
                ultimoUso.sair(cliente);  // Mantenha a passagem do cliente aqui
                double valorPago = ultimoUso.valorPago();
                return valorPago;
            } else {
                System.out.println("Erro");
                return 0.0;
            }
        } else {
            System.out.println("Não há carros estacionados.");
            return 0.0;
        }
    }

    /**
     * Calcula o valor total arrecadado com o uso das vagas.
     *
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double valorTotal = 0.0;
        for (int i = 0; i < qtUso; i++) {
            valorTotal += usos[i].valorPago();
        }
        return valorTotal;
    }

    /**
     * Calcula o valor arrecadado no mês especificado.
     *
     * @param mes O mês para o qual o valor arrecadado será calculado.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double valorArrecadado = 0.0;
        for (int i = 0; i < qtUso; i++) {
            if (usos[i] != null && usos[i].getMes() == mes) {
                valorArrecadado += usos[i].valorPago();
            }
        }
        return valorArrecadado;
    }

    /**
     * Obtém o total de usos da vaga pelo veículo.
     *
     * @return O total de usos da vaga.
     */
    public int totalDeUsos() {
        return qtUso;
    }

    /**
     * Obtém a placa do veículo.
     *
     * @return A placa do veículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Obtém o uso de vaga pelo índice especificado.
     *
     * @param n O índice do uso de vaga desejado.
     * @return O uso de vaga no índice especificado.
     */
    public UsoDeVaga getUsoDeVaga(int n) {
        if (n >= 0 && n < qtUso) {
            return this.usos[n];
        } else {
            System.out.println("Índice de uso inválido.");
            return null;
        }
    }   
    /**
     * retorna um Serviço.
     * @return Serviço.
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * Define um Serviço.
     * @param servico Recebe um Serviço.
     */
    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
