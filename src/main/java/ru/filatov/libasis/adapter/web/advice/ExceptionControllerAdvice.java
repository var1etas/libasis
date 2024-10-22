package ru.filatov.libasis.adapter.web.advice;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.filatov.libasis.adapter.web.advice.excep.CustomException;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomException exception(Exception e)
    {
        return CustomException.create(e);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomException exception(ResourceNotFoundException e)
    {
        return CustomException.create(e);
    }
}
