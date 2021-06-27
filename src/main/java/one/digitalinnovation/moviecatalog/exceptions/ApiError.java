package one.digitalinnovation.moviecatalog.exceptions;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    @Getter
    private List<String> errors;

    public ApiError(String message) {

        this.errors = Arrays.asList(message);

    }

}
