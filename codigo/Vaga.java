

public class Vaga {

	
	private boolean disponivel;
	private String id;

	public Vaga(int fila, int numero) {
		id = Integer.toString(fila) + " - "+ Integer.toString(numero);
		disponivel = true;

	}

	public boolean estacionar() {
		if (disponivel) {
			disponivel = false;
			return true;
		}
		return false;
	}

	public boolean sair() {
		if (!disponivel) {
			disponivel = true;
			return true;
		}
		return false;
	}

	public boolean disponivel() {
		return disponivel;
	}

}