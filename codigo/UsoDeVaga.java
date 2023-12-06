import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class UsoDeVaga implements Serializable {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private Servico servico;

    /**
     * Construtor da classe UsoDeVaga.
     * @param vaga Vaga a ser utilizada.
     * @throws VagaIndisoponivelException Lança e exceção.
     */
    public UsoDeVaga(Vaga vaga) throws VagaIndisoponivelException {
        this.vaga = vaga;

        if (vaga.disponivel()) {
            vaga.estacionar();
            this.entrada = LocalDateTime.now();
            this.valorPago = VALOR_FRACAO; 
        } else {
            throw new VagaIndisoponivelException("A vaga está indisponível.");
        }
    }

    /**
     * Método para contratar um serviço para o veículo.
     * @param qual Serviço a ser contratado.
     */
    public void contratarServico(Servico qual) {
        this.servico = qual;
    }

    /**
     * Registra a saída do veículo da vaga.
     * @param cliente recebe um objeto do tipo Cliente.
     * @throws ServicoNaoExecutadoException Lança a exceção.
     */
    public void sair(Cliente cliente) throws ServicoNaoExecutadoException {
        this.saida = LocalDateTime.now();
        calcularValorPago(cliente);
    
        if (servico != null) {
            double tempoMinimo = servico.getTempo();
    
            double tempo = calcularDiferencaEmMinutos(entrada, saida);
            if (tempo < tempoMinimo) {
                throw new ServicoNaoExecutadoException("O serviço não foi executado. O tempo mínimo é de " + servico.getTempo() + " minutos.");
            }
        }
    }

    public Servico getServico() {
        return servico;
    }

    public double valorPago() {
        return valorPago;
    }

    /**
     * Calcula o valor a ser pago pelo uso da vaga.
     * @param cliente Recebe um objeto do tipo Cliente.
     */
    public void calcularValorPago(Cliente cliente) {
        if (cliente.isMensalista() || (cliente.isTurnista() && cliente.getTurno().eHorarioDoTurno(LocalTime.now()))) {
            // Se o cliente for mensalista, o valor pago é 0, independentemente do tempo de uso ou serviços contratados
            valorPago = 0;

        } else {
            long minutos = calcularDiferencaEmMinutos(entrada, saida);
            double valorTemp = Math.ceil((double) minutos / 15) * VALOR_FRACAO;
            valorPago = valorTemp > VALOR_MAXIMO ? VALOR_MAXIMO : valorTemp;
            if (servico != null) {
                valorPago += servico.getValor();
            }
        }
    }

    /**
     * Calcula a diferença em minutos entre duas datas.
     * @param inicio Data inicial.
     * @param fim Data final.
     * @return diferença em minutos entre duas datas.
     */
    public long calcularDiferencaEmMinutos(LocalDateTime inicio, LocalDateTime fim) {
        long diferencaEmMinutos = 0;
        while (inicio.isBefore(fim)) {
            inicio = inicio.plusMinutes(1);
            diferencaEmMinutos++;
        }
        return diferencaEmMinutos;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public int getMes() {
        return entrada.getMonthValue();
    }

    public Vaga getVaga() {
        return this.vaga;
    }
    @Override
    public String toString() {
        return "UsoDeVaga [entrada=" + entrada + ", saida=" + saida + ", valorPago=" + valorPago + ", servico=" + servico + "]";
    }
}

