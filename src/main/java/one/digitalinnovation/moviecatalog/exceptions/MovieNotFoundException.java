package one.digitalinnovation.moviecatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MovieNotFoundException extends Exception {

    public MovieNotFoundException(String movieName) {
        super(String.format("Movie with name %s not found in the system.", movieName));
    }

    public MovieNotFoundException(Long id) {
        super(String.format("Movie with id %d not found in the system.", id));
    }
}
