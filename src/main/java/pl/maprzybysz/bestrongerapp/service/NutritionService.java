package pl.maprzybysz.bestrongerapp.service;

import pl.maprzybysz.bestrongerapp.Entity.EatenMeal;
import pl.maprzybysz.bestrongerapp.Entity.Meal;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;
import pl.maprzybysz.bestrongerapp.Entity.ShoppingListElement;

import java.time.LocalDate;
import java.util.List;

public interface NutritionService {
    public void addMeal(MealDTO meal);
    public Meal getMealByName(String name);
    public MealDTO getMealDTOByName(String name);
    public List<MealDTO> searchMealByNameContains(String name);
    public void saveEatenMeal(EatenMeal eatenMeal, String username);
    public List<EatenMeal> getEatenMealsByUsername(String username);
    public List<EatenMeal> getEatenMealsByUsernameAndMealDate(String username, LocalDate date);
    public List<EatenMeal> getEatenMealsByUsernameAndMealDateAndMealTime(String username, LocalDate date,
                                                                          String mealTime);
    public void deleteMealById(Long id);
    public List<ShoppingListElement> getShoppingList(String username);
    public void deleteShoppingListElement(Long id);
    public void addShoppingListElement(String username, String listItem);


}
