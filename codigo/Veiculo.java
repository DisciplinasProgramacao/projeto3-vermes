

public class Veiculo {

	private String placa;
	private UsoDeVaga[] usos;
	private static final int MAX_USO = 1000;
	private int qtUso = 0;

	public Veiculo(String placa) {
		this.placa = placa;
        this.usos = new UsoDeVaga[MAX_USO];
	}

	public void estacionar(Vaga vaga) {
		 UsoDeVaga novoUso = new UsoDeVaga(vaga);
		 usos[qtUso] = novoUso;
		 qtUso++;
	}

	public double sair() {
		
	}

	public double totalArrecadado() {
		double valorTotal = 0.0;
		for (UsoDeVaga uso : usos){
		valorTotal += uso.valorPago();
		}
        return valorTotal;
	}

	public double arrecadadoNoMes(int mes) {
		double valorArrecadado = 0.0;
		for (UsoDeVaga uso : usos) {
			if (uso != null && uso.getData().getMonth() == mes) {
				valorArrecadado += uso.valorMedioPorUso();
			}
		}
		return valorArrecadado;
	}

	public int totalDeUsos() {
		return usos.length;
	}

}
