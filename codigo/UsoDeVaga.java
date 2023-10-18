import java.time.LocalDateTime;

public class UsoDeVaga {

    private static final double FRACAO_USO = 0.25;
    private static final double VALOR_FRACAO = 4.0;
    private static final double VALOR_MAXIMO = 50.0;
    private Vaga vaga;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;

    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now(); 
    }

    public void sair() {
        this.saida = LocalDateTime.now(); 
        calcularValorPago();
    }

    public double valorPago() {
        return valorPago;
    }

    private void calcularValorPago() {
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

    public int getMes() {
        return 0;
    }

}
