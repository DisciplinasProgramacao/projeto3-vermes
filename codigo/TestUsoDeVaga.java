
import org.junit.Test;


import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class TestUsoDeVaga {
 
    private Vaga vaga;
    private UsoDeVaga usoDeVaga;

    

    

    @Test
    public void testContratarServico() throws VagaIndisoponivelException {
        criaUsoDeVagaHelper(1, 1);
        usoDeVaga.contratarServico(Servico.MANOBRISTA);
        assertEquals("Constratando serviço correto",Servico.MANOBRISTA, usoDeVaga.getServico());
    }
      
    @Test
    public void testSair() throws ServicoNaoExecutadoException, VagaIndisoponivelException {
        Cliente cliente = new Cliente("John Doe", "123", TipoDePlano.MENSALISTA, 100.0);
    
        criaUsoDeVagaHelper(1, 2);
        usoDeVaga.sair(cliente);  
        assertEquals("Imprimindo data de saída atual", LocalDateTime.now(), usoDeVaga.getSaida());
    }

    @Test
    public void testCalculcarValorPago() throws ServicoNaoExecutadoException, VagaIndisoponivelException {
        Cliente cliente = new Cliente("John Doe", "123", TipoDePlano.MENSALISTA, 100.0);
    
        criaUsoDeVagaHelper(1, 3);
        usoDeVaga.sair(cliente); 
        usoDeVaga.calcularValorPago(cliente); 
        assertNotEquals(4.0, usoDeVaga.valorPago());
    }
    
    @Test
    public void testCalcularDiferencaEmMinutos() throws VagaIndisoponivelException{
        criaUsoDeVagaHelper(1, 4);
        LocalDateTime inicio = LocalDateTime.of(2020, 10, 10, 10, 10);
        LocalDateTime fim = LocalDateTime.of(2020, 10, 10, 10, 20);
        assertEquals("Calculando diferença em minutos",10, usoDeVaga.calcularDiferencaEmMinutos(inicio, fim));
    }
        
    @Test
public void testVagaIndisponivel() {
    assertThrows(VagaIndisoponivelException.class, () -> {
        
        Cliente cliente = new Cliente("John Doe", "123", TipoDePlano.MENSALISTA, 100.0);

        criaUsoDeVagaHelper(1, 5);
        UsoDeVaga usoDeVaga2 = new UsoDeVaga(vaga);
        usoDeVaga.sair(cliente); 
        usoDeVaga.calcularValorPago(cliente); 
    });
}

@Test
public void testServicoNaoExecutado() {
    assertThrows(ServicoNaoExecutadoException.class, () -> {
        
        Cliente cliente = new Cliente("John Doe", "123", TipoDePlano.MENSALISTA, 100.0);

        criaUsoDeVagaHelper(1, 6);
        usoDeVaga.contratarServico(Servico.LAVAGEM);
        usoDeVaga.sair(cliente); 
        usoDeVaga.calcularValorPago(cliente); 
    });
}


    public void criaUsoDeVagaHelper (int fila, int coluna) throws VagaIndisoponivelException{
        vaga = new Vaga(fila, coluna);
        usoDeVaga = new UsoDeVaga(vaga);
    }

    
   
    

    
    

}



