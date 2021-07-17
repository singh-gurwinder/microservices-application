package com.sidhu.auth.exception;

import com.sidhu.auth.exception.model.Response;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected @ResponseBody
    Response handleExceptionInternal(final InvalidCredentialsException ex)
    {
        return new Response(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
