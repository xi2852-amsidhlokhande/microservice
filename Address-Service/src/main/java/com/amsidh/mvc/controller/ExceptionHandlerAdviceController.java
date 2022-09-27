package com.amsidh.mvc.controller;

import com.amsidh.mvc.exception.AddressNotFoundException;
import com.amsidh.mvc.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerAdviceController {

    private static final String EXCEPTION_ERROR_CODE = "0001";
    private static final String RUNTIME_EXCEPTION_ERROR_CODE = "0002";
    private static final String ADDRESS_NOT_FOUND_ERROR_CODE = "0012";

    @ExceptionHandler(value = {AddressNotFoundException.class})
    public ResponseEntity<Error> addressNotFoundException(AddressNotFoundException addressNotFoundException) {
        return ResponseEntity.ok(Error.builder().code(ADDRESS_NOT_FOUND_ERROR_CODE).message(addressNotFoundException.getLocalizedMessage()).timestamp(new Date()).build());
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Error> anyRuntimeException(RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error.builder().code(RUNTIME_EXCEPTION_ERROR_CODE).message(runtimeException.getLocalizedMessage()).timestamp(new Date()).build());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Error> anyException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error.builder().code(EXCEPTION_ERROR_CODE).message(exception.getLocalizedMessage()).timestamp(new Date()).build());
    }

}
