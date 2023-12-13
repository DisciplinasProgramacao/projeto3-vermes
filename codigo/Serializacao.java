import java.io.*;
    /**
     * Classe que representa a serialização do estacionamento.
     * Para que um objeto possa ser salvo em um arquvio, ele deve implementar a interface Serializable.
     */
public class Serializacao {

    public static void salvarEstacionamento(Estacionamento estacionamento, String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(estacionamento);
        }
    }

    public static Estacionamento carregarEstacionamento(String nomeArquivo) throws IOException, ClassNotFoundException {
        Estacionamento estacionamento;

        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            estacionamento = (Estacionamento) objectIn.readObject();
        }

        return estacionamento;
    }
}

