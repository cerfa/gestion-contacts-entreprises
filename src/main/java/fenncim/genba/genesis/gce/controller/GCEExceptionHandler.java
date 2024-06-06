package fenncim.genba.genesis.gce.controller;


import fenncim.genba.genesis.gce.exception.ContactDataException;
import fenncim.genba.genesis.gce.exception.EnterpriseDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.logging.ErrorManager;

@ControllerAdvice
public class GCEExceptionHandler {
    @ExceptionHandler({Exception.class, HttpMediaTypeNotAcceptableException.class, EnterpriseDataException.class,
            ContactDataException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> manageTargetsDataRetrievalException(WebRequest request, Exception occuredException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(occuredException.getMessage());
    }
 }
