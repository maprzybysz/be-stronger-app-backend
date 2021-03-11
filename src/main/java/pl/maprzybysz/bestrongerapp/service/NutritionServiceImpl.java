package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;
import pl.maprzybysz.bestrongerapp.Entity.Mapper.MealMapper;
import pl.maprzybysz.bestrongerapp.exception.MealDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.Entity.*;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.EatenMealRepo;
import pl.maprzybysz.bestrongerapp.repository.MealRepo;
import pl.maprzybysz.bestrongerapp.repository.ShoppingListElementRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NutritionServiceImpl implements NutritionService {

    private MealRepo mealRepo;
    private AppUserRepo appUserRepo;
    private EatenMealRepo eatenMealRepo;
    private ShoppingListElementRepo shoppingListElementRepo;

    @Autowired
    public NutritionServiceImpl(MealRepo mealRepo, AppUserRepo appUserRepo, EatenMealRepo eatenMealRepo,
                                ShoppingListElementRepo shoppingListElementRepo) {
        this.mealRepo = mealRepo;
        this.appUserRepo = appUserRepo;
        this.eatenMealRepo = eatenMealRepo;
        this.shoppingListElementRepo = shoppingListElementRepo;
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
    public MealDTO getMealDTOByName(String name) {
        Optional<Meal> findMeal = mealRepo.findByName(name);
        if (findMeal.isEmpty()) {
            throw new MealDoesNotExistsException(name);
        } else {
            return MealMapper.mealToMealDTO().map(findMeal.get(), MealDTO.class);
        }
    }
    @Override
    public List<MealDTO> searchMealByNameContains(String name) {
        Optional<List<Meal>> findMeals = mealRepo.findByNameContains(name);
        List<MealDTO> mealDTOS = new ArrayList<>();
        if(findMeals.isEmpty()){
           return List.of();
        }else{
            for (Meal meal: findMeals.get()){
                mealDTOS.add(MealMapper.mealToMealDTO().map(meal, MealDTO.class));
            }
            return mealDTOS;
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
    @Override
    public List<ShoppingListElement> getShoppingList(String username) {
        Optional<AppUser> appUser = appUserRepo.findByUsername(username);
        if(appUser.isPresent()){
            return appUser.get().getShoppingList();
        }else{
            return List.of(null);
        }
    }

    @Override
    public void deleteShoppingListElement(Long id) {
        shoppingListElementRepo.deleteById(id);
    }

    @Override
    public void addShoppingListElement(String username, String listItem) {
        Optional<AppUser> user = appUserRepo.findByUsername(username);
        if(user.isPresent()){
            ShoppingListElement shoppingListElement = new ShoppingListElement();
            shoppingListElement.setListElement(listItem);
            shoppingListElement.setAppUser(user.get());
            shoppingListElementRepo.save(shoppingListElement);
        }else{
            throw new UserDoesNotExistsException(username);
        }
    }

}
