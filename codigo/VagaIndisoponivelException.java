/**
 * Classe que representa uma exceção de vaga indisponível.
 */
public class VagaIndisoponivelException extends Exception {
    private String message;
    
    public String getMessage() {
        return message;
    }
    /**
     * Construtor padrão da exceção.
     * @param message Mensagem de erro.
     */
    public VagaIndisoponivelException(String message){
        super(message);
    }
}
