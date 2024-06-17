package fenncim.genba.genesis.gce.controller;


import fenncim.genba.genesis.gce.exception.ContactDataException;
import fenncim.genba.genesis.gce.exception.EnterpriseDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GCEExceptionHandler {
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class, EnterpriseDataException.class,
            ContactDataException.class})
    public ResponseEntity<String> manageTargetsDataRetrievalException(Exception occuredException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(occuredException.getMessage());
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> manageTargetsDataRetrievalGenericException(Exception occuredException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(occuredException.getMessage());
    }
 }
