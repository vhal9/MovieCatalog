package one.digitalinnovation.moviecatalog.controllers;

import one.digitalinnovation.moviecatalog.exceptions.ApiError;
import one.digitalinnovation.moviecatalog.exceptions.MovieAlreadyRegisteredException;
import one.digitalinnovation.moviecatalog.exceptions.MovieNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleMovieNotFoundException(MovieNotFoundException ex) {

        return new ApiError(ex.getMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMethodNotValidException(MethodArgumentNotValidException ex) {

        List<String> erros = ex.getBindingResult().getAllErrors()
                .stream()
                .map( erro -> erro.getDefaultMessage())
                .collect(Collectors.toList());

        return new ApiError(erros);

    }

    @ExceptionHandler(MovieAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleMovieAlreadyRegisteredException(MovieAlreadyRegisteredException ex) {

        return new ApiError(ex.getMessage());

    }

}
