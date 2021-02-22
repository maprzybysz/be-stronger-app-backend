package pl.maprzybysz.bestrongerapp.service;

import pl.maprzybysz.bestrongerapp.Entity.EatenMeal;
import pl.maprzybysz.bestrongerapp.Entity.Meal;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;

import java.time.LocalDate;
import java.util.List;

public interface NutritionService {
    public void addMeal(Meal meal);
    public Meal getMealByName(String name);
    public MealDTO getMealDTOByName(String name);
    public List<MealDTO> searchMealByNameContains(String name);
    public void saveEatenMeal(EatenMeal eatenMeal, String username);
    public List<EatenMeal> getEatenMealsByUsername(String username);
    public List<EatenMeal> getEatenMealsByUsernameAndMealDate(String username, LocalDate date);
    public List<EatenMeal> getEatenMealsByUsernameAndMealDateAndMealTime(String username, LocalDate date,
                                                                          String mealTime);
    public void deleteMealById(Long id);


}
