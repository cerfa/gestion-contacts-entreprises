package fenncim.genba.genesis.gce.controller;


import fenncim.genba.genesis.gce.exception.ContactDataException;
import fenncim.genba.genesis.gce.exception.EnterpriseDataException;
import fenncim.genba.genesis.gce.exception.ErrorMessageCustom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;

@ControllerAdvice
public class GCEExceptionHandler {
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class, EnterpriseDataException.class,
            ContactDataException.class})
    public ResponseEntity<ErrorMessageCustom> manageTargetsDataRetrievalException(Exception occuredException) {
        ErrorMessageCustom errorMessage =  new ErrorMessageCustom(occuredException.getMessage(),
                new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> manageTargetsDataRetrievalGenericException(Exception occuredException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(occuredException.getMessage());
    }
 }
