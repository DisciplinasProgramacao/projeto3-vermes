
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

public class UsoDeVagaTest {
 
    private Vaga vaga;
    private UsoDeVaga usoDeVaga;

    

    

    @Test
    public void testContratarServico() {
        criaUsoDeVagaHelper(1, 1);
        usoDeVaga.contratarServico(Servico.MANOBRISTA);
        assertEquals("Constratando serviço correto",Servico.MANOBRISTA, usoDeVaga.getServico());
    }
      
    @Test
    public void testSair() {
        criaUsoDeVagaHelper(1, 2);
        usoDeVaga.sair();
        assertEquals("Imprimindo data de saída atual",LocalDateTime.now(), usoDeVaga.getSaida());
    }

    @Test
    public void testCalculcarValorPago(){
        criaUsoDeVagaHelper(1, 3);
        usoDeVaga.sair();
        usoDeVaga.calcularValorPago();
        assertEquals("Calculando valor pago",0.0, usoDeVaga.valorPago(), 0.0);
    }

    @Test
    public void testCalcularDiferencaEmMinutos(){
        criaUsoDeVagaHelper(1, 4);
        LocalDateTime inicio = LocalDateTime.of(2020, 10, 10, 10, 10);
        LocalDateTime fim = LocalDateTime.of(2020, 10, 10, 10, 20);
        assertEquals("Calculando diferença em minutos",10, usoDeVaga.calcularDiferencaEmMinutos(inicio, fim));
    }

    public void criaUsoDeVagaHelper(int fila, int coluna){
        vaga = new Vaga(fila, coluna);
        usoDeVaga = new UsoDeVaga(vaga);
    }

    
   
    

    
    

}

