public class Cliente {

    private String nome;
    private String id;
    private Veiculo[] veiculos;

    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new Veiculo[50];
    }

    public void addVeiculo(Veiculo veiculo) {
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] == null) {
                veiculos[i] = veiculo;
                break;
            }
        }
    }

    public Veiculo possuiVeiculo(String placa) {
		Veiculo quem = new Veiculo(placa);
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null && veiculos[i].equals(quem)) {
                return veiculos[i];
            }
        }
        return null;
    }

    public int totalDeUsos() {
        int totalUsos = 0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                totalUsos += veiculos[i].totalDeUsos();
            }
        }
        return totalUsos;
    }

    public double arrecadadoPorVeiculo(String placa) {
        Veiculo veiculo = possuiVeiculo(placa);
        if (veiculo != null) {
            return veiculo.totalArrecadado();
        }
        return 0.0;
    }

    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                totalArrecadado += veiculos[i].totalArrecadado();
            }
        }
        return totalArrecadado;
    }

    public double arrecadadoNoMes(int mes) {
        double arrecadadoMes = 0.0;
        for (int i = 0; i < veiculos.length; i++) {
            if (veiculos[i] != null) {
                arrecadadoMes += veiculos[i].arrecadadoNoMes(mes);
            }
        }
        return arrecadadoMes;
    }
}
