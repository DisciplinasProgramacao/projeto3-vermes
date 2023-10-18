import java.time.LocalDateTime;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private boolean manobristaContratado;
    private boolean lavagemContratada;
    private boolean polimentoContratado;

    

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now(); 
        this.manobristaContratado = false;
        this.lavagemContratada = false;
        this.polimentoContratado = false;

    }
    public void contratarManobrista(){
        this.manobristaContratado = true;
    }
    public void contratarLavagem(){
        this.lavagemContratada = true;
    }
    public void contratarPolimento(){
        this.polimentoContratado = true;
    }

    public void sair() {
        this.saida = LocalDateTime.now(); 
        calcularValorPago();
    }

    public double valorPago() {
        return valorPago;
    }

    private void calcularValorPago() {
        if (manobristaContratado) {
            valorPago += 5.0; // Custo do Manobrista
        }
        if (lavagemContratada && calcularDiferencaEmMinutos(entrada, saida) >= 60) {
            valorPago += 20.0; // Custo da Lavagem (se permanência for de pelo menos 1 hora)
        }
        if (polimentoContratado && calcularDiferencaEmMinutos(entrada, saida) >= 120) {
            valorPago += 45.0; // Custo do Polimento (se permanência for de pelo menos 2 horas)
        }
        long minutos = calcularDiferencaEmMinutos(entrada, saida);
        double valorTemp = (minutos / 15) * VALOR_FRACAO;
        valorPago = valorTemp > VALOR_MAXIMO ? VALOR_MAXIMO : valorTemp;
    }

    private long calcularDiferencaEmMinutos(LocalDateTime inicio, LocalDateTime fim) {
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