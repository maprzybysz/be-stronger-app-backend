package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.Exercise;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepo extends JpaRepository<Exercise, Long > {

    Optional<List<Exercise>> findByNameContainsOrderByNameAsc(String name);
    Optional<List<Exercise>> findAllByOrderByNameAsc();

}
