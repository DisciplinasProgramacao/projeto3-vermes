import java.io.Serializable;

/**
 * Classe que representa um veículo estacionado.
 */
public class Veiculo implements Serializable {

    private String placa;
    private UsoDeVaga[] usos;
    private static final int MAX_USO = 1000;
    private int qtUso = 0;

    /**
     * Construtor para criar um novo veículo com uma placa.
     *
     * @param placa A placa do veículo.
     */
    public Veiculo(String placa) {
        this.placa = placa;
        this.usos = new UsoDeVaga[MAX_USO];
    }

    /**
     * Estaciona o veículo em uma vaga.
     *
     * @param vaga A vaga em que o veículo será estacionado.
     * @throws VagaIndisoponivelException
     */
    public void estacionar(Vaga vaga) throws VagaIndisoponivelException {
        if (qtUso < MAX_USO) {
            UsoDeVaga novoUso = new UsoDeVaga(vaga);
            usos[qtUso] = novoUso;
            qtUso++;
        } else {
            System.out.println("Capacidade máxima de uso atingida.");
        }
    }

    /**
     * Retira o veículo da vaga e calcula o valor a ser pago.
     *
     * @return O valor a ser pago pelo uso da vaga.
     * @throws ServicoNaoExecutadoException
     */
    public double sair() throws ServicoNaoExecutadoException {
        if (qtUso > 0) {
            UsoDeVaga ultimoUso = usos[qtUso - 1];
            if (ultimoUso != null) {
                ultimoUso.sair();
                double valorPago = ultimoUso.valorPago();
                return valorPago;
            } else {
                System.out.println("Erro");
                return 0.0;
            }
        } else {
            System.out.println("Não há carros estacionados.");
            return 0.0;
        }
    }

    /**
     * Calcula o valor total arrecadado com o uso das vagas.
     *
     * @return O valor total arrecadado.
     */
    public double totalArrecadado() {
        double valorTotal = 0.0;
        for (int i = 0; i < qtUso; i++) {
            valorTotal += usos[i].valorPago();
        }
        return valorTotal;
    }

    /**
     * Calcula o valor arrecadado no mês especificado.
     *
     * @param mes O mês para o qual o valor arrecadado será calculado.
     * @return O valor arrecadado no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double valorArrecadado = 0.0;
        for (int i = 0; i < qtUso; i++) {
            if (usos[i] != null && usos[i].getMes() == mes) {
                valorArrecadado += usos[i].valorPago();
            }
        }
        return valorArrecadado;
    }

    /**
     * Obtém o total de usos da vaga pelo veículo.
     *
     * @return O total de usos da vaga.
     */
    public int totalDeUsos() {
        return qtUso;
    }

    /**
     * Obtém a placa do veículo.
     *
     * @return A placa do veículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Obtém o uso de vaga pelo índice especificado.
     *
     * @param n O índice do uso de vaga desejado.
     * @return O uso de vaga no índice especificado.
     */
    public UsoDeVaga getUsoDeVaga(int n) {
        if (n >= 0 && n < qtUso) {
            return this.usos[n];
        } else {
            System.out.println("Índice de uso inválido.");
            return null;
        }
    }
}
