package pl.maprzybysz.bestrongerapp.repository;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.maprzybysz.bestrongerapp.model.EatenMeal;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EatenMealRepo extends JpaRepository<EatenMeal, Long> {
    Optional<List<EatenMeal>> findEatenMealsByUsername(String username);
    Optional<List<EatenMeal>> findEatenMealsByUsernameAndMealDate(String username, LocalDate date);
    Optional<List<EatenMeal>> findEatenMealsByUsernameAndMealDateAndMealTime(String username, LocalDate date,
                                                                             String mealTime);
}
