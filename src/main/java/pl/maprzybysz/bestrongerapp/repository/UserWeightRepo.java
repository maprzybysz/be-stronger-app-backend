package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maprzybysz.bestrongerapp.Entity.UserWeight;

public interface UserWeightRepo extends JpaRepository<UserWeight, Long> {
}
