import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Classe de teste para a classe Cliente.
 */
public class TestCliente {

    private Cliente cliente;
    private Veiculo veiculo;

    @Before
    public void setUp() {
        cliente = new Cliente("Maisa", "ID123", null, 0);
        veiculo = new Veiculo("ABC123");
        cliente.addVeiculo(veiculo, null);
    }

    /**
     * Testa o método `addVeiculo` da classe Cliente.
     */
    @Test
    public void testAddVeiculo() {
        Veiculo novoVeiculo = new Veiculo("XYZ789");
        cliente.addVeiculo(novoVeiculo, null);
        assertEquals(novoVeiculo, cliente.possuiVeiculo("XYZ789"));
    }

    /**
     * Testa o método `possuiVeiculo` da classe Cliente.
     */
    @Test
    public void testPossuiVeiculo() {
        assertEquals(veiculo, cliente.possuiVeiculo("ABC123"));
    }

    /**
     * Testa o método `totalDeUsos` da classe Cliente.
     */
    @Test
    public void testTotalDeUsos() {
        int totalUsosVeiculo = veiculo.totalDeUsos();
        assertEquals(totalUsosVeiculo, cliente.totalDeUsos());
    }

    /**
     * Testa o método `arrecadadoPorVeiculo` da classe Cliente.
     */
    @Test
    public void testArrecadadoPorVeiculo() {
        double arrecadadoVeiculo = veiculo.totalArrecadado();
        assertEquals(arrecadadoVeiculo, cliente.arrecadadoPorVeiculo("ABC123"), 0.01); 
    }

    /**
     * Testa o método `arrecadadoTotal` da classe Cliente.
     */
    @Test
    public void testArrecadadoTotal() {
        double arrecadadoVeiculo = veiculo.totalArrecadado();
        assertEquals(arrecadadoVeiculo, cliente.arrecadadoTotal(), 0.01); 
    }
}
