import java.time.LocalDateTime;

public class UsoDeVaga {

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
      */
    public UsoDeVaga(Vaga vaga) throws VagaIndisoponivelException{
        this.vaga = vaga;
        this.entrada = LocalDateTime.now(); 
         
        if (vaga.disponivel()) {
            vaga.estacionar();
        } else {
            throw new VagaIndisoponivelException("A vaga está indisponível.");
        }

    }
    /**
     *  Método para contratar um serviço para o veículo.
     * @param qual Serviço a ser contratado.
     */
    public void contratarServico(Servico qual){
        this.servico = qual;
    }
     /**
      * Registra a saída do veículo da vaga.
      */
    public void sair() throws ServicoNaoExecutadoException {
        this.saida = LocalDateTime.now(); 
        calcularValorPago();
       
        if(servico != null)
        {
        double tempoMini = servico.getTempo();
        
        double tempo = calcularDiferencaEmMinutos(entrada, saida);
            if(tempo < tempoMini){
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
      */
    public void calcularValorPago() {
        
        public void calcularValorPago() {
    // Calcula a diferença em minutos entre a entrada e a saída
    long minutos = calcularDiferencaEmMinutos(entrada, saida);

    // Arredonda para cima para garantir que mesmo com poucos minutos, seja cobrado o equivalente a 15 minutos
    long minutosArredondados = (long) Math.ceil((double) minutos / 15) * 15;

    // Calcula o valor com base nos minutos arredondados
    double valorTemp = (minutosArredondados / 15) * VALOR_FRACAO;

    // Verifica se o valor calculado é maior que o VALOR_MAXIMO
    valorPago = valorTemp > VALOR_MAXIMO ? VALOR_MAXIMO : valorTemp;

    if (servico != null) {
        valorPago += servico.getValor();
    }
}
    }
    /**
     * Calcula a diferença em minutos entre duas datas.
     * @param inicio Data inicial.
     * @param fim  Data final.
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
    public LocalDateTime getEntrada(){
        return entrada;
    }

    public int getMes(){
        return entrada.getMonthValue();
    }
    public Vaga getVaga(){
     return this.vaga;
    }

}
