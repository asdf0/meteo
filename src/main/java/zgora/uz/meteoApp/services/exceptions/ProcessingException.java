package zgora.uz.meteoApp.services.exceptions;

public class ProcessingException extends RuntimeException{
    public ProcessingException() {
    }

    public ProcessingException(String s) {
        super(s);
    }
}
