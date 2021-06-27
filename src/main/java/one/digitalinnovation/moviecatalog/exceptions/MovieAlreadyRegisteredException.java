package one.digitalinnovation.moviecatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MovieAlreadyRegisteredException extends Exception {

    public MovieAlreadyRegisteredException(String movieName) {
        super(String.format("Movie with name %s is already registered in the system.", movieName));
    }

}
