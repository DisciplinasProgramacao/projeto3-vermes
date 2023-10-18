import static org.junit.Assert.*;
import org.junit.Test;

public class VeiculoTest {

    @Test
    public void testEstacionar() {
        Veiculo veiculo = new Veiculo("ABC-1234");
        Vaga vaga = new Vaga("A1");

        veiculo.estacionar(vaga);

        assertEquals(1, veiculo.totalDeUsos());

        assertEquals(vaga, veiculo.getUsos()[0].getVaga());
    }

    @Test
    public void testSair() {
        Veiculo veiculo = new Veiculo("XYZ-5678");
        Vaga vaga = new Vaga("B2");

        veiculo.estacionar(vaga);

        double valorPago = veiculo.sair();

        assertTrue(valorPago > 0);

        assertEquals(0, veiculo.totalDeUsos());
    }

    @Test
    public void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("LMN-9012");
        Vaga vaga1 = new Vaga("C3");
        Vaga vaga2 = new Vaga("D4");

        veiculo.estacionar(vaga1);
        veiculo.sair();

        veiculo.estacionar(vaga2);
        veiculo.sair();

        double totalArrecadado = veiculo.totalArrecadado();

        assertEquals(2 * (vaga1.getPrecoPorHora() + vaga2.getPrecoPorHora()), totalArrecadado, 0.001);
    }

    @Test
    public void testArrecadadoNoMes() {
        Veiculo veiculo = new Veiculo("PQR-3456");
        Vaga vaga1 = new Vaga("E5");
        Vaga vaga2 = new Vaga("F6");

        veiculo.estacionar(vaga1);
        veiculo.sair();

        veiculo.estacionar(vaga2);
        veiculo.sair();

        assertEquals(0, veiculo.arrecadadoNoMes(1), 0.001);

        int mesAtual = LocalDate.now().getMonthValue();
        double valorEsperado = vaga1.getPrecoPorHora() + vaga2.getPrecoPorHora();
        assertEquals(valorEsperado, veiculo.arrecadadoNoMes(mesAtual), 0.001);
    }
}
