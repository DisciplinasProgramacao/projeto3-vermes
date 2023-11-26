/**
 * A classe `Mensalista` representa um uso de vaga mensalista em um estacionamento, implementando a interface Diario.
 * Um mensalista possui um tipo de plano, uma vaga associada e um valor pago mensalmente.
 */
public class Mensalista extends UsoDeVaga implements Diario {

    /** O tipo de plano associado ao mensalista, que é sempre do tipo MENSALISTA. */
    TipoDePlano tipoDePlano = TipoDePlano.MENSALISTA;

    /** O valor total pago pelo mensalista. */
    private double valorPago;

    /**
     * Cria um novo objeto `Mensalista` associado a uma vaga específica.
     *
     * @param vaga A vaga associada ao mensalista.
     * @throws VagaIndisponivelException Se a vaga estiver ocupada.
     */
    public Mensalista(Vaga vaga) throws VagaIndisoponivelException {
        super(vaga);
    }

    /**
     * Estaciona o veículo do mensalista. Sempre retorna verdadeiro, pois mensalistas podem estacionar a qualquer momento.
     *
     * @return Sempre retorna verdadeiro.
     */
    @Override
    public boolean estacionar() {
        return true;
    }

    /**
     * Calcula o valor a ser pago pelo mensalista. No caso dos mensalistas, o valor é fixo em R$500 por mês.
     *
     * @param cliente O cliente associado ao mensalista.
     */
    @Override
    public void calcularValorPago(Cliente cliente) {
        this.valorPago += 500;
    }

    /**
     * Obtém o valor total pago pelo mensalista.
     *
     * @return O valor total pago pelo mensalista.
     */
    @Override
    public double valorPago() {
        return valorPago;
    }
}
