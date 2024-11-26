package ru.filatov.libasis.adapter.web.advice;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.filatov.libasis.adapter.web.advice.excep.CustomException;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionControllerAdvice {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomException exception(ResourceNotFoundException e)
    {
        return CustomException.create(e);
    }
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public CustomException exception(AccessDeniedException e){
        return CustomException.create(e);
    }
}
