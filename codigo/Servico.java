public enum Servico {
    MANOBRISTA(5.0,0.0),
    LAVAGEM(20.0,60.0),
    POLIMENTO(45.0,120.0);

    private double valor;
    private double tempo;

    Servico(double valor, double tempo) {
        this.valor = valor;
        this.tempo = tempo;
    }
    public double getValor() {
        return valor;
    }
    public double getTempo() {
        return tempo;
    }
}
