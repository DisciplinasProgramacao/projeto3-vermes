import java.time.LocalTime;

public class Turnista extends UsoDeVaga implements Diario {

    private static final double VALOR_MAXIMO = 50.0;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_HORISTA = 0;
    private TipoDePlano tipoDePlano = TipoDePlano.TURNISTA;
    private Turno turno;
    private double valorPago;

    // Construtor que aceita a Vaga e o Turno
    public Turnista(Vaga vaga, Turno turno) throws VagaIndisoponivelException {
        super(vaga);
        this.turno = turno;
    }

    @Override
    public boolean estacionar() {
        return turno.eHorarioDoTurno(LocalTime.now());
    }

    @Override
    public double valorPago() {
        return valorPago;
    }

    @Override
    public void calcularValorPago() {
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

    private double calcularValorEstacionamento() {
        long minutos = calcularDiferencaEmMinutos(getEntrada(), getSaida());
        double valorTemp = (minutos / 15) * VALOR_FRACAO;
        return valorTemp > VALOR_MAXIMO ? VALOR_MAXIMO : valorTemp;
    }

    Turno getTurno() {
        return turno;
    }
}
