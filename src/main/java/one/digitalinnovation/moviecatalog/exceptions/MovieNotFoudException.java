package one.digitalinnovation.moviecatalog.exceptions;

public class MovieNotFoudException extends Exception {
    public MovieNotFoudException(String movieName) {
        super(String.format("Movie with name %s not found in the system.", movieName));
    }
}
