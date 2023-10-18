

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
		if (qtUso > 0) {
			qtUso--;
			UsoDeVaga ultimoUso = usos[qtUso];
			if (ultimoUso != null) {
				ultimoUso.sair(); 
				double valorPago = ultimoUso.valorPago();
				usos[qtUso] = null;
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
			if (uso != null && uso.getMes() == mes) {
				valorArrecadado += uso.valorPago();
			}
		}
		return valorArrecadado;
	}

	public int totalDeUsos() {
		return usos.length;
	}

}
