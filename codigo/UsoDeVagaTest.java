
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class UsoDeVagaTest {
    

    @Test
    public void testVagaDisponivel() {
        criaUsoDeVagaHelper(1, true);
        assertTrue(vaga.isDisponivel());
    }

    @Test
    public void testVagaOcupada() {
        criaUsoDeVagaHelper(2, false);
        assertFalse(vaga.isDisponivel());
    }

    @Test
    public void testAlterarDisponibilidade() {
        criaUsoDeVagaHelper(3, true);
        vaga.setDisponivel(false);
        assertFalse(vaga.isDisponivel());
    }

    @Test 
    public void testContratarManobrista() {
        criaUsoDeVagaHelper(4, true);
        vaga.contratarManobrista();
        assertTrue(vaga.isManobristaContratado());
    }

    @Test
    public void testContratarLavagem() {
        criaUsoDeVagaHelper(5, true);
        vaga.contratarLavagem();
        assertTrue(vaga.isLavagemContratada());
    }

    @Test 
    public void testContratarPolimento() {
        criaUsoDeVagaHelper(6, true);
        vaga.contratarPolimento();
        assertTrue(vaga.isPolimentoContratado());
    }

    @Test
    public void testSair() {
        criaUsoDeVagaHelper(7, true);
        vaga.sair();
        assertNotNull(vaga.getSaida());
    }

    @Test
    public void testValorPago() {
        criaUsoDeVagaHelper(8, true);
        vaga.sair();
        assertNotNull(vaga.valorPago());
    }
    @Test
    public void testCalcularValorPago() {
        criaUsoDeVagaHelper(9, true);
        vaga.sair();
        assertNotNull(vaga.calcularValorPago());
    }
    @Test
    public void testCalcularDiferencaEmMinutos() {
        criaUsoDeVagaHelper(10, true);
        vaga.sair();
        assertNotNull(vaga.calcularDiferencaEmMinutos());
    }
    @Test
    public void testCalcularValorPagoComSeriços() {
        criaUsoDeVagaComServiçoHelper(11, true, true, true, true);
        vaga.sair();
        assertNotNull(vaga.calcularValorPago());
    }
    
    




    public void criaUsoDeVagaHelper(int fila,  boolean disponivel) {
        Vaga vaga = new Vaga(fila, disponivel);
        UsoDeVaga vaga = new UsoDeVaga(vaga);
       
    }
    public void criaUsoDeVagaComServiçoHelper(int fila, boolean disponivel, boolean manobristaContratado, boolean lavagemContratada, boolean polimentoContratado) {
        Vaga vaga = new Vaga(fila, disponivel);
        UsoDeVaga vaga = new UsoDeVaga(vaga);
        vaga.contratarManobrista();
        vaga.contratarLavagem();
        vaga.contratarPolimento();
    }
    

}

