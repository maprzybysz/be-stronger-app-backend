package pl.maprzybysz.bestrongerapp.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.exception.MealDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.model.EatenMeal;
import pl.maprzybysz.bestrongerapp.model.Meal;
import pl.maprzybysz.bestrongerapp.service.MealService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/meal")
public class MealController {

    private MealService mealService;

    @Autowired
    public MealController(MealService mealService) {
        this.mealService = mealService;
    }

    @PostMapping("/addMeal")
    public void addMeal(@RequestBody Meal meal) {
        mealService.addMeal(meal);
    }

    @GetMapping("/getMealByName/{name}")
    public ResponseEntity<?> getMealByName(@PathVariable String name) {
        try{
            Meal meal = mealService.getMealByName(name);
            return ResponseEntity.ok(meal);
        }catch (MealDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchMeals/{name}")
    public ResponseEntity<?> getMealsContainsName(@PathVariable String name) {
        List<Meal> meals = mealService.searchMealByNameContains(name);
        return ResponseEntity.ok(meals);
    }

    @PostMapping("/saveEatenMeal")
    public ResponseEntity<?> saveEatenMeal(@RequestBody EatenMeal eatenMeal) {
        System.out.println(eatenMeal);
        mealService.saveEatenMeal(eatenMeal);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping("/getEatenMealsByUsername/{username}")
    public ResponseEntity<?> getEatenMealsByUsername(@PathVariable String username) {
        List<EatenMeal> eatenMeals = mealService.getEatenMealsByUsername(username);
        return ResponseEntity.ok(eatenMeals);
    }
    @GetMapping("/getEatenMealsByMealDate/{username}/{date}")
    public ResponseEntity<?> getEatenMealsByMealDate(@PathVariable String username, @PathVariable String date){
        LocalDate localDate = LocalDate.parse(date);
        try{
            List<EatenMeal> eatenMeals = mealService.getEatenMealsByUsernameAndMealDate(username, localDate);
            return ResponseEntity.ok(eatenMeals);
        }catch (TokenExpiredException e){
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @CrossOrigin
    @DeleteMapping("/deleteEatenMealById/{id}")
    public ResponseEntity<?> deleteEatenMealById(@PathVariable Long id){
        try{
            mealService.deleteMealById(id);
            return ResponseEntity.status(HttpStatus.OK).body("eatenMeal delete");
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }

    }

}
