package pl.maprzybysz.bestrongerapp.service;

import org.apache.catalina.User;
import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.DTO.AppUserDetailsDTO;
import pl.maprzybysz.bestrongerapp.Entity.UserTMR;
import pl.maprzybysz.bestrongerapp.Entity.UserWeight;

import java.time.LocalDate;
import java.util.List;


public interface AppUserDetailsService {
    public AppUserDetailsDTO getUserDetails(String username);
    public List<UserWeight> getUserWeights(String username);
    public double getUserWeight(String username);
    public void addWeight(UserWeight userWeight, String username);
    public void updateUserActivity(String username, String userActivity);
    public void updateUserHeight(String username, double userHeight);
    public void updateUserGoal(String username, String userGoal);
    public void addTMRbyUsername(String username);
    public void addTMRbyUser(AppUser appUser);
    public List<UserTMR> getUserTMRs(String username);
    public UserTMR getUserTMR(String date, String username);
}
