package pl.maprzybysz.bestrongerapp.service;

import pl.maprzybysz.bestrongerapp.model.EatenMeal;
import pl.maprzybysz.bestrongerapp.model.Meal;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MealService {
    public void addMeal(Meal meal);
    public Meal getMealByName(String name);
    public List<Meal> searchMealByNameContains(String name);
    public void saveEatenMeal(EatenMeal eatenMeal);
    public List<EatenMeal> getEatenMealsByUsername(String username);
    public List<EatenMeal> getEatenMealsByUsernameAndMealDate(String username, LocalDate date);
    public List<EatenMeal> getEatenMealsByUsernameAndMealDateAndMealTime(String username, LocalDate date,
                                                                          String mealTime);

}
