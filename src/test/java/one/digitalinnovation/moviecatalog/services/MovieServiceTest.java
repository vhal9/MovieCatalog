package one.digitalinnovation.moviecatalog.services;

import one.digitalinnovation.moviecatalog.builder.MovieDTOBuilder;
import one.digitalinnovation.moviecatalog.exceptions.MovieAlreadyRegisteredException;
import one.digitalinnovation.moviecatalog.exceptions.MovieNotFoudException;
import one.digitalinnovation.moviecatalog.mappers.MovieMapper;
import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.models.entitys.Movie;
import one.digitalinnovation.moviecatalog.repositorys.MovieRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    private static final long INVALID_MOVIE_ID = 1L;

    @Mock
    private MovieRepository movieRepository;

    private MovieMapper movieMapper = MovieMapper.INSTANCE;

    @InjectMocks
    private MovieService movieService;

    @Test
    void whenMovieInformedThenItShouldBeCreated() throws MovieAlreadyRegisteredException {

        //given
        MovieDTO expectedMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();
        Movie expectedSavedMovie = movieMapper.toModel(expectedMovieDTO);

        //when
        when(movieRepository.findByName(expectedMovieDTO.getName())).thenReturn(Optional.empty());
        when(movieRepository.save(expectedSavedMovie)).thenReturn(expectedSavedMovie);

        //then
        MovieDTO createdMovieDTO = movieService.createMovie(expectedMovieDTO);

        assertThat(createdMovieDTO.getId(), is(equalTo(expectedMovieDTO.getId())));
        assertThat(createdMovieDTO.getName(), is(equalTo(expectedMovieDTO.getName())));

    }

    @Test
    void whenAlreadyRegisteredMovieInformedThenAnExceptionShouldBeThrown() {

        //given
        MovieDTO expectedMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();
        Movie duplicatedMovie = movieMapper.toModel(expectedMovieDTO);

        //when
        when(movieRepository.findByName(expectedMovieDTO.getName())).thenReturn(Optional.of(duplicatedMovie));

        //then
        assertThrows(MovieAlreadyRegisteredException.class, () -> movieService.createMovie(expectedMovieDTO));

    }

    @Test
    void whenValidMovieNameIsGivenThenReturnAMovie() throws MovieNotFoudException {

        // given
        MovieDTO expectedFoundMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();
        Movie expectedFoundMovie = movieMapper.toModel(expectedFoundMovieDTO);

        // when
        when(movieRepository.findByName(expectedFoundMovie.getName())).thenReturn(Optional.of(expectedFoundMovie));

        // then
        MovieDTO foundMovieDTO = movieService.findByName(expectedFoundMovie.getName());

        assertThat(foundMovieDTO, Matchers.is(equalTo(expectedFoundMovieDTO)));

    }

    @Test
    void whenNotRegisteredMovieNameIsGivenThenThrownAnException() throws MovieNotFoudException {

        // given
        MovieDTO expectedFoundMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();

        // when
        when(movieRepository.findByName(expectedFoundMovieDTO.getName())).thenReturn(Optional.empty());

        // then
        assertThrows(MovieNotFoudException.class, () -> movieService.findByName(expectedFoundMovieDTO.getName()));

    }

    @Test
    void whenListMovieIsCalledThenReturnAListOfMovies() {

        // given
        MovieDTO expectedFoundMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();
        Movie expectedFoundMovie = movieMapper.toModel(expectedFoundMovieDTO);

        //when
        when(movieRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundMovie));

        //then
        List<MovieDTO> foundListMovieDTO = movieService.listMovies();

        assertThat(foundListMovieDTO, is(not(empty())));
        assertThat(foundListMovieDTO.get(0), is(equalTo(expectedFoundMovieDTO)));

    }

    @Test
    void whenListMovieIsCalledThenReturnAEmptyListOfMovies() {

        //when
        when(movieRepository.findAll()).thenReturn(Collections.emptyList());

        //then
        List<MovieDTO> foundListMovieDTO = movieService.listMovies();

        assertThat(foundListMovieDTO, is(empty()));

    }

    @Test
    void whenExclusionIsCalledWithValidIdThenAMovieShouldBeDeleted() throws MovieNotFoudException {

        //given
        MovieDTO expectedDeleteMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();
        Movie expectedDeleteMovie = movieMapper.toModel(expectedDeleteMovieDTO);

        //when
        when(movieRepository.findById(expectedDeleteMovieDTO.getId())).thenReturn(Optional.of(expectedDeleteMovie));
        doNothing().when(movieRepository).delete(expectedDeleteMovie);

        //then
        movieService.deleteById(expectedDeleteMovieDTO.getId());

        verify(movieRepository, times(1)).findById(expectedDeleteMovieDTO.getId());
        verify(movieRepository, times(1)).delete(expectedDeleteMovie);

    }

    @Test
    void whenExclusionIsCalledWithInvalidIdThenThrownAnException() throws MovieNotFoudException {

        //when
        when(movieRepository.findById(INVALID_MOVIE_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(MovieNotFoudException.class, () -> movieService.deleteById(INVALID_MOVIE_ID));

    }

    @Test
    void whenUpdateIsCalledWithValidIdThenMovieShouldBeUpdated() throws MovieNotFoudException {

        //given
        MovieDTO expectedMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();
        Movie expectedUpdatedMovie = movieMapper.toModel(expectedMovieDTO);

        //when
        when(movieRepository.findById(expectedMovieDTO.getId())).thenReturn(Optional.of(expectedUpdatedMovie));
        when(movieRepository.save(expectedUpdatedMovie)).thenReturn(expectedUpdatedMovie);

        //then
        MovieDTO updatedMovieDTO = movieService.updateMovie(expectedMovieDTO.getId(), expectedMovieDTO);

        assertThat(updatedMovieDTO, Matchers.is(equalTo(expectedMovieDTO)));

    }

    @Test
    void whenUpdateIsCalledWithInvalidIdThenThrowAnException() {

        //given
        MovieDTO expectedMovieDTO = MovieDTOBuilder.builder().build().toMovieDTO();

        //when
        when(movieRepository.findById(INVALID_MOVIE_ID)).thenReturn(Optional.empty());

        //then
        assertThrows(MovieNotFoudException.class, () -> movieService.updateMovie(INVALID_MOVIE_ID, expectedMovieDTO));

    }

}
