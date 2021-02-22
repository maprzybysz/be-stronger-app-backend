package pl.maprzybysz.bestrongerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.EatenMeal;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EatenMealRepo extends JpaRepository<EatenMeal, Long> {
    Optional<List<EatenMeal>> findEatenMealsByAppUser(AppUser appUser);
    Optional<List<EatenMeal>> findEatenMealsByAppUserAndMealDate(AppUser appUser, LocalDate date);
    Optional<List<EatenMeal>> findEatenMealsByAppUserAndMealDateAndMealTime(AppUser appUser, LocalDate date,
                                                                             String mealTime);
}
