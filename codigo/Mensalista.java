public class Mensalista extends UsoDeVaga implements Diario {
    
    TipoDePlano tipoDePlano = TipoDePlano.MENSALISTA;
    private double valorPago;

    public Mensalista(Vaga vaga) throws VagaIndisoponivelException{
       super(vaga);
    }
    @Override
    public boolean estacionar() {
        return true;
    }
    @Override
    public void calcularValorPago(Cliente cliente) {
        this.valorPago+= 500;
    }
    @Override
    public double valorPago() {
        return valorPago;
    }
}
