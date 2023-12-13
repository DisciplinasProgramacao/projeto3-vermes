
/**
 * Classe que representa uma exceção de serviço não executado.
 * 
 */
public class ServicoNaoExecutadoException extends Exception {
    private String message;
    
    public String getMessage() {
        return message;
    }
    /**
     * Construtor padrão da exceção.
     *
     * @param message Mensagem de erro.
     */
    public ServicoNaoExecutadoException(String message){
        super(message);
    }
}
