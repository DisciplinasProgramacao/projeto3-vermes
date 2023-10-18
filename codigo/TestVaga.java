import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VagaTest {

    @Test
    public void testEstacionar() {
        Vaga vaga = new Vaga(1, 1);
        assertTrue(vaga.disponivel());
        assertTrue(vaga.estacionar());
        assertFalse(vaga.disponivel());
    }

    @Test
    public void testSair() {
        Vaga vaga = new Vaga(1, 1);
        vaga.estacionar();
        assertFalse(vaga.disponivel());
        assertTrue(vaga.sair());
        assertTrue(vaga.disponivel());
    }

    @Test
    public void testDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        assertTrue(vaga.disponivel());
        vaga.estacionar();
        assertFalse(vaga.disponivel());
    }
}
