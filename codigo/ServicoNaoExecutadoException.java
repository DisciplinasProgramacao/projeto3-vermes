public class ServicoNaoExecutadoException extends Exception {
    private String message;
    
    public String getMessage() {
        return message;
    }
    public ServicoNaoExecutadoException(String message){
        super(message);
    }
}
