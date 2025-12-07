package com.berkay.ranker.common.exceptionHandling;

import com.berkay.ranker.common.exceptionHandling.customExceptions.DuplicateResourceException;
import com.berkay.ranker.common.exceptionHandling.customExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex) {

        String localizedMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );

        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", 404,
                        "error", localizedMessage
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<?> handleDuplicate(DuplicateResourceException ex) {

        String localizedMessage = messageSource.getMessage(
                ex.getMessageKey(),
                ex.getArgs(),
                LocaleContextHolder.getLocale()
        );

        return new ResponseEntity<>(
                Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", 409,
                        "error", localizedMessage
                ),
                HttpStatus.CONFLICT
        );
    }

}
