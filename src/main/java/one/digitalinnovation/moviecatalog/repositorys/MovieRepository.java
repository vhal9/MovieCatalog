package one.digitalinnovation.moviecatalog.repositorys;

import one.digitalinnovation.moviecatalog.models.entitys.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
