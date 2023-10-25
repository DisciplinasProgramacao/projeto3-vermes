import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * A classe Estacionamento representa um estacionamento que gerencia o estacionamento de veículos para vários clientes.
 */
public class Estacionamento implements Serializable {

    private String nome;
    private LinkedList<Cliente> clientes;
    private LinkedList<Vaga> vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private LinkedList<UsoDeVaga> usos;

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
        this.usos = new LinkedList<>();
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
            cliente.addVeiculo(veiculo);
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
     * @throws EstacionamentoLotadoException Se o estacionamento estiver lotado.
     */
    public void estacionar(String placa) throws EstacionamentoLotadoException {
        Vaga vagaDisponivel = null;
        Veiculo veiculoEncontrado = null;
        boolean estacionado = false;
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
     */
    public double sair(String placa) {
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

    // Outros métodos e funcionalidades da classe...

    public double totalArrecadado() {
        double total = 0.0;
        for (UsoDeVaga uso : usos) {
            total += uso.valorPago();
        }
        return total;
    }

    public double arrecadacaoNoMes(int mes) {
        double totalMes = 0;
        for (Cliente cliente : clientes) {
            if (cliente != null) {
                totalMes += cliente.arrecadadoNoMes(mes);
            }
        }
        return totalMes;
    }

    public double valorMedioPorUso() {
        if (usos.isEmpty()) {
            return 0.0;
        }
        return totalArrecadado() / usos.size();
    }public String top5Clientes(int mes) {
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
}
