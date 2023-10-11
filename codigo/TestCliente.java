import org.junit.Test;
import static org.junit.Assert.*;

public class TestCliente {

    @Test
    public void testAddVeiculo() {
        Cliente cliente = new Cliente("Maisa", "1");
        Veiculo veiculo = new Veiculo("ABC123");

        cliente.addVeiculo(veiculo);

        assertEquals(veiculo, cliente.possuiVeiculo("ABC123"));
    }

    @Test
    public void testPossuiVeiculo() {
        Cliente cliente = new Cliente("Miguel2", "2");
        Veiculo veiculo = new Veiculo("XYZ789");

        cliente.addVeiculo(veiculo);

        assertEquals(veiculo, cliente.possuiVeiculo("XYZ789"));
        assertNull(cliente.possuiVeiculo("XULAM000"));
    }

    @Test
    public void testTotalDeUsos() {
        Cliente cliente = new Cliente("Salim", "3");
        Veiculo veiculo1 = new Veiculo("PLA111");
        Veiculo veiculo2 = new Veiculo("PLA222");

        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        assertEquals(0, cliente.totalDeUsos());

        assertEquals(2, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        Cliente cliente = new Cliente("Lukinhas", "4");
        Veiculo veiculo = new Veiculo("PLI333");

        cliente.addVeiculo(veiculo);

        assertEquals(20.0, cliente.arrecadadoPorVeiculo("PLI333"), 0.001);
    }

    @Test
    public void testArrecadadoTotal() {
        Cliente cliente = new Cliente("Ferreira", "5");
        Veiculo veiculo1 = new Veiculo("PLE444");
        Veiculo veiculo2 = new Veiculo("PLO555");

        cliente.addVeiculo(veiculo1);
        cliente.addVeiculo(veiculo2);

        assertEquals(20.0, cliente.arrecadadoTotal(), 0.001);
    }

    @Test
    public void testArrecadadoNoMes() {
        Cliente cliente = new Cliente("Faria", "6");
        Veiculo veiculo = new Veiculo("PLU666");

        cliente.addVeiculo(veiculo);

        assertEquals(20.0, cliente.arrecadadoNoMes(10), 0.001);
    }
}
