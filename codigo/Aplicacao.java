import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Classe que representa a aplicação de gerenciamento de um estacionamento.
 */
public class Aplicacao {
    private static Scanner scanner;
    private static Estacionamento estacionamento;
    private static String nomeArquivo;
    private static double totalServicos = 0;
    private static double totalMesServicos = 0;
    private static double totalValorMedio = 0;
    private static double arrecadaTotal = 0;

    public static void main(String[] args) throws IOException, ClassNotFoundException, VagaIndisoponivelException, ServicoNaoExecutadoException {
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
                estacionamento = new Estacionamento("Estacionamento 1", 5, 10);
                nomeArquivo = "dat/estacionamento1.dat";
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

    public static void menu() throws IOException, VagaIndisoponivelException, ServicoNaoExecutadoException {
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
        System.out.println("\t10. Visualizar arrecadação total de cada estacionamento");
        System.out.println("\t11. Visualizar média utilizada por mensalistas no mês");
        System.out.println("\t12. Visualizar média utilizada por horistas no mês");
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
            case 10:
                ordenarEstacionamentos();
                break;
            case 11:
                exibirMediaUtilizacaoMensalistasNoMesCorrente();
                break;

            case 12:
                exibirArrecadacaoMediaHoristasNoMesCorrente();
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
    
        System.out.println("Escolha o tipo de cliente (1. Horista, 2. Mensalista, 3. Turnista): ");
        int escolhaTipoCliente = Integer.parseInt(scanner.nextLine());
    
        TipoDePlano tipoCliente = null;
        switch (escolhaTipoCliente) {
            case 1:
                tipoCliente = TipoDePlano.HORISTA;
                break;
            case 2:
                tipoCliente = TipoDePlano.MENSALISTA;
                break;
            case 3:
                tipoCliente = TipoDePlano.TURNISTA;
                break;
            default:
                System.out.println("Opção inválida. Cliente será cadastrado como Horista por padrão.");
                tipoCliente = TipoDePlano.HORISTA;
                break;
        }
    
        Cliente cliente;
        if (tipoCliente == TipoDePlano.TURNISTA) {
            System.out.println("Escolha o turno (1. Manhã, 2. Tarde, 3. Noite): ");
            int escolhaTurno = Integer.parseInt(scanner.nextLine());
            Turno turno = null;
            switch (escolhaTurno) {
                case 1:
                    turno = Turno.MANHA;
                    break;
                case 2:
                    turno = Turno.TARDE;
                    break;
                case 3:
                    turno = Turno.NOITE;
                    break;
                default:
                    System.out.println("Opção inválida. Turno padrão (Manhã) será atribuído.");
                    turno = Turno.MANHA;
                    break;
            }
            cliente = new Cliente(nomeCliente, idCliente, tipoCliente, turno);
        } else {
            cliente = new Cliente(nomeCliente, idCliente, tipoCliente, arrecadaTotal);
        }
    
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
    
            try {
                cliente.addVeiculo(veiculo, null);
                System.out.println("Veículo adicionado com sucesso.");
            } catch (Exception e) {
                System.out.println("Erro ao adicionar veículo: " + e.getMessage());
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
    
    
    public static void estacionarVeiculo() throws VagaIndisoponivelException {
        System.out.println("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
    
        try {
            estacionamento.estacionar(placa);
            System.out.println("Veículo estacionado com sucesso.");
        } catch (LotadoException e) {
            System.out.println("O estacionamento está lotado");
        }
    }
    
    public static void sairDaVaga() throws ServicoNaoExecutadoException {
        System.out.println("Digite a placa do veículo: ");
        String placa = scanner.nextLine();
    
        double valorPago = estacionamento.sair(placa);
    
        if (valorPago > 0) {
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
                double valorServico = servicoEscolhido.getValor();
                double tempoServico = servicoEscolhido.getTempo();
    
                System.out.println("Serviço aplicado com sucesso.");
                valorPago += valorServico;
    
                System.out.println("Valor total pago (incluindo serviço): R$" + valorPago);
                System.out.println("Tempo total do serviço: " + tempoServico + " minutos");
                totalServicos += valorServico;
                totalMesServicos += valorServico;
                totalValorMedio += valorServico;
                arrecadaTotal += valorServico;
            } else {
                System.out.println("Operação cancelada. Nenhum serviço selecionado.");
                System.out.println("Valor pago: R$" + valorPago);
            }
        }
    }
    
    
    public static void consultarTotal() {
        double total = estacionamento.totalArrecadado();
        System.out.println("O total arrecadado pelo estacionamento foi de R$" + (total + totalServicos));
    }

    public static void consultarTotalMes() {
        System.out.println("Digite o mês: ");
        int mes = Integer.parseInt(scanner.nextLine());
        double totalMes = estacionamento.arrecadacaoNoMes(mes);
        System.out.println("A arrecadação do mês " + mes + " foi de R$" + (totalMes + totalMesServicos));
    }

    public static void consultarValorMedio() {
        double valorMedio = estacionamento.valorMedioPorUso();
        System.out.println("O valor médio de uso no estacionamento foi de R$" + (valorMedio + totalValorMedio));
    }

    public static void mostrarTop5Clientes() {
        System.out.println(estacionamento.top5Clientes(10));
    }

    public static void mostrarHistoricoCliente() {
        System.out.println("Digite o ID do cliente: ");
        String idCliente = scanner.nextLine();

        Cliente cliente = estacionamento.busca(idCliente);

        if (cliente != null) {
            String historicoCliente = cliente.historico();
            System.out.println("Histórico do Cliente:\n" + historicoCliente);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void calcularArrecadacaoTotalOrdenar() {
        double arrecadacaoTotal = estacionamento.calcularArrecadacaoTotal();
        System.out.println("A arrecadação total do estacionamento foi de R$" + (arrecadacaoTotal + arrecadaTotal));
    }

    public static void ordenarEstacionamentos() {
        List<Estacionamento> estacionamentos = Arrays.asList(estacionamento);
        
        Estacionamento.ordenarEstacionamentos(estacionamentos);
        
     for (Estacionamento est : estacionamentos) {
            System.out.println("Nome: " + est.getNome() + ", Arrecadação Total: R$" + est.calcularArrecadacaoTotal());
        }
    }
    private static void exibirMediaUtilizacaoMensalistasNoMesCorrente() {
        double mediaMensalista = estacionamento.mediaUtilizacaoMensalistasNoMesCorrente();
        System.out.println("A média de utilização dos clientes mensalistas no mês corrente é: " + mediaMensalista);
    }

    private static void exibirArrecadacaoMediaHoristasNoMesCorrente() {
        double mediaHorista = estacionamento.arrecadacaoMediaHoristasNoMesCorrente();
        System.out.println("A média de arrecadação dos clientes horistas no mês corrente é: " + mediaHorista);
    }
}
