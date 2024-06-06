package fenncim.genba.genesis.gce.exception;

public class ContactDataException extends  Exception {
    private final String message;

    public ContactDataException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
