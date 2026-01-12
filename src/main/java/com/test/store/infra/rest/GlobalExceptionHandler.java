package com.test.store.infra.rest;

import com.test.store.domain.exception.EntityNotFoundException;
import com.test.store.domain.exception.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemDetail> handleException(Exception ex) {
        return handleError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected internal error occurred.", ex.getMessage());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ProblemDetail> handleMissingParameter(MissingServletRequestParameterException ex) {
        return handleError(HttpStatus.BAD_REQUEST, "Missing parameter.", ex.getMessage());
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ProblemDetail> handleInvalidParameter(InvalidInputException ex) {
        return handleError(HttpStatus.BAD_REQUEST, "Invalid parameter.", ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleNotFound(EntityNotFoundException ex) {
        return handleError(HttpStatus.NOT_FOUND, "Entity not found", ex.getMessage());
    }

    private ResponseEntity<ProblemDetail> handleError(HttpStatus status, String title, String detail) {
        log.error("Handling API error - Status: {}, Title: {}, Detail: {}", status, title, detail);
        ProblemDetail error = ProblemDetail.forStatusAndDetail(status, detail);
        error.setTitle(title);
        error.setProperty("timestamp", OffsetDateTime.now());
        error.setProperty("application", "StoreApp");
        return ResponseEntity.status(status).body(error);
    }
}
