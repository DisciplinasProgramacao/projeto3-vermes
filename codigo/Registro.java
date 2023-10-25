import java.io.Serializable;
import java.util.Date;

public class Registro implements Serializable{
    private Date data;
    private String descricao;
    private double quilometragem;

    public Registro(Date data, String descricao, double quilometragem) {
        this.data = data;
        this.descricao = descricao;
        this.quilometragem = quilometragem;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public int getMes() {
        return 0;
    }
}
