package com.FACTor.Digital_Wallet.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> illegalArgException(IllegalArgumentException ex){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Status", "BAD_REQUEST",
                                    "Message", ex.getMessage()));
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> invalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Status", "UNAUTHORIZED",
                "Message", ex.getMessage()));
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Status", "NOT_FOUND",
                "Message", ex.getMessage()));
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, Object>> usernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Status", "NOT_FOUND",
              "Message", ex.getMessage()));
    }
    @ExceptionHandler(ExistingUserException.class)
    public ResponseEntity<Map<String, Object>> existingUserException(ExistingUserException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("Status", "CONFLICT",
                "Message", ex.getMessage()));
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Object>> constraintViolation(ConstraintViolationException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Status", "BAD_REQUEST",
                "Message",ex.getMessage()));
    }
}
