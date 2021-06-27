package one.digitalinnovation.moviecatalog.mappers;

import one.digitalinnovation.moviecatalog.models.DTO.MovieDTO;
import one.digitalinnovation.moviecatalog.models.entitys.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {

    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "releaseDate", source = "releaseDate", dateFormat = "dd-MM-yyyy")
    Movie toModel(MovieDTO movieDTO);

    @Mapping(target = "releaseDate", source = "releaseDate", dateFormat = "dd-MM-yyyy")
    MovieDTO toDTO(Movie movie);

}
