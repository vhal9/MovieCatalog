package one.digitalinnovation.moviecatalog.controllers;

import one.digitalinnovation.moviecatalog.exceptions.ApiError;
import one.digitalinnovation.moviecatalog.exceptions.MovieNotFoudException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MovieNotFoudException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleMovieNotFoundException(MovieNotFoudException ex) {

        return new ApiError(ex.getMessage());

    }

}
