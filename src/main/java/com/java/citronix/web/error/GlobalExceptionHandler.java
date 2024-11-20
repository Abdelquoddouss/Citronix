package com.java.citronix.web.error;

import com.java.citronix.exception.FermeNotFoundException;
import com.java.citronix.exception.InvalidSearchCriteriaException;
import com.java.citronix.exception.NoResultsFoundException;
import com.java.citronix.exception.SuperficieValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());

    }

    @ExceptionHandler(FermeNotFoundException.class)
    public ResponseEntity<String> handleFermeNotFoundException(FermeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


    @ExceptionHandler(NoResultsFoundException.class)
    public ResponseEntity<String> handleNoResultsFound(NoResultsFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidSearchCriteriaException.class)
    public ResponseEntity<String> handleInvalidSearchCriteria(InvalidSearchCriteriaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(SuperficieValidationException.class)
    public ResponseEntity<Map<String, String>> handleSuperficieValidationException(SuperficieValidationException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Superficie Validation Error");
        response.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}