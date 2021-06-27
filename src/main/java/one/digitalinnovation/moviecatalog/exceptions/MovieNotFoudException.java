package one.digitalinnovation.moviecatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class MovieNotFoudException extends Exception {

    public MovieNotFoudException(String movieName) {
        super(String.format("Movie with name %s not found in the system.", movieName));
    }

    public MovieNotFoudException(Long id) {
        super(String.format("Movie with id %d not found in the system.", id));
    }
}
