package one.digitalinnovation.moviecatalog.builder;

import lombok.Builder;
import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.models.enums.Genre;

@Builder
public class MovieDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Avengers";

    @Builder.Default
    private Genre genre = Genre.ACTION;

    @Builder.Default
    private int rate = 90;

    @Builder.Default
    private String releaseDate = "21-12-2020";

    public MovieDTO toMovieDTO() {
        return new MovieDTO(id,
                name,
                genre,
                rate,
                releaseDate);
    }


}
