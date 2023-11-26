/**
 * A classe `Horista` representa um uso de vaga por um cliente do tipo horista em um estacionamento,
 * implementando a interface Diario.
 * Um horista possui um tipo de plano, um valor pago e métodos para calcular e obter esse valor.
 */
public class Horista extends UsoDeVaga implements Diario {

    /** O tipo de plano associado ao horista, que é sempre do tipo HORISTA. */
    TipoDePlano tipoDePlano = TipoDePlano.HORISTA;

    /** O valor total pago pelo horista. */
    private double valorPago;

    /**
     * Cria um novo objeto `Horista` associado a uma vaga específica.
     *
     * @param vaga A vaga associada ao horista.
     * @throws VagaIndisponivelException Se a vaga estiver ocupada.
     */
    public Horista(Vaga vaga) throws VagaIndisponivelException {
       super(vaga);
    }

    /**
     * Permite que o cliente do tipo horista estacione a qualquer momento.
     *
     * @return true, indicando que o cliente horista pode estacionar a qualquer momento.
     */
    @Override
    public boolean estacionar() {
        return true;
    }

    /**
     * Obtém o valor total pago pelo horista.
     *
     * @return O valor total pago pelo horista.
     */
    @Override
    public double valorPago() {
        return valorPago;
    }
}
