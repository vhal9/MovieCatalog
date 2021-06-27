package one.digitalinnovation.moviecatalog.services;

import lombok.AllArgsConstructor;
import one.digitalinnovation.moviecatalog.mappers.MovieMapper;
import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.models.entitys.Movie;
import one.digitalinnovation.moviecatalog.repositorys.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper = MovieMapper.INSTANCE;

    public MovieDTO createMovie(MovieDTO movieDTO) {

        Movie movie = movieMapper.toModel(movieDTO);
        Movie savedMovie = movieRepository.save(movie);
        return movieMapper.toDTO(savedMovie);

    }
    
}