package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maprzybysz.bestrongerapp.Entity.UserWeight;

import java.time.LocalDate;
import java.util.Optional;

public interface UserWeightRepo extends JpaRepository<UserWeight, Long> {

    Optional<UserWeight> findUserWeightByAppUserDetailsIdAndDateAdded(Long id, LocalDate date);
}
