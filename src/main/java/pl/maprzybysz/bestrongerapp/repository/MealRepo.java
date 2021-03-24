package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.Meal;

import java.util.List;
import java.util.Optional;


@Repository
public interface MealRepo extends JpaRepository<Meal, Long> {
    Optional<Meal> findByName(String name);
    Optional<List<Meal>> findByNameContains(String name);
    Optional<List<Meal>> findTop10ByOrderByNameAsc();

}
