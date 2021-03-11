package pl.maprzybysz.bestrongerapp.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.exception.MealDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.Entity.EatenMeal;
import pl.maprzybysz.bestrongerapp.Entity.Meal;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;
import pl.maprzybysz.bestrongerapp.Entity.ShoppingListElement;
import pl.maprzybysz.bestrongerapp.service.AppUserService;
import pl.maprzybysz.bestrongerapp.service.NutritionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/meal")
public class NutritionController {

    private NutritionService nutritionService;
    private AppUserService appUserService;

    @Autowired
    public NutritionController(NutritionService nutritionService, AppUserService appUserService) {
        this.nutritionService = nutritionService;
        this.appUserService = appUserService;
    }

    @PostMapping("/addMeal")
    public void addMeal(@RequestBody Meal meal) {
        nutritionService.addMeal(meal);
    }

    @GetMapping("/getMealByName/{name}")
    public ResponseEntity<?> getMealDTOByName(@PathVariable String name) {
        try{
            MealDTO meal = nutritionService.getMealDTOByName(name);
            return ResponseEntity.ok(meal);
        }catch (MealDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/searchMeals/{name}")
    public ResponseEntity<?> getMealsContainsName(@PathVariable String name) {
        List<MealDTO> meals = nutritionService.searchMealByNameContains(name);
        return ResponseEntity.ok(meals);
    }

    @PostMapping("/saveEatenMeal/{username}")
    public ResponseEntity<?> saveEatenMeal(@RequestBody EatenMeal eatenMeal, @PathVariable String username) {
        try{
            nutritionService.saveEatenMeal(eatenMeal, username);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/getEatenMealsByUsername/{username}")
    public ResponseEntity<?> getEatenMealsByUsername(@PathVariable String username) {
        List<EatenMeal> eatenMeals = nutritionService.getEatenMealsByUsername(username);
        return ResponseEntity.ok(eatenMeals);
    }
    @GetMapping("/getEatenMealsByMealDate/{username}/{date}")
    public ResponseEntity<?> getEatenMealsByMealDate(@PathVariable String username, @PathVariable String date){
        LocalDate localDate = LocalDate.parse(date);
        try{
            List<EatenMeal> eatenMeals = nutritionService.getEatenMealsByUsernameAndMealDate(username, localDate);
            return ResponseEntity.ok(eatenMeals);
        }catch (TokenExpiredException e){
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @DeleteMapping("/deleteEatenMealById/{id}")
    public ResponseEntity<?> deleteEatenMealById(@PathVariable Long id){
        try{
            nutritionService.deleteMealById(id);
            return ResponseEntity.status(HttpStatus.OK).body("eatenMeal delete");
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getShoppingList/{username}")
    public ResponseEntity<?> getShoppingList(@PathVariable String username){
        try{
            List<ShoppingListElement> shoppingList = nutritionService.getShoppingList(username);
            return ResponseEntity.status(HttpStatus.OK).body(shoppingList);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/deleteShoppingListElement/{id}")
    public ResponseEntity<?> deleteShoppingListElement(@PathVariable Long id){
        try{
            nutritionService.deleteShoppingListElement(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/addShoppingListElement/{username}/{listItem}")
    public ResponseEntity<?> addShoppingListElement(@PathVariable String username, @PathVariable String listItem){
        try{
            nutritionService.addShoppingListElement(username, listItem);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }



}
