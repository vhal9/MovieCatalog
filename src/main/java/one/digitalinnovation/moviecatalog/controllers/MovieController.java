package one.digitalinnovation.moviecatalog.controllers;

import lombok.AllArgsConstructor;
import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.services.MovieService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movie")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO createMovie(@RequestBody @Valid MovieDTO movieDTO) {

        return movieService.createMovie(movieDTO);

    }

    @GetMapping
    public List<MovieDTO> listMovies() {

        return movieService.listMovies();

    }

}
