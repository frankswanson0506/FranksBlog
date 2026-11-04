package com.myblog.myblog.config;

import com.myblog.myblog.model.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionAdvice {
    @ExceptionHandler(NoSuchElementException.class)
    public Result<Void> notFound(NoSuchElementException ex) {
        String msg = ex.getMessage() == null ? "not found" : ex.getMessage();
        return Result.fail(404, msg);
    }
}