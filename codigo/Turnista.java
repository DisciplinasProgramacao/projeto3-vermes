import java.time.LocalTime;

/**
 * A classe `Turnista` representa um uso de vaga por um cliente do tipo turnista em um estacionamento,
 * implementando a interface Diario.
 * Um turnista possui um tipo de plano, um turno associado, um valor pago e métodos para calcular e obter esse valor.
 */
public class Turnista extends UsoDeVaga implements Diario {

    /** O valor máximo que um turnista pode pagar. */
    private static final double VALOR_MAXIMO = 50.0;

    /** O valor da fração de tempo utilizado no cálculo do valor a ser pago. */
    private static final double VALOR_FRACAO = 4.0;

    /** O valor padrão para turnistas, que é zero. */
    private static final double VALOR_HORISTA = 0;

    /** O tipo de plano associado ao turnista, que é sempre do tipo TURNISTA. */
    private TipoDePlano tipoDePlano = TipoDePlano.TURNISTA;

    /** O turno associado ao turnista. */
    private Turno turno;

    /** O valor total pago pelo turnista. */
    private double valorPago;

    /**
     * Cria um novo objeto `Turnista` associado a uma vaga específica e a um turno.
     *
     * @param vaga A vaga associada ao turnista.
     * @param turno O turno associado ao turnista.
     * @throws VagaIndisponivelException Se a vaga estiver ocupada.
     */
    public Turnista(Vaga vaga, Turno turno) throws VagaIndisponivelException {
        super(vaga);
        this.turno = turno;
    }

    /**
     * Verifica se o cliente do tipo turnista pode estacionar com base no horário do turno.
     *
     * @return true se o cliente pode estacionar no horário do turno, false caso contrário.
     */
    @Override
    public boolean estacionar() {
        return turno.eHorarioDoTurno(LocalTime.now());
    }

    /**
     * Obtém o valor total pago pelo turnista.
     *
     * @return O valor total pago pelo turnista.
     */
    @Override
    public double valorPago() {
        return valorPago;
    }

    /**
     * Calcula o valor a ser pago pelo turnista com base no tipo de plano e no serviço associado, se houver.
     *
     * @param cliente O cliente associado ao turnista.
     */
    @Override
    public void calcularValorPago(Cliente cliente) {
        if (tipoDePlano == TipoDePlano.TURNISTA) {
            if (turno.eHorarioDoTurno(LocalTime.now())) {
                // Cliente está estacionando no seu turno, não paga pelo estacionamento
                valorPago = 0.0;
            } else {
                // Cliente está estacionando fora do seu turno, paga como horista
                valorPago = VALOR_HORISTA;
            }
        } else {
            // Cliente é horista, paga pela fração de tempo
            valorPago = calcularValorEstacionamento();
        }

        if (getServico() != null) {
            valorPago += getServico().getValor();
        }
    }

    /**
     * Calcula o valor do estacionamento com base na diferença de tempo entre a entrada e a saída do cliente.
     *
     * @return O valor calculado do estacionamento.
     */
    private double calcularValorEstacionamento() {
        long minutos = calcularDiferencaEmMinutos(getEntrada(), getSaida());
        double valorTemp = (minutos / 15) * VALOR_FRACAO;
        return valorTemp > VALOR_MAXIMO ? VALOR_MAXIMO : valorTemp;
    }

    /**
     * Obtém o turno associado ao turnista.
     *
     * @return O turno associado ao turnista.
     */
    Turno getTurno() {
        return turno;
    }
}
