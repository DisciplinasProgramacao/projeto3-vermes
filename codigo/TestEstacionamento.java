import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEstacionamento {

    private Estacionamento estacionamento;

    @BeforeEach
    public void setUp() {
        estacionamento = new Estacionamento("estacionamento vermes", 10, 10);
    }

    @Test
    public void testAddCliente() {
        Cliente cliente = new Cliente("lucas", "123");
        estacionamento.addCliente(cliente);
        assertTrue(estacionamento.cliente.contains(cliente));
    }

    @Test
    public void testEstacionar() {
        estacionamento.addCliente(new Cliente("gabriel", "333"));
        estacionamento.estacionar("ADD123");
    
        boolean vagaEstacionada = false;
        for (Vaga vaga : estacionamento.vagas) {
            if (!vaga.disponivel()) {
                vagaEstacionada = true;
                break;
            }
        }
        assertTrue(vagaEstacionada);
    }

    @Test
    public void testSair() {
        estacionamento.addCliente(new Cliente("miguel", "444"));
        estacionamento.estacionar("ABC123");
        double valorPago = estacionamento.sair("ABC123");
        assertTrue(valorPago >= 0.0);
    }

    @Test
    public void testTotalArrecadado() {
        Cliente cliente1 = new Cliente("maisa", "676");
        estacionamento.addCliente(cliente1);
        estacionamento.estacionar("GGG455");
        estacionamento.sair("GGG455");

        Cliente cliente2 = new Cliente("Slim", "989");
        estacionamento.addCliente(cliente2);
        estacionamento.estacionar("DEF456");
        estacionamento.sair("DEF456");

        double totalArrecadado = estacionamento.totalArrecadado();
        assertEquals(0.0, totalArrecadado); 
    }

    @Test
    public void testArrecadacaoNoMes() {
        Cliente cliente1 = new Cliente("Faria", "323");
        estacionamento.addCliente(cliente1);
        estacionamento.estacionar("GGG789");
        estacionamento.sair("GGG789");

        Cliente cliente2 = new Cliente("Ferreira", "976");
        estacionamento.addCliente(cliente2);
        estacionamento.estacionar("HJK456");
        estacionamento.sair("HJK456");

        double arrecadacaoNoMes = estacionamento.arrecadacaoNoMes(10); 
        assertEquals(0.0, arrecadacaoNoMes); 
    }

    @Test
    public void testValorMedioPorUso() {
    
        Cliente cliente1 = new Cliente("lucas", "874");
        estacionamento.addCliente(cliente1);
        estacionamento.estacionar("ABC123");
        estacionamento.sair("ABC123");

        Cliente cliente2 = new Cliente("joao", "111");
        estacionamento.addCliente(cliente2);
        estacionamento.estacionar("GHJ789");
        estacionamento.sair("GHJ789");

        double valorMedioPorUso = estacionamento.valorMedioPorUso();
        assertEquals(0.0, valorMedioPorUso);
    }

    @Test
    public void testTop5Clientes() {

        Cliente cliente1 = new Cliente("maisa", "3456");
        estacionamento.addCliente(cliente1);
        estacionamento.estacionar("POO123");
        estacionamento.sair("POO123");

        Cliente cliente2 = new Cliente("Miguel", "2323");
        estacionamento.addCliente(cliente2);
        estacionamento.estacionar("DEF456");
        estacionamento.sair("DEF456");

        String topClientes = estacionamento.top5Clientes(10); 
       
        String clientestop = "1. Cliente: Cliente 1, Arrecadação no mês: R$0.0\n2. Cliente: Cliente 2, Arrecadação no mês: R$0.0\n";
        assertEquals(clientestop, topClientes);
    }
}

