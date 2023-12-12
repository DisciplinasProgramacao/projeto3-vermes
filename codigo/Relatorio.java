import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Relatorio implements Observer, Serializable{
    private List<Cliente> top5Clientes ;
    private double arrecadacaoMensal;
    
    

    public Relatorio( ) {
        this.top5Clientes = new LinkedList<>();; // O Próprio Observer notifica em ordem o Relatório 
        this.arrecadacaoMensal = 0.0;
        
    }

    @Override
    public void update(double valor , Cliente cliente) {
        
        this.arrecadacaoMensal += valor;
        this.top5Clientes.add(cliente);
        this.top5Clientes = this.top5Clientes.stream().sorted(Comparator.comparing(Cliente::arrecadadoTotal).reversed()).collect(Collectors.toList());
        if(this.top5Clientes.size() > 5){
            this.top5Clientes.remove(5);
        }
        
    }

    public List<Cliente> getTop5Clientes() {
        return top5Clientes;
    }
    public void setArrecadacaoMensal(double arrecadacaoMensal) {
        this.arrecadacaoMensal = arrecadacaoMensal;
    }
    
}

