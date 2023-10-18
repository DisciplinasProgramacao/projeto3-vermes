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
    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now(); 
         

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
    public void sair() {
        this.saida = LocalDateTime.now(); 
        calcularValorPago();
        
        if(servico != null)
        {double tempoMini = servico.getTempo();}
        
        double tempo = calcularDiferencaEmMinutos(entrada, saida);

        
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
        
        long minutos = calcularDiferencaEmMinutos(entrada, saida);
        double valorTemp = (minutos / 15) * VALOR_FRACAO;
        valorPago = valorTemp > VALOR_MAXIMO ? VALOR_MAXIMO : valorTemp;
         if(servico != null)
         {valorPago += servico.getValor();}
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
}