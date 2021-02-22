package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.Entity.AppUserDetails;
import pl.maprzybysz.bestrongerapp.Entity.DTO.AppUserDetailsDTO;
import pl.maprzybysz.bestrongerapp.Entity.DTO.MealDTO;
import pl.maprzybysz.bestrongerapp.Entity.Mapper.AppUserDetailsMapper;
import pl.maprzybysz.bestrongerapp.Entity.Mapper.MealMapper;
import pl.maprzybysz.bestrongerapp.Entity.Meal;
import pl.maprzybysz.bestrongerapp.exception.UserDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.UserWeight;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.UserWeightRepo;

import java.util.*;

@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService{

    private AppUserRepo appUserRepo;
    private UserWeightRepo userWeightRepo;

    @Autowired
    public AppUserDetailsServiceImpl(AppUserRepo appUserRepo, UserWeightRepo userWeightRepo) {
        this.appUserRepo = appUserRepo;
        this.userWeightRepo = userWeightRepo;
    }

    @Override
    public AppUserDetailsDTO getUserDetails(String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            System.out.println(AppUserDetailsMapper.detailsToDetailsDTO().map(findUser.get().getUserDetails(),
                    AppUserDetailsDTO.class));
            return AppUserDetailsMapper.detailsToDetailsDTO().map(findUser.get().getUserDetails(),
                    AppUserDetailsDTO.class);
        }else{
            throw new UserDoesNotExistsException(username);
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
            throw new UserDoesNotExistsException(username);
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
            throw new UserDoesNotExistsException(username);
        }
    }

    @Override
    public void addWeight(UserWeight userWeight, String username) {
        Optional<AppUser> findUser = appUserRepo.findByUsername(username);
        if(findUser.isPresent()){
            userWeight.setAppUserDetails(findUser.get().getUserDetails());
            userWeightRepo.save(userWeight);
        }else{
            throw new UserDoesNotExistsException(username);
        }
    }
}
