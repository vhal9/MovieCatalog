package one.digitalinnovation.moviecatalog.controllers;

import one.digitalinnovation.moviecatalog.builder.MovieDTOBuilder;
import one.digitalinnovation.moviecatalog.exceptions.MovieAlreadyRegisteredException;
import one.digitalinnovation.moviecatalog.exceptions.MovieNotFoudException;
import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.services.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static one.digitalinnovation.moviecatalog.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    private static final String MOVIE_API_URL_PATH = "/api/v1/movie";
    private static final long VALID_MOVIE_ID = 1L;
    private static final long INVALID_MOVIE_ID = 2l;

    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPOSTIsCalledThenAMovieIsCreated() throws Exception {

        //given
        MovieDTO movieDTO = MovieDTOBuilder.builder().build().toMovieDTO();

        //when
        when(movieService.createMovie(movieDTO)).thenReturn(movieDTO);

        //then
        mockMvc.perform(post(MOVIE_API_URL_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(movieDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(movieDTO.getName())))
                .andExpect(jsonPath("$.rate", is(movieDTO.getRate())))
                .andExpect(jsonPath("$.releaseDate", is(movieDTO.getReleaseDate())));

    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {

        //given
        MovieDTO movieDTO = MovieDTOBuilder.builder().build().toMovieDTO();
        movieDTO.setName("");

        //then
        mockMvc.perform(post(MOVIE_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movieDTO)))
                .andExpect(status().isBadRequest());

    }

}
