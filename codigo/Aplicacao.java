import java.util.Scanner;
import java.io.IOException;
import java.util.List;

/**
 * Classe que representa a aplicação de gerenciamento de um estacionamento.
 */
public class Aplicacao {
    private static Scanner scanner;
    private static Estacionamento estacionamento;
    private static String nomeArquivo;

    public static void main(String[] args) throws IOException, ClassNotFoundException, VagaIndisoponivelException {
        scanner = new Scanner(System.in);

        System.out.println("Escolha o estacionamento (1, 2 ou 3): ");
        int escolhaEstacionamento = Integer.parseInt(scanner.nextLine());

        switch (escolhaEstacionamento) {
            case 1:
                estacionamento = new Estacionamento("Estacionamento 1", 5, 10);
                nomeArquivo = "dat/estacionamento1.dat";
                break;
            case 2:
                estacionamento = new Estacionamento("Estacionamento 2", 5, 10);
                nomeArquivo = "dat/estacionamento2.dat";
                break;
            case 3:
            
                estacionamento = new Estacionamento("Estacionamento 3", 5, 10);
                nomeArquivo = "dat/estacionamento3.dat";
                break;
            default:
                System.out.println("Opção inválida. Usando Estacionamento 1 por padrão.");
                break;
        }

        try {
            Estacionamento est = Serializacao.carregarEstacionamento(nomeArquivo);
            if (est != null) {
                estacionamento = est;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar o estacionamento: " + e.getMessage());
        }

        menu();
    }

    public static void menu() throws IOException, VagaIndisoponivelException {
        System.out.println("Escolha uma das opções: ");
        System.out.println("\t1. Cadastrar cliente");
        System.out.println("\t2. Adicionar veículo");
        System.out.println("\t3. Estacionar");
        System.out.println("\t4. Sair");
        System.out.println("\t5. Consultar total arrecadado pelo estacionamento");
        System.out.println("\t6. Consultar total arrecadado no mês");
        System.out.println("\t7. Consultar valor médio de utilização do estacionamento");
        System.out.println("\t8. Consultar top 5 clientes");
        System.out.println("\t9. Mostrar histórico de cliente");
        System.out.println("\t0. Sair do programa");

        int opcao;

        try {
            opcao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            opcao = -1;
        }

        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                adicionarVeiculo();
                break;
            case 3:
                estacionarVeiculo();
                break;
            case 4:
                sairDaVaga();
                break;
            case 5:
                consultarTotal();
                break;
            case 6:
                consultarTotalMes();
                break;
            case 7:
                consultarValorMedio();
                break;
            case 8:
                mostrarTop5Clientes();
                break;
            case 9:
                mostrarHistoricoCliente();
                break;
            case 0:
            try {
                Serializacao.salvarEstacionamento(estacionamento, nomeArquivo);
            } catch (IOException e) {
                System.out.println("Erro ao salvar estacionamento: " + e.getMessage());
            }
            System.out.println("Saindo do programa.");
            System.exit(0);
            default:
                System.out.println("A opção informada é inválida.");
        }

        menu(); // Chama o menu novamente após a seleção da opção.
    }

    public static void cadastrarCliente() {
        System.out.println("Digite o ID do cliente: ");
        String idCliente = scanner.nextLine();

        System.out.println("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        Cliente cliente = new Cliente(nomeCliente, idCliente);
        estacionamento.addCliente(cliente);
    }

    public static void adicionarVeiculo() {
        System.out.println("Digite o ID do cliente: ");
        String idCliente = scanner.nextLine();
    
        Cliente cliente = estacionamento.busca(idCliente);
    
        if (cliente != null) {
            System.out.println("Digite a placa do veículo: ");
            String placa = scanner.nextLine();
            Veiculo veiculo = new Veiculo(placa);
    
            System.out.println("Escolha um dos serviços:");
            System.out.println("1. Manobrista - R$5.0");
            System.out.println("2. Lavagem - R$20.0");
            System.out.println("3. Polimento - R$45.0");
    
            int opcaoServico = Integer.parseInt(scanner.nextLine());
    
            Servico servicoEscolhido = null;
            switch (opcaoServico) {
                case 1:
                    servicoEscolhido = Servico.MANOBRISTA;
                    break;
                case 2:
                    servicoEscolhido = Servico.LAVAGEM;
                    break;
                case 3:
                    servicoEscolhido = Servico.POLIMENTO;
                    break;
                default:
                    System.out.println("Opção inválida. Nenhum serviço selecionado.");
                    break;
            }
    
            if (servicoEscolhido != null) {
                cliente.addVeiculo(veiculo, servicoEscolhido);  // Adicione o serviço escolhido ao veículo
                System.out.println("Veículo adicionado com sucesso.");
            } else {
                System.out.println("Operação cancelada. Nenhum serviço selecionado.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    

    public static void estacionarVeiculo() throws VagaIndisoponivelException {
        System.out.println("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
    
        Veiculo veiculo = estacionamento.buscaVeiculo(placa);
    
        if (veiculo != null) {
            try {
                veiculo.estacionar(null);
                System.out.println("Veículo estacionado com sucesso.");
            } catch (LotadoException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
    

    public static void sairDaVaga() {
        System.out.println("Digite a placa do veículo: ");
        String placa = scanner.nextLine();

        double valorPago = estacionamento.sair(placa);
        if (valorPago > 0) {
            System.out.println("Valor pago: R$" + valorPago);
        }
    }

    public static void consultarTotal() {
        double total = estacionamento.totalArrecadado();
        System.out.println("O total arrecadado pelo estacionamento foi de R$" + total);
    }

    public static void consultarTotalMes() {
        System.out.println("Digite o mês: ");
        int mes = Integer.parseInt(scanner.nextLine());
        double totalMes = estacionamento.arrecadacaoNoMes(mes);
        System.out.println("A arrecadação do mês " + mes + " foi de R$" + totalMes);
    }

    public static void consultarValorMedio() {
        double valorMedio = estacionamento.valorMedioPorUso();
        System.out.println("O valor médio de uso no estacionamento foi de R$" + valorMedio);
    }

    public static void mostrarTop5Clientes() {
        System.out.println(estacionamento.top5Clientes(10));
    }

    public static void mostrarHistoricoCliente() {
        System.out.println("Digite o ID do cliente: ");
        String idCliente = scanner.nextLine();
        Cliente cliente = estacionamento.busca(idCliente);
        if (cliente != null) {
            List<Registro> historico = cliente.getHistorico();
            for (Registro registro : historico) {
                System.out.println("Data: " + registro.getData() + ", Descrição: " + registro.getDescricao() + ", Quilometragem: " + registro.getQuilometragem());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
