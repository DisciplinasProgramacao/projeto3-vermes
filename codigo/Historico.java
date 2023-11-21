import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A classe Historico representa um histórico de registros e fornece métodos
 * para gerenciar e recuperar dados históricos.
 */
public class Historico implements Serializable {
    /** A lista de registros armazenados no histórico. */
    private List<Registro> registros;

    /**
     * Constrói uma instância vazia de dados históricos.
     */
    public Historico() {
        registros = new ArrayList<>();
    }

    /**
     * Adiciona um novo registro ao histórico.
     *
     * @param registro O registro a ser adicionado.
     */
    public void adicionarRegistro(Registro registro) {
        registros.add(registro);
    }

    /**
     * Recupera todo o histórico de registros.
     *
     * @return Uma lista de todos os registros no histórico.
     */
    public List<Registro> getHistorico() {
        return registros;
    }

    /**
     * Recupera os registros para um mês específico no histórico.
     *
     * @param mes O mês para o qual os registros devem ser recuperados.
     * @return Uma lista de registros para o mês especificado.
     */
    public List<Registro> getHistoricoPorMes(int mes) {
        List<Registro> historicoMes = new ArrayList<>();
        for (Registro registro : registros) {
            if (registro.getMes() == mes) {
                historicoMes.add(registro);
            }
        }
        return historicoMes;
    }
}
