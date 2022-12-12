package ru.vsu.csf.asashina.universitysystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class ErrorHandler {

    private static final String INTERNAL_SERVER_ERROR_TEXT = "Internal server error";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> internalServerErrorHandler(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.internalServerError().body(INTERNAL_SERVER_ERROR_TEXT);
    }

    @ExceptionHandler({TypeMismatchException.class, NoSuchFieldException.class})
    public ResponseEntity<?> badRequestErrorHandler(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<?> methodArgumentNotValidExceptionHandler(BindException e) {
        return ResponseEntity.badRequest().body(e.getBindingResult().getAllErrors().stream()
                .map(error -> (FieldError) error)
                .collect(Collectors.toMap(
                        FieldError::getField,
                        DefaultMessageSourceResolvable::getDefaultMessage,
                        (message1, message2) -> message1 + ", " + message2
                )));
    }

    @ExceptionHandler({ClassNotFoundException.class})
    public ResponseEntity<?> notFoundErrorHandler(Exception e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> conflictErrorHandler(Exception e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> forbiddenErrorHandler(Exception e) {
        return ResponseEntity.status(403).body(e.getMessage());
    }
}
