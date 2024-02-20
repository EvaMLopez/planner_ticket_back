package dev.plannerticket.Exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FileExceptionHandler  {
     @ExceptionHandler(FileException.class)
    public ResponseEntity<String> handleFileException(FileException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ex.getMessage());
    }
}
