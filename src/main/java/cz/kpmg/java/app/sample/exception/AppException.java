package cz.kpmg.java.app.sample.exception;

import org.springframework.http.HttpStatus;

public abstract class AppException extends Exception {

    protected AppException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();

}
