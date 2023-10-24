import java.io.IOException;
import java.util.Scanner;
import java.util.List; 

public class Aplicacao {
    private static Scanner SCANNER;
    private static Estacionamento estacionamento;

    public static void main(String[] args) {
        SCANNER = new Scanner(System.in);
        estacionamento = new Estacionamento("Estacionamento da Maisa", 5, 10);
        menu();
    }

    public static void menu() throws IOException {
        System.out.println("Escolha uma das opções: ");
        System.out.println("\t0. Salvar e sair");
        System.out.println("\t1. Cadastrar cliente");
        System.out.println("\t2. Adicionar veículo");
        System.out.println("\t3. Estacionar");
        System.out.println("\t4. Sair");
        System.out.println("\t5. Consultar total arrecadado pelo estacionamento");
        System.out.println("\t6. Consultar total arrecadado no mês");
        System.out.println("\t7. Consultar valor médio de utilização do estacionamento");
        System.out.println("\t8. Consultar top 5 clientes");
        System.out.println("\t9. Mostrar histórico de cliente");

        int opcao;

        try {
            opcao = Integer.parseInt(SCANNER.nextLine());
        } catch (NumberFormatException exception) {
            opcao = -1;
        }
        boolean saindo = false;

        switch (opcao) {
            case 0 -> cadastrarCliente();
            case 1 -> adicionarVeiculo();
            case 2 -> estacionarVeiculo();
            case 3 -> sairDaVaga();
            case 4 -> consultarTotal();
            case 5 -> consultarTotalMes();
            case 6 -> consultarValorMedio();
            case 7 -> mostrarTop5Clientes();
            case 8 -> mostrarHistoricoCliente();
            default -> System.out.println("A opção informada é inválida.");
        }

        if (!saindo) {

            System.out.println();
            System.out.println("Pressione ENTER para voltar ao menu...");

            try {
                System.in.read();
            } catch (IOException ignored) {
            }

            menu();
        }
    }



    public static void cadastrarCliente() {
        System.out.println("Digite o ID do cliente: ");
        String idCliente = SCANNER.nextLine();

        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = SCANNER.nextLine();

        Cliente cliente = new Cliente(nomeCliente, idCliente);
        estacionamento.addCliente(cliente);
    }

    public static void adicionarVeiculo() {
        System.out.println("Digite o ID do cliente: ");
        String idCliente = SCANNER.nextLine();

        System.out.println("Digite a placa do veículo: ");
        String placa = SCANNER.nextLine();

        Cliente cliente = estacionamento.buscarCliente(idCliente);

        if (cliente != null) {
            Veiculo veiculo = new Veiculo(placa);
            cliente.addVeiculo(veiculo);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void estacionarVeiculo() {
        System.out.println("Digite a placa do veículo: ");
        String placa = SCANNER.nextLine();

        Veiculo veiculo = estacionamento.buscarVeiculo(placa);

        if (veiculo != null) {
            Vaga vaga = estacionamento.getVagaDisponivel();
            if (vaga != null) {
                veiculo.estacionar(vaga);
                System.out.println("Veículo estacionado com sucesso.");
            } else {
                System.out.println("Não há vagas disponíveis.");
            }
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void sairDaVaga() {
        System.out.println("Digite a placa do veículo: ");
        String placa = SCANNER.nextLine();

        Veiculo veiculo = estacionamento.buscarVeiculo(placa);

        if (veiculo != null) {
            double valorPago = veiculo.sair();
            System.out.println("Valor pago: R$" + valorPago);
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public static void consultarTotal() {
        double total = estacionamento.totalArrecadado();
        System.out.println("O total arrecadado pelo estacionamento foi de R$" + total);
    }

    public static void consultarTotalMes() {
        System.out.println("Digite o mês: ");
        int mes = Integer.parseInt(SCANNER.nextLine());
        double totalMes = estacionamento.arrecadacaoNoMes(mes);
        System.out.println("A arrecadação do mês " + mes + " foi de R$" + totalMes);
    }

   public static void consultarValorMedio() {
    double valorMedio = estacionamento.valorMedioPorUso();
    System.out.println("O valor médio de uso no estacionamento foi de R$" + valorMedio);
}
}
