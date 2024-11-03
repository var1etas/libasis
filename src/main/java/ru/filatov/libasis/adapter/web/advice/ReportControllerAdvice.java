package ru.filatov.libasis.adapter.web.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.filatov.libasis.adapter.web.controller.ReportController;

import java.util.NoSuchElementException;

@ControllerAdvice(basePackageClasses = ReportController.class)
public class ReportControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * Отдает статус 404 Not Found в случае выброса NoSuchElementException
     * @author Alexandr Filatov
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleException(NullPointerException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Отдает статус 422 Unprocessable Entity в случае не валидных аргументов метода
     * @author Alexandr Filatov
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
