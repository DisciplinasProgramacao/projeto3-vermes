import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;

public class TestVeiculo {
    

    private Veiculo veiculo;
    private Vaga vaga;

    @Before
    public void setUp() {
        veiculo = new Veiculo("ABC1234");
        vaga = new Vaga(1, 1);
        Servico servico = Servico.MANOBRISTA; 
        veiculo.setServico(servico); 
    }
    
    @Test
    public void testEstacionar() {
        try {
            veiculo.estacionar(vaga);
            assertEquals(1, veiculo.totalDeUsos());
        } catch (LotadoException | VagaIndisoponivelException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
public void testSair() {
    try {
        Cliente cliente = new Cliente("John Doe", "123", TipoDePlano.MENSALISTA, 100.0);

        veiculo.estacionar(vaga);
        double valorPago = veiculo.sair(cliente); 
        assertTrue(valorPago >= 0);
    } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
        fail("Não deveria lançar exceção aqui.");
    }
}

    @Test
public void testTotalArrecadado() {
    try {
        Cliente cliente = new Cliente("John Doe", "123", TipoDePlano.MENSALISTA, 100.0);

        veiculo.estacionar(vaga);
        veiculo.sair(cliente); 
        double valorTotal = veiculo.totalArrecadado();
        assertTrue(valorTotal >= 0);
    } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
        fail("Não deveria lançar exceção aqui.");
    }
}

    @Test
    public void testArrecadadoNoMes() {
        try {
            
            Cliente cliente = new Cliente("John Doe", "123", TipoDePlano.MENSALISTA, 100.0);
    
            veiculo.estacionar(vaga);
            veiculo.sair(cliente); 
            int mes = 10;
            double valorArrecadado = veiculo.arrecadadoNoMes(mes);
            assertTrue(valorArrecadado >= 0);
        } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
    public void testTotalDeUsos() {
        try {
            veiculo.estacionar(vaga);
            assertEquals(1, veiculo.totalDeUsos());
        } catch (LotadoException | VagaIndisoponivelException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
    public void testGetPlaca() {
        assertEquals("ABC1234", veiculo.getPlaca());
    }

    @Test
    public void testGetUsoDeVaga() {
        try {
            veiculo.estacionar(vaga);
            UsoDeVaga uso = veiculo.getUsoDeVaga(0);
            assertEquals(vaga, uso.getVaga());
        } catch (LotadoException | VagaIndisoponivelException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }
}
