public class VagaIndisoponivelException extends Exception {
    private String message;
    
    public String getMessage() {
        return message;
    }
    public VagaIndisoponivelException(String message){
        super(message);
    }
}
