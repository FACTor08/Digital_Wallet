package com.FACTor.Digital_Wallet.exceptions;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(IOException.class)
    public ResponseEntity<Map<String, Object>> ioException(IOException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(500).body(Map.of("Message", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> illegalArgException(IllegalArgumentException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Status", "BAD_REQUEST",
                                    "Message", ex.getMessage()));
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String, Object>> JwtException(JwtException ex){
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Status", "UNAUTHORIZED",
                "Message", ex.getMessage()));
    }
    @ExceptionHandler(ServletException.class)
    public ResponseEntity<Map<String, Object>> servletException(ServletException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(500).body(Map.of("Message", ex.getMessage()));
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, Object>> invalidCredentialsException(InvalidCredentialsException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Status", "UNAUTHORIZED",
                "Message", ex.getMessage()));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> badCredentialsException(BadCredentialsException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message: ", ex.getMessage()));
    }
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<Map<String, Object>> accountNotFoundException(AccountNotFoundException ex) {
        log.error(ex.getMessage());
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
