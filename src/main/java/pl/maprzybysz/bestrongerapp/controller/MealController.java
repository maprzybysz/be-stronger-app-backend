package pl.maprzybysz.bestrongerapp.controller;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.model.EatenMeal;
import pl.maprzybysz.bestrongerapp.model.Meal;
import pl.maprzybysz.bestrongerapp.service.MealService;

import java.time.LocalDate;
import java.util.Date;
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

    @GetMapping("/getMeal/{name}")
    public ResponseEntity<Meal> getMeal(@PathVariable String name) {
        Meal meal = mealService.getMealByName(name);
        return ResponseEntity.ok(meal);
    }

    @GetMapping("/searchMeals/{name}")
    public ResponseEntity<?> getMealsContainsName(@PathVariable String name) {
        List<Meal> meals = mealService.searchMealByNameContains(name);
        return ResponseEntity.ok(meals);
    }

    @PostMapping("/saveEatenMeal/{username}")
    public ResponseEntity<?> saveEatenMeal(@RequestBody EatenMeal eatenMeal, @PathVariable String username) {
        mealService.saveEatenMeal(eatenMeal);
        return ResponseEntity.ok(HttpEntity.EMPTY);
    }

    @GetMapping("/getEatenMeal/{username}")
    public ResponseEntity<?> getEatenMeal(@PathVariable String username) {
        List<EatenMeal> eatenMeals = mealService.getEatenMealsByUsername(username);
        return ResponseEntity.ok(eatenMeals);
    }

    @GetMapping("/getEatenMealByMealTime/{username}/{date}/{mealTime}")
    public ResponseEntity<?> getEatenMealByMealTime(@PathVariable String username, @PathVariable String date,
                                                    @PathVariable String mealTime){
        LocalDate localDate = LocalDate.parse(date);
        List<EatenMeal> eatenMeals = mealService.getEatenMealsByUsernameAndMealDateAndMealTime(username, localDate,
                mealTime);
        return ResponseEntity.ok(eatenMeals);
    }

}
