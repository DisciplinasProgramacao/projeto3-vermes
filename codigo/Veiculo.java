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
 * @throws VagaIndisponivelException Lança a exceção.
 * @throws LotadoException Lança a exceção
 */
public void estacionar(Vaga vaga, Cliente cliente) throws LotadoException, VagaIndisoponivelException {
    if (qtUso < MAX_USO) {
        UsoDeVaga novoUso = new UsoDeVaga(vaga);
        usos[qtUso] = novoUso;
        qtUso++;

        // Verifica o tipo de plano do cliente
        if (cliente.isMensalista()) {
            
            Mensalista mensalista = new Mensalista(vaga);
            cliente.addUsoDeVagaMensalista(mensalista);
        } else if (cliente.isTurnista()) {
            
            Turnista turnista = new Turnista(vaga, cliente.getTurno());
            cliente.addUsoDeVagaTurnista(turnista);
        } else {
            
            Horista horista = new Horista(vaga);
            cliente.addUsoDeVagaHorista(horista);
        }

    } else {
        throw new LotadoException();
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
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}

