package com.eduardo.voting.error;

import com.eduardo.voting.api.v1.web.response.VotingErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;


@RestControllerAdvice
public class VotingExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleVotingErrors(RuntimeException error, HttpServletRequest reqeust) {
        String message;
        if(error instanceof ValidationException) message = error.getCause().getMessage();
        else message = error.getMessage();

        VotingErrorResponse errorResponse = VotingErrorResponse.builder().message(message).build();
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleClientErrors(MethodArgumentNotValidException error, HttpServletRequest reqeust) {
        VotingErrorResponse errorResponse = VotingErrorResponse.builder().message("Validation for the given CPF failed").build();
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
