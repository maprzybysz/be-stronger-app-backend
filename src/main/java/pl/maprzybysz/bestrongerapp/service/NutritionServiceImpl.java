package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.exception.MealDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.model.AppUser;
import pl.maprzybysz.bestrongerapp.model.EatenMeal;
import pl.maprzybysz.bestrongerapp.model.Meal;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.EatenMealRepo;
import pl.maprzybysz.bestrongerapp.repository.MealRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NutritionServiceImpl implements NutritionService {

    private MealRepo mealRepo;
    private AppUserRepo appUserRepo;
    private EatenMealRepo eatenMealRepo;

    @Autowired
    public NutritionServiceImpl(MealRepo mealRepo, AppUserRepo appUserRepo, EatenMealRepo eatenMealRepo) {
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
        if(findMeal.isEmpty()){
            throw new MealDoesNotExistsException(name);
        }else{
            return findMeal.get();
        }

    }

    @Override
    public List<Meal> searchMealByNameContains(String name) {
        Optional<List<Meal>> findMeals = mealRepo.findByNameContains(name);
        if(findMeals.isEmpty()){
           return List.of();
        }else{
            return findMeals.get();
        }
    }

    @Override
    public void saveEatenMeal(EatenMeal eatenMeal, String username) {
        Optional<AppUser> user = appUserRepo.findByUsername(username);
        if(user.isPresent()){
            eatenMeal.setAppUser(user.get());
        }else {
            throw new UserDoesNotExistsException(username);
        }
        eatenMealRepo.save(eatenMeal);
    }

    @Override
    public List<EatenMeal> getEatenMealsByUsername(String username) {
        Optional<AppUser> user = appUserRepo.findByUsername(username);
        if(user.isPresent()){
            Optional<List<EatenMeal>> eatenMeals = eatenMealRepo.findEatenMealsByAppUser(user.get());
            if (eatenMeals.isEmpty()) {
                return List.of();
            } else {
                return eatenMeals.get();
            }
        }else {
            throw new UserDoesNotExistsException(username);
        }

    }

    @Override
    public List<EatenMeal> getEatenMealsByUsernameAndMealDate(String username, LocalDate date) {
        Optional<AppUser> user = appUserRepo.findByUsername(username);
        if(user.isPresent()){
            Optional<List<EatenMeal>> eatenMeals = eatenMealRepo.findEatenMealsByAppUserAndMealDate(user.get(), date);
            if (eatenMeals.isEmpty()) {
                return List.of();
            } else {
                return eatenMeals.get();
            }
        }else {
            throw new UserDoesNotExistsException(username);
        }
    }

    @Override
    public List<EatenMeal> getEatenMealsByUsernameAndMealDateAndMealTime(String username, LocalDate date,
                                                                         String mealTime) {
        Optional<AppUser> user = appUserRepo.findByUsername(username);
        if(user.isPresent()){
            Optional<List<EatenMeal>> eatenMeals =
                    eatenMealRepo.findEatenMealsByAppUserAndMealDateAndMealTime(user.get(),
                    date, mealTime);
            if (eatenMeals.isEmpty()) {
                return List.of();
            } else {
                return eatenMeals.get();
            }
        }else {
            throw new UserDoesNotExistsException(username);
        }
    }
    @Override
    public void deleteMealById(Long id){
        eatenMealRepo.deleteById(id);
    }


}
