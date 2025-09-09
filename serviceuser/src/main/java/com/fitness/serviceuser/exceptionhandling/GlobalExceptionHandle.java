package com.fitness.serviceuser.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandle {

    @ExceptionHandler(exception = Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllException(Exception ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistException ex,WebRequest request) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(),
                request.getDescription(false));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex,WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ErrorResponse error = new ErrorResponse("VALIDATION_ERROR", message);
        return ResponseEntity.badRequest().body(error);
    }

}
