package com.javanauta.bff_agendador.controller;



import com.javanauta.bff_agendador.infrastructure.exceptions.ConflictException;
import com.javanauta.bff_agendador.infrastructure.exceptions.IlegalArgumentException;
import com.javanauta.bff_agendador.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.bff_agendador.infrastructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalExceptionHandler {


    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException (ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler (ConflictException.class)
    public ResponseEntity<String> handleConflictException (ConflictException ex ){
        return new ResponseEntity<>(ex.getMessage() , HttpStatus.CONFLICT);
    }

    @ExceptionHandler (UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException (UnauthorizedException ex ) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IlegalArgumentException.class)
    public ResponseEntity<String> handleIlegalArgumentException(IlegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
