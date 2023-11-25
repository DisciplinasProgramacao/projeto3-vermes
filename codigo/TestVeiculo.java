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
        vaga = new Vaga(1, 1); // Fornecendo valores válidos para fila e número
        Servico servico = Servico.MANOBRISTA; // Inicializando o serviço corretamente
        veiculo.setServico(servico); // Configurando o serviço no veículo
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
            veiculo.estacionar(vaga);
            double valorPago = veiculo.sair();
            assertTrue(valorPago >= 0);
        } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
    public void testTotalArrecadado() {
        try {
            veiculo.estacionar(vaga);
            veiculo.sair();
            double valorTotal = veiculo.totalArrecadado();
            assertTrue(valorTotal >= 0);
        } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
    public void testArrecadadoNoMes() {
        try {
            veiculo.estacionar(vaga);
            veiculo.sair();
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
}import static org.junit.Assert.assertEquals;
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
        vaga = new Vaga(1, 1); // Fornecendo valores válidos para fila e número
        Servico servico = Servico.MANOBRISTA; // Inicializando o serviço corretamente
        veiculo.setServico(servico); // Configurando o serviço no veículo
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
            veiculo.estacionar(vaga);
            double valorPago = veiculo.sair();
            assertTrue(valorPago >= 0);
        } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
    public void testTotalArrecadado() {
        try {
            veiculo.estacionar(vaga);
            veiculo.sair();
            double valorTotal = veiculo.totalArrecadado();
            assertTrue(valorTotal >= 0);
        } catch (LotadoException | VagaIndisoponivelException | ServicoNaoExecutadoException e) {
            fail("Não deveria lançar exceção aqui.");
        }
    }

    @Test
    public void testArrecadadoNoMes() {
        try {
            veiculo.estacionar(vaga);
            veiculo.sair();
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
