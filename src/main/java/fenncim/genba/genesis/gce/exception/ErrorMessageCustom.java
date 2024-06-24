package fenncim.genba.genesis.gce.exception;

import java.sql.Timestamp;
import java.util.UUID;

public class ErrorMessageCustom {
    private UUID id = UUID.randomUUID();
    private String message;
    private Timestamp timestamp;

    public ErrorMessageCustom(String message) {
        this.message = message;
    }

    public ErrorMessageCustom(String message, Timestamp timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}