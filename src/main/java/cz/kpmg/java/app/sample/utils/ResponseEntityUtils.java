package cz.kpmg.java.app.sample.utils;

import cz.kpmg.java.app.sample.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityUtils {

    private static final String MESSAGE_HEADER = "message";

    private ResponseEntityUtils() {}

    public static <T> ResponseEntity<T> createEmptyResponseEntity(HttpStatus status) {
        return new ResponseEntity<>(status);
    }

    public static <T> ResponseEntity<T> createResponseEntity(T object, HttpStatus status) {
        return new ResponseEntity<>(object, status);
    }

    public static <T> ResponseEntity<T> fromException(Exception exception) {
        if (exception instanceof AppException appException) {
            switch (appException.getHttpStatus()) {
                case BAD_REQUEST -> {
                    return badRequest(appException.getMessage());
                }
                case NOT_FOUND -> {
                    return notFound(appException.getMessage());
                }
                default -> {
                    return internalServerError(exception.getMessage());
                }
            }
        }
        return internalServerError(exception.getMessage());
    }

    private static <T> ResponseEntity<T> badRequest(String message) {
        return ResponseEntity.badRequest().header(MESSAGE_HEADER, message).build();
    }

    private static <T> ResponseEntity<T> notFound(String message) {
        return ResponseEntity.notFound().header(MESSAGE_HEADER, message).build();
    }

    private static <T> ResponseEntity<T> internalServerError(String message) {
        return ResponseEntity.internalServerError().header(MESSAGE_HEADER, message).build();
    }
}
