package one.digitalinnovation.moviecatalog.exceptions;

public class MovieAlreadyRegisteredException extends Exception {

    public MovieAlreadyRegisteredException(String movieName) {
        super(String.format("Movie with name %s is already registered in the system.", movieName));
    }

}
