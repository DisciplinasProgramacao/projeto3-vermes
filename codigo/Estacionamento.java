import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

/**
 * A classe Estacionamento representa um estacionamento que gerencia o estacionamento de veículos para vários clientes.
 */
public class Estacionamento implements Serializable {

    private String nome;
    private LinkedList<Cliente> clientes;
    private LinkedList<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;
  

    /**
     * Constrói um objeto Estacionamento com o nome especificado, número de fileiras no estacionamento e número de vagas por fileira.
     *
     * @param nome O nome do estacionamento.
     * @param fileiras O número de fileiras no estacionamento.
     * @param vagasPorFila O número de vagas por fileira.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new LinkedList<>();
        gerarVagas();
    }

    /**
     * Obtém o nome do estacionamento.
     *
     * @return O nome do estacionamento.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Adiciona um veículo ao cliente identificado pelo ID.
     *
     * @param veiculo O veículo a ser adicionado.
     * @param idCli O ID do cliente.
     */
    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente quem = new Cliente("idCli", idCli);
        Cliente cliente = busca(quem);
        if (cliente != null) {
            cliente.addVeiculo(veiculo, null);
        }
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente O cliente a ser adicionado.
     * @return true se o cliente foi adicionado com sucesso, false se o cliente já existir.
     */
    public boolean addCliente(Cliente cliente) {
        if ((busca(cliente)) == null) {
            clientes.add(cliente);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Busca um cliente no estacionamento.
     *
     * @param quem O cliente a ser buscado.
     * @return O cliente se encontrado, ou null se não encontrado.
     */
    Cliente busca(Cliente quem) {
        for (Cliente cliente : clientes) {
            if (cliente != null && cliente.equals(quem)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Gera as vagas no estacionamento com base no número de fileiras e vagas por fileira.
     */
    private void gerarVagas() {
        vagas = new LinkedList<>();
        for (int i = 0; i < quantFileiras; i++) {
            for (int j = 0; j < vagasPorFileira; j++) {
                int numeroVaga = i * vagasPorFileira + j + 1;
                vagas.add(new Vaga(i, numeroVaga));
            }
        }
    }

    /**
     * Estaciona um veículo com a placa especificada.
     *
     * @param placa A placa do veículo a ser estacionado.
     * @throws VagaIndisoponivelException
     * @throws EstacionamentoLotadoException Se o estacionamento estiver lotado.
     */
    public void estacionar(String placa) throws LotadoException, VagaIndisoponivelException {
        Vaga vagaDisponivel = null;
        Veiculo veiculoEncontrado = null;
        for (Vaga vaga : vagas) {
            if (vaga.disponivel()) {
                vagaDisponivel = vaga;
                break;
            }
        }

        if (vagaDisponivel != null) {
            for (Cliente cliente : clientes) {
                veiculoEncontrado = cliente.possuiVeiculo(placa);
                if (veiculoEncontrado != null) {
                    veiculoEncontrado.estacionar(vagaDisponivel);
                    break;
                }
            }
        }
    }

    /**
     * Retira um veículo com a placa especificada do estacionamento e retorna o valor a ser pago.
     *
     * @param placa A placa do veículo a ser retirado.
     * @return O valor a ser pago pelo uso da vaga.
     * @throws ServicoNaoExecutadoException
     */
    public double sair(String placa) throws ServicoNaoExecutadoException {
        for (Cliente cliente : clientes) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                double valorPago = veiculo.sair();
                System.out.println("Valor a ser pago: " + valorPago);
                return valorPago;
            }
        }

        System.out.println("Veículo não encontrado.");
        return 0.0;
    }

/**
     * Calcula o total arrecadado pelo estacionamento.
     *
     * @return O valor total arrecadado.
     */

     public double totalArrecadado() {
        double total = 0.0;
        for (Cliente cliente : clientes) {
            total += cliente.arrecadadoTotal();
        }
        return total;
    }


/**
     * Calcula a arrecadação no mês especificado.
     *
     * @param mes O número do mês (1 a 12).
     * @return O valor arrecadado no mês.
     */
    public double arrecadacaoNoMes(int mes) {
        double totalMes = 0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                totalMes += cliente.arrecadadoNoMes(mes);
            }
        }
        return totalMes;
    }


    /**
     * Calcula o valor médio por uso de vaga.
     *
     * @return O valor médio por uso de vaga.
     */
    public double valorMedioPorUso() {
        double totalArrecadado = 0.0;
        int totalUsos = 0;

        for (Cliente cliente : clientes) {
            totalArrecadado += cliente.arrecadadoTotal();
            totalUsos += cliente.totalDeUsos();
        }

        if (totalUsos == 0) {
            return 0.0;
        }

        return totalArrecadado / totalUsos;
    }

 /**
     * Retorna os nomes dos top 5 clientes com maior arrecadação no mês especificado.
     *
     * @param mes O número do mês (1 a 12).
     * @return Uma string contendo os nomes dos clientes.
     */

public String top5Clientes(int mes) {
        Cliente[] topClientes = new Cliente[5];

        for (Cliente c : clientes) {
            if (c != null) {
                double valorDoCliente = c.arrecadadoNoMes(mes);

                for (int i = 0; i < 5; i++) {
                    if (topClientes[i] == null || valorDoCliente > topClientes[i].arrecadadoNoMes(mes)) {
                        for (int j = 4; j > i; j--) {
                            topClientes[j] = topClientes[j - 1];
                        }
                        topClientes[i] = c;
                        break;
                    }
                }
            }
        }

        // Agora topClientes contém os 5 principais clientes
        String[] nomesTopClientes = new String[5];
        for (int i = 0; i < 5; i++) {
            if (topClientes[i] != null) {
                nomesTopClientes[i] = topClientes[i].getNome();
            }
        }

        return Arrays.toString(nomesTopClientes);
    }

    /**
     * Retorna a lista de clientes.
     *
     * @return A lista de clientes.
     */
    public Cliente[] getClientes() {
        return clientes.toArray(new Cliente[0]);
    }

    /**
     * Busca um cliente pelo ID.
     *
     * @param idCliente O ID do cliente a ser buscado.
     * @return O cliente encontrado, ou null se não encontrado.
     */
    public Cliente busca(String idCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Busca um veículo pela placa.
     *
     * @param placa A placa do veículo a ser buscado.
     * @return O veículo encontrado, ou null se não encontrado.
     */
    public Veiculo buscaVeiculo(String placa) {
        for (Cliente cliente : clientes) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                return veiculo;
            }
        }
        return null;
    }
   public double mediaUtilizacaoMensalistasNoMesCorrente() {
        int totalClientesMensalistas = 0;
        int totalUtilizacoesMensalistas = 0;
    
        LocalDate dataAtual = LocalDate.now();
        int mesCorrente = dataAtual.getMonthValue();
    
        for (Cliente cliente : clientes) {
            if (isMensalista(cliente)) {
                int utilizacoesNoMes = cliente.obterNumeroUtilizacoesNoMes(mesCorrente);
                totalClientesMensalistas++;
                totalUtilizacoesMensalistas += utilizacoesNoMes;
            }
        }
    
        if (totalClientesMensalistas > 0) {
            return (double) totalUtilizacoesMensalistas / totalClientesMensalistas;
        } else {
            return 0.0;
        }
    }
    
