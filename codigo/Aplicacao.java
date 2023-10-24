import java.util.List;
public class Aplicacao {

    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento("Estacionamento da Maisa", 5, 10);

        Cliente cliente1 = new Cliente("Maisa", "001");
        Cliente cliente2 = new Cliente("Tico", "002");

        estacionamento.addCliente(cliente1);
        estacionamento.addCliente(cliente2);

        Vaga vaga1 = estacionamento.getVagaDisponivel();
        Vaga vaga2 = estacionamento.getVagaDisponivel();
        Veiculo veiculo1 = new Veiculo("ABC111");
        Veiculo veiculo2 = new Veiculo("ABC222");

        cliente1.addVeiculo(veiculo1);
        cliente2.addVeiculo(veiculo2);

        veiculo1.estacionar(vaga1);
        veiculo2.estacionar(vaga2);

 
        double valorPago1 = veiculo1.sair();
        double valorPago2 = veiculo2.sair();

        System.out.println("Valor pago pelo veículo 1: R$" + valorPago1);
        System.out.println("Valor pago pelo veículo 2: R$" + valorPago2);


        List<Registro> historicoCliente1 = cliente1.obterHistoricoTodosVeiculos();
        System.out.println("Histórico do Cliente 1:");
        for (Registro registro : historicoCliente1) {
            System.out.println("Data: " + registro.getData() + ", Descrição: " + registro.getDescricao());
        }

        double arrecadacaoTotal = estacionamento.totalArrecadado();
        System.out.println("Arrecadação total do estacionamento: R$" + arrecadacaoTotal);
    }
}
