package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.Training;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingRepo extends JpaRepository<Training, Long> {
   Optional<List<Training>> getTrainingByAppUserId(Long id);
}
