package pl.maprzybysz.bestrongerapp.service;

import org.apache.catalina.User;
import pl.maprzybysz.bestrongerapp.Entity.DTO.AppUserDetailsDTO;
import pl.maprzybysz.bestrongerapp.Entity.UserWeight;

import java.util.List;


public interface AppUserDetailsService {
    public AppUserDetailsDTO getUserDetails(String username);
    public List<UserWeight> getUserWeights(String username);
    public double getUserWeight(String username);
    public void addWeight(UserWeight userWeight, String username);
}
