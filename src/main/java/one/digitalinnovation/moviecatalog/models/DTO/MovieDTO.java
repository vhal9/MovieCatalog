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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long id;

    @NotEmpty(message = "Name field is required")
    @Size(min = 2, max = 60, message = "Name field must be between 2 and 60")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Genre genre;

    @NotNull
    @Min(value = 0,message = "Rate field must greater than or equal to 0")
    @Max(value= 100, message = "Rate field less than or equal to 100")
    private int rate;

    @NotEmpty(message = "Release Date field is required")
    private String releaseDate;

}
