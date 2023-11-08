import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TestVeiculo {

    private Veiculo veiculo;
    private Vaga vaga;

    @Before
    public void setUp() {
        veiculo = new Veiculo("ABC1234");
        
    }

    @Test
    public void testEstacionar() throws LotadoException, VagaIndisoponivelException {
        veiculo.estacionar(vaga);
        assertEquals(1, veiculo.totalDeUsos());
    }

    @Test
    public void testSair() throws LotadoException, VagaIndisoponivelException, ServicoNaoExecutadoException {
        veiculo.estacionar(vaga);
        double valorPago = veiculo.sair();
        assertTrue(valorPago >= 0);
    }

    @Test
    public void testTotalArrecadado() throws LotadoException, VagaIndisoponivelException, ServicoNaoExecutadoException {
        veiculo.estacionar(vaga);
        veiculo.sair();
        double valorTotal = veiculo.totalArrecadado();
        assertTrue(valorTotal >= 0);
    }

    @Test
    public void testArrecadadoNoMes() throws LotadoException, VagaIndisoponivelException, ServicoNaoExecutadoException {
        veiculo.estacionar(vaga);
        veiculo.sair();
        int mes = 10;
        double valorArrecadado = veiculo.arrecadadoNoMes(mes);
        assertTrue(valorArrecadado >= 0);
    }

    @Test
    public void testTotalDeUsos() throws LotadoException, VagaIndisoponivelException {
        veiculo.estacionar(vaga);
        assertEquals(1, veiculo.totalDeUsos());
    }

    @Test
    public void testGetPlaca() {
        assertEquals("ABC1234", veiculo.getPlaca());
    }
}
