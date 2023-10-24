

public class Veiculo {

	private String placa;
	private UsoDeVaga[] usos;
	private static final int MAX_USO = 1000;
	private int qtUso;
	

	public Veiculo(String placa) {
		this.placa = placa;
        this.usos = new UsoDeVaga[MAX_USO];

	}

	public void estacionar(Vaga vaga) {
		 UsoDeVaga novoUso = new UsoDeVaga(vaga);
		 usos[++qtUso] = novoUso;

	}


	public double sair() {
		if (qtUso > 0) {
			
			UsoDeVaga ultimoUso = usos[qtUso];
			if (ultimoUso != null) {
				ultimoUso.sair(); 
				double valorPago = ultimoUso.getValorPago();
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
		valorTotal += uso.getValorPago();
		}
        return valorTotal;
	}

	public double arrecadadoNoMes(int mes) {
		double valorArrecadado = 0.0;
		for (UsoDeVaga uso : usos) {
			if (uso != null && uso.getMes() == mes) {
				valorArrecadado += uso.getValorPago();
			}
		}
		return valorArrecadado;
	}

	public int totalDeUsos() {
		return qtUso;
	}

	public String getPlaca() {
        return placa;
    }

}
