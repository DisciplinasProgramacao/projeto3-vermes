import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestEstacionamento {

    private Estacionamento estacionamento;
    private Cliente cliente1;
    private Veiculo veiculo1;
    private Cliente cliente2;
    private Veiculo veiculo2;

    @BeforeEach
    public void setUp() {
        estacionamento = new Estacionamento("estacionamento vermes", 10, 10);
        cliente1 = new Cliente("gabriel", "333", null, 0);
        veiculo1 = new Veiculo("ADD123");
        cliente1.addVeiculo(veiculo1, null);
    
        estacionamento.addCliente(cliente1);
        cliente2 = new Cliente("lucas", "123", null, 0);
        veiculo2 = new Veiculo("XYZ789");
        cliente2.addVeiculo(veiculo2, null);
        estacionamento.addCliente(cliente2);
    }


    @Test
    public void testAddClientwe() {
        Cliente cliente = new Cliente("lucas", "123", null, 0);
        assertTrue(estacionamento.addCliente(cliente));
        assertFalse(estacionamento.addCliente(cliente1));
    }

    @Test
    public void testEstacionar() throws LotadoException, VagaIndisoponivelException {
        cliente1.addVeiculo(veiculo1, null);
        estacionamento.estacionar("ADD123");
        assertEquals(1, veiculo1.totalDeUsos());
    }

    @Test
    public void testSair() throws LotadoException, VagaIndisoponivelException, ServicoNaoExecutadoException {
        estacionamento.estacionar("XYZ789");
        double valorPago = estacionamento.sair("XYZ789");
        assertTrue(valorPago >= 0.0);
    }

    @Test
    public void testTotalArrecadado() throws LotadoException, VagaIndisoponivelException {
        estacionamento.estacionar("ADD123");
        estacionamento.estacionar("XYZ789");

        double totalArrecadado = estacionamento.totalArrecadado();

        assertEquals(8.0, totalArrecadado);
    }

    @Test
    public void testArrecadadoNoMes() throws ServicoNaoExecutadoException, LotadoException, VagaIndisoponivelException {
        veiculo1.estacionar(new Vaga(1, 1), cliente1);
        veiculo1.sair(cliente1);  
        veiculo2.estacionar(new Vaga(2, 2), cliente1);
        veiculo2.sair(cliente2);  
        veiculo2.estacionar(new Vaga(3, 3), cliente1);
        veiculo2.sair(cliente2);  
    
        int mes = 1;
        double arrecadacaoEsperada = veiculo1.arrecadadoNoMes(mes) + veiculo2.arrecadadoNoMes(mes);
        double arrecadacaoNoMes = estacionamento.arrecadacaoNoMes(mes);
    
        assertEquals(arrecadacaoEsperada, arrecadacaoNoMes);
    }
    

    @Test
    public void testValorMedioPorUso() {
        try {
            estacionamento.estacionar("ADD123");
            estacionamento.sair("ADD123");
            estacionamento.estacionar("XYZ789");
            estacionamento.sair("XYZ789");
        } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
            e.printStackTrace();
        }

        double totalArrecadado = estacionamento.totalArrecadado();
        int totalUsos = cliente1.totalDeUsos() + cliente2.totalDeUsos();
        double valorMedioEsperado = totalArrecadado / totalUsos;

        double valorMedioCalculado = estacionamento.valorMedioPorUso();
        assertEquals(valorMedioEsperado, valorMedioCalculado); 
    }

    @Test
    public void testTop5Clientes() throws LotadoException, ServicoNaoExecutadoException, VagaIndisoponivelException {
        Cliente cliente1 = new Cliente("maisa", "3456", null, 0);
        estacionamento.addCliente(cliente1);
        estacionamento.estacionar("POO123");
        estacionamento.sair("POO123");
    
        Cliente cliente2 = new Cliente("Miguel", "2323", null, 0);
        estacionamento.addCliente(cliente2);
        estacionamento.estacionar("DEF456");
        estacionamento.sair("DEF456");
        
        Cliente cliente3 = new Cliente("Joao", "1234", null, 0);
    estacionamento.addCliente(cliente3);
    estacionamento.estacionar("ABC789");
    estacionamento.sair("ABC789");

    
        String topClientes = estacionamento.top5Clientes(12); 
    
        String clientestop = "[gabriel, lucas, maisa, Miguel, Joao]";
        assertEquals(clientestop, topClientes);}
    
}

