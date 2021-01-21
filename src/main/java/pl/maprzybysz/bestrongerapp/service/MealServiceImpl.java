package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.model.AppUser;
import pl.maprzybysz.bestrongerapp.model.EatenMeal;
import pl.maprzybysz.bestrongerapp.model.Meal;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.EatenMealRepo;
import pl.maprzybysz.bestrongerapp.repository.MealRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    private MealRepo mealRepo;
    private AppUserRepo appUserRepo;
    private EatenMealRepo eatenMealRepo;

    @Autowired
    public MealServiceImpl(MealRepo mealRepo, AppUserRepo appUserRepo, EatenMealRepo eatenMealRepo) {
        this.mealRepo = mealRepo;
        this.appUserRepo = appUserRepo;
        this.eatenMealRepo = eatenMealRepo;
    }

    @Override
    public void addMeal(Meal meal) {
        mealRepo.save(meal);
    }

    @Override
    public Meal getMealByName(String name) {
        Optional<Meal> findMeal = mealRepo.findByName(name);
        return findMeal.get();
    }

    @Override
    public List<Meal> searchMealByNameContains(String name) {
        Optional<List<Meal>> findMeals = mealRepo.findByNameContains(name);

        return findMeals.get();

    }

    @Override
    public void saveEatenMeal(EatenMeal eatenMeal) {

        eatenMeal.setMealDate(LocalDate.now());
        eatenMealRepo.save(eatenMeal);


    }

    @Override
    public List<EatenMeal> getEatenMealsByUsername(String username) {
        List<EatenMeal> findMeals = eatenMealRepo.findEatenMealsByUsername(username).get();
        return findMeals;
    }

    @Override
    public List<EatenMeal> getEatenMealsByUsernameAndMealDate(String username, Date date) {
        List<EatenMeal> findMeals = eatenMealRepo.findEatenMealsByUsernameAndMealDate(username, date).get();
        return findMeals;
    }

    @Override
    public List<EatenMeal> getEatenMealsByUsernameAndMealDateAndMealTime(String username, LocalDate date,
                                                                         String mealTime) {


        List<EatenMeal> findMeals = eatenMealRepo.findEatenMealsByUsernameAndMealDateAndMealTime(username, date, mealTime).get();
        return findMeals;
    }



}
