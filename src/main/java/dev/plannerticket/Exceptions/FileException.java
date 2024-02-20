package dev.plannerticket.Exceptions;

import org.springframework.http.HttpStatus;

 public class FileException extends RuntimeException {
    private HttpStatus httpStatus;

    public FileException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

