package one.digitalinnovation.moviecatalog.models.DTO;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.moviecatalog.models.enums.Genre;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 60)
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Genre genre;

    @NotNull
    @Min(0)
    @Max(100)
    private int rate;

    @NotNull
    private String releaseDate;

}
