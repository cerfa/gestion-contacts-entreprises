package fenncim.genba.genesis.gce.exception;

public class EnterpriseDataException extends  Exception {
    private final String message;

    public EnterpriseDataException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
