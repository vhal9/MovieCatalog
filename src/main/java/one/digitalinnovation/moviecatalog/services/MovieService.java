package one.digitalinnovation.moviecatalog.services;

import lombok.AllArgsConstructor;
import one.digitalinnovation.moviecatalog.exceptions.MovieAlreadyRegisteredException;
import one.digitalinnovation.moviecatalog.exceptions.MovieNotFoundException;
import one.digitalinnovation.moviecatalog.mappers.MovieMapper;
import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.models.entitys.Movie;
import one.digitalinnovation.moviecatalog.repositorys.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper = MovieMapper.INSTANCE;

    public MovieDTO createMovie(MovieDTO movieDTO) throws MovieAlreadyRegisteredException {

        verifyIfIsAlreadyRegistered(movieDTO.getName());

        Movie movie = movieMapper.toModel(movieDTO);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDTO(savedMovie);

    }

    public List<MovieDTO> listMovies() {

        List<Movie> allMovies = movieRepository.findAll();

        return allMovies.stream()
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());

    }

    public MovieDTO findByName(String name) throws MovieNotFoundException {

        Movie foundMovie = movieRepository.findByName(name)
                .orElseThrow(() -> new MovieNotFoundException(name));

        return movieMapper.toDTO(foundMovie);

    }

    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) throws MovieNotFoundException {

        movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        Movie movie = movieMapper.toModel(movieDTO);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDTO(savedMovie);

    }

    public void deleteById(Long id) throws MovieNotFoundException {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(id));

        movieRepository.delete(movie);

    }

    private void verifyIfIsAlreadyRegistered(String name) throws MovieAlreadyRegisteredException {
        Optional<Movie> movieFound = movieRepository.findByName(name);

        if (movieFound.isPresent())
            throw new MovieAlreadyRegisteredException(name);
    }
}