    private boolean isMensalista(Cliente cliente) {
        return cliente != null && cliente.getClass().equals(Mensalista.class);
    }

    public double arrecadacaoMediaHoristasNoMesCorrente() {
        int totalClientesHoristas = 0;
        double totalArrecadacaoHoristas = 0.0;
    
        LocalDate dataAtual = LocalDate.now();
        int mesCorrente = dataAtual.getMonthValue();
    
        for (Cliente cliente : clientes) {
            if (isHorista(cliente)) {
                double arrecadacaoNoMes = cliente.obterNumeroUtilizacoesNoMes(mesCorrente);
                totalClientesHoristas++;
                totalArrecadacaoHoristas += arrecadacaoNoMes;
            }
        }
    
        if (totalClientesHoristas > 0) {
            return totalArrecadacaoHoristas / totalClientesHoristas;
        } else {
            return 0.0;
        }
    }
    
    private boolean isHorista(Cliente cliente) {
        return cliente != null && cliente.getClass().equals(Horista.class);
    }
public double calcularArrecadacaoTotal() {
        return clientes.stream()
                .mapToDouble(Cliente::arrecadadoTotal)
                .sum();
    }
       public static void ordenarEstacionamentos(List<Estacionamento> estacionamentos) {
        Collections.sort(estacionamentos, Comparator.comparingDouble(Estacionamento::calcularArrecadacaoTotal).reversed());
    }
}
