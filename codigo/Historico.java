import java.util.ArrayList;
import java.util.List;

public class Historico {
    private List<Registro> registros;

    public Historico() {
        registros = new ArrayList<>();
    }

    public void adicionarRegistro(Registro registro) {
        registros.add(registro);
    }
    public List<Registro> getHistorico() {
        return registros;
    }

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
