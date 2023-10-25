import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEstacionamento {

    private Estacionamento estacionamento;
    private Cliente c;
    private Veiculo v;

    @BeforeEach
    public void setUp() {
        estacionamento = new Estacionamento("estacionamento vermes", 10, 10);
        c = new Cliente("gabriel", "333");
        v = new Veiculo("ADD123");
        estacionamento.addCliente(c);
    }

    @Test
    public void testAddCliente() {
        Cliente cliente = new Cliente("lucas", "123");
        assertTrue(estacionamento.addCliente(cliente));
    }

    @Test
    public void testEstacionar() throws LotadoException {
        c.addVeiculo(v);
        estacionamento.estacionar("ADD123");
        assertEquals(1, v.totalDeUsos());;         
        
    }

    @Test
    public void testSair() throws LotadoException {
        estacionamento.addCliente(new Cliente("miguel", "444"));
        estacionamento.estacionar("ABC123");
        double valorPago = estacionamento.sair("ABC123");
        assertTrue(valorPago >= 0.0);
    }

    @Test
    public void testTotalArrecadado() throws LotadoException {
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
    public void testArrecadacaoNoMes() throws LotadoException {
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
    public void testValorMedioPorUso() throws LotadoException {
    
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
    public void testTop5Clientes() throws LotadoException {
        Cliente cliente1 = new Cliente("maisa", "3456");
        estacionamento.addCliente(cliente1);
        estacionamento.estacionar("POO123");
        estacionamento.sair("POO123");
    
        Cliente cliente2 = new Cliente("Miguel", "2323");
        estacionamento.addCliente(cliente2);
        estacionamento.estacionar("DEF456");
        estacionamento.sair("DEF456");
        
        Cliente cliente3 = new Cliente("Joao", "1234");
    estacionamento.addCliente(cliente3);
    estacionamento.estacionar("ABC789");
    estacionamento.sair("ABC789");

    Cliente cliente4 = new Cliente("Maria", "5678");
    estacionamento.addCliente(cliente4);
    estacionamento.estacionar("XYZ012");
    estacionamento.sair("XYZ012");
    
        String topClientes = estacionamento.top5Clientes(12); 
    
        String clientestop = "[gabriel, maisa, Miguel, Joao, Maria]";
        assertEquals(clientestop, topClientes);}
    
}
