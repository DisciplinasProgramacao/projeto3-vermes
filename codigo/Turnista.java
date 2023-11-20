import java.time.LocalTime;

public class Turnista extends UsoDeVaga implements Diario {
    
    private static final double VALOR_MAXIMO = 50.0;
    private static final long VALOR_FRACAO = 4.0;
    TipoDePlano tipoDePlano = TipoDePlano.TURNISTA;
    Turno turno;
    private double valorPago;

    public Turnista(Vaga vaga, Turno turno) throws VagaIndisoponivelException{
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
        if(estacionar())
        valorPago+= 200;
        else{
        valorPago+= 200;    
        long minutos = calcularDiferencaEmMinutos(getEntrada(), getSaida());
        double valorTemp = (minutos / 15) * VALOR_FRACAO;
        valorPago = valorTemp > VALOR_MAXIMO ? VALOR_MAXIMO : valorTemp;
        if (getServico() != null) {
            valorPago += getServico().getValor();
        }
        }

    }


    Turno getTurno() {
        return turno;
    }
}
