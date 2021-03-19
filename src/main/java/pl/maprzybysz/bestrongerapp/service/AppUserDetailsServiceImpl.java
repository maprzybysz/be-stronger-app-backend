package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.Entity.*;
import pl.maprzybysz.bestrongerapp.Entity.DTO.AppUserDetailsDTO;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;
import pl.maprzybysz.bestrongerapp.Entity.Mapper.AppUserDetailsMapper;
import pl.maprzybysz.bestrongerapp.Entity.Mapper.MealMapper;
import pl.maprzybysz.bestrongerapp.exception.UserDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.UserTMRRepo;
import pl.maprzybysz.bestrongerapp.repository.UserWeightRepo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService{

    private AppUserRepo appUserRepo;
    private UserWeightRepo userWeightRepo;
    private UserTMRRepo userTMRRepo;

    @Autowired
    public AppUserDetailsServiceImpl(AppUserRepo appUserRepo, UserWeightRepo userWeightRepo, UserTMRRepo userTMRRepo) {
        this.appUserRepo = appUserRepo;
        this.userWeightRepo = userWeightRepo;
        this.userTMRRepo = userTMRRepo;
    }

    @Override
    public AppUserDetailsDTO getUserDetails(String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            return AppUserDetailsMapper.detailsToDetailsDTO().map(findUser.get().getUserDetails(),
                    AppUserDetailsDTO.class);
        }else{
            throw new UserDoesNotExistsException();
        }
    }

    @Override
    public List<UserWeight> getUserWeights(String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            List<UserWeight> weights = findUser.get().getUserDetails().getWeights();
            Collections.sort(weights, new Comparator<UserWeight>(){
                @Override
                public int compare(UserWeight o1, UserWeight o2){
                    return o1.getDateAdded().compareTo(o2.getDateAdded());
                }
            });
            return weights;
        }else{
            throw new UserDoesNotExistsException();
        }
    }
    @Override
    public double getUserWeight(String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            List<UserWeight> weights = findUser.get().getUserDetails().getWeights();
            Collections.sort(weights, new Comparator<UserWeight>(){
                @Override
                public int compare(UserWeight o1, UserWeight o2){
                    return o1.getDateAdded().compareTo(o2.getDateAdded());
                }
            });
            return weights.get(weights.size()-1).getWeight();
        }else{
            throw new UserDoesNotExistsException();
        }
    }

    @Override
    public void addWeight(UserWeight userWeight, String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            AppUserDetails details = findUser.get().getUserDetails();
            Optional<UserWeight> findWeight =
                    userWeightRepo.findUserWeightByAppUserDetailsIdAndDateAdded(details.getId(), userWeight.getDateAdded());
            userWeight.setAppUserDetails(details);
            if(findWeight.isEmpty()){
                userWeightRepo.save(userWeight);
            }else{
                findWeight.get().setWeight(userWeight.getWeight());
                userWeightRepo.save(findWeight.get());
            }
            addTMRbyUsername(username, userWeight.getDateAdded());
        }else{
            throw new UserDoesNotExistsException();
        }
    }

    @Override
    public void updateUserActivity(String username, String userActivity) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            UserActivity activity = UserActivity.valueOf(userActivity);
            findUser.get().getUserDetails().setUserActivity(activity);
            appUserRepo.save(findUser.get());
            addTMRbyUsername(username, null);
        }else{
            throw new UserDoesNotExistsException();
        }
    }

    @Override
    public void updateUserHeight(String username, double userHeight) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            findUser.get().getUserDetails().setHeight(userHeight);
            appUserRepo.save(findUser.get());
            addTMRbyUsername(username, null);
        }else{
            throw new UserDoesNotExistsException();
        }
    }
    @Override
    public void updateUserGoal(String username, String userGoal) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            UserGoal goal = UserGoal.valueOf(userGoal);
            findUser.get().getUserDetails().setUserGoal(goal);
            appUserRepo.save(findUser.get());
            addTMRbyUsername(username, null);
        }else{
            throw new UserDoesNotExistsException();
        }
    }

    @Override
    public void addTMRbyUsername(String username, LocalDate date) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            AppUserDetails details = findUser.get().getUserDetails();
            saveTMR(details, date);
           }else{
            throw new UserDoesNotExistsException();
        }
    }

    @Override
    public void addTMRbyUser(AppUser appUser, LocalDate date) {
        AppUserDetails details = appUser.getUserDetails();
        saveTMR(details, date);
    }

    @Override
    public List<UserTMR> getUserTMRs(String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
           return findUser.get().getUserDetails().getTmrs();
        }else{
            throw new UserDoesNotExistsException();
        }
    }

    @Override
    public UserTMR getUserTMR(String date, String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        LocalDate createDate = LocalDate.parse(date);
        List<UserTMR> tmrs;
        if(findUser.isPresent()){
            tmrs = userTMRRepo.findUserTMRByDateAddedIsLessThanEqualAndAppUserDetailsId(createDate,
                    findUser.get().getUserDetails().getId());
            if(tmrs.size()>0){
                Collections.sort(tmrs, new Comparator<UserTMR>(){
                    @Override
                    public int compare(UserTMR o1, UserTMR o2){
                        return o1.getDateAdded().compareTo(o2.getDateAdded());
                    }
                });
                return tmrs.get(tmrs.size()-1);
            }else {
                tmrs = userTMRRepo.findUserTMRByDateAddedIsGreaterThanAndAppUserDetailsId(createDate,
                        findUser.get().getUserDetails().getId());
                Collections.sort(tmrs, new Comparator<UserTMR>(){
                    @Override
                    public int compare(UserTMR o1, UserTMR o2){
                        return o1.getDateAdded().compareTo(o2.getDateAdded());
                    }
                });
                return tmrs.get(0);
            }

        }else{
            throw new UserDoesNotExistsException();
        }
    }
    private void saveTMR(AppUserDetails details, LocalDate date){
        UserTMR newTMR = getTMR(details, date);
        Optional<UserTMR> findTMR = userTMRRepo.findUserTMRByAppUserDetailsIdAndDateAdded(details.getId(),
                newTMR.getDateAdded());
        if(findTMR.isPresent()){
            newTMR.setId(findTMR.get().getId());
            userTMRRepo.save(newTMR);
        }else{
            userTMRRepo.save(newTMR);
        }
    }
    private UserTMR getTMR(AppUserDetails details, LocalDate date){
        long userAge = calculateAge(details.getBirthday());
        double BMR = calculateBMR(details.getLastWeight(), details.getHeight(), userAge, details.getSex());
        double TMR = calculateTMR(BMR, details.getUserActivity(), details.getUserGoal());
        double protein = calculateProtein(details.getLastWeight());
        double fat = calculateFat(TMR);
        double carbohydrates = calculateCarbohydrates(TMR, protein, fat);
        UserTMR userTMR = new UserTMR();
        userTMR.setTmr(TMR);
        userTMR.setProtein(protein);
        userTMR.setFat(fat);
        userTMR.setCarbohydrates(carbohydrates);
        if(date==null){
            userTMR.setDateAdded(LocalDate.now());
        }else{
            userTMR.setDateAdded(date);
        }
        userTMR.setAppUserDetails(details);
        return userTMR;
    }

    private double calculateBMR(double weight, double height, long userAge, String sex){
        if(sex.equals("Mężczyzna")){
            return (66 + (13.7*weight) + (5*height) - (6.76*userAge));
        }else{
            return (655 + (9.6*weight) + (1.8*height) - (4.7*userAge));
        }

    }

    private double calculateTMR(double BMR, UserActivity userActivity, UserGoal userGoal){
        double TMR = 0;
        switch(userActivity){
            case ZNIKOMA:
                TMR = BMR*1.25;
                break;
            case MAŁA:
                TMR = BMR*1.45;
                break;
            case UMIARKOWANA:
                TMR = BMR*1.65;
                break;
            case DUŻA:
                TMR = BMR*1.85;
                break;
        }
        switch (userGoal){
            case REDUKCJA:
                TMR = TMR-300;
                break;
            case UTRZYMANIE:
                break;
            case MASA:
                TMR = TMR+300;
                break;
        }
        return Math.round(TMR * 100.0) / 100.0;
    }
    private long calculateAge(LocalDate dateOfBirth){
        return ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
    }
    private double calculateProtein(double weight){
        return Math.round((weight*1.9) * 100.0) / 100.0;
    }
    private double calculateFat(double TMR){
        return Math.round((((TMR*0.25)/9)*1.9) * 100.0) / 100.0;
    }
    private double calculateCarbohydrates(double TMR, double protein, double fat){
        double proteinKcal = protein*4;
        double fatKcal = fat*9;
        return Math.round((((TMR-proteinKcal-fatKcal)/4) * 100.0) / 100.0);
    }


}
