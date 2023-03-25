package com.mycode.foodorderapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        exceptionDetails.setErrorMessages(errors);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleException(Exception ex){
        log.error(ex.getMessage());

        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.getErrorMessages().add(ex.getMessage());
        exceptionDetails.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
