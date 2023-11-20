public class Horista extends UsoDeVaga implements Diario {
    
    TipoDePlano tipoDePlano = TipoDePlano.HORISTA;
    private double valorPago;

    public Horista(Vaga vaga) throws VagaIndisoponivelException{
       super(vaga);
    }

    @Override
    public boolean estacionar() {
        return true;
    }

    
    @Override
    public double valorPago() {
        return valorPago;
    }
}
