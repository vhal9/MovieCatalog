package one.digitalinnovation.moviecatalog.controllers;

import lombok.AllArgsConstructor;
import one.digitalinnovation.moviecatalog.exceptions.MovieAlreadyRegisteredException;
import one.digitalinnovation.moviecatalog.exceptions.MovieNotFoundException;
import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.services.MovieService;
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
    public MovieDTO createMovie(@RequestBody @Valid MovieDTO movieDTO) throws MovieAlreadyRegisteredException {

        return movieService.createMovie(movieDTO);

    }

    @GetMapping
    public List<MovieDTO> listMovies() {

        return movieService.listMovies();

    }

    @GetMapping("/{name}")
    public MovieDTO findByName(@PathVariable String name) throws MovieNotFoundException {

        return movieService.findByName(name);

    }

    @PutMapping("/{id}")
    public MovieDTO updateMovie(@PathVariable Long id, @RequestBody @Valid MovieDTO movieDTO) throws MovieNotFoundException {

        return movieService.updateMovie(id, movieDTO);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws MovieNotFoundException {

        movieService.deleteById(id);

    }

}
