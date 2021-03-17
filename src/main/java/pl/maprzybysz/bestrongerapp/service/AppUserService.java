package pl.maprzybysz.bestrongerapp.service;


import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.DTO.SignUpDTO;
import pl.maprzybysz.bestrongerapp.Entity.ShoppingListElement;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AppUserService{
    public void addNewUser(SignUpDTO signUpDTO, HttpServletRequest request);
    public void verifyToken(String token);
    public void sendRecoveryToken(String username);
    public void removeToken(String token);
    public void restartPassword(String token, String password, String confirmPassword);
    public void removeRecoveryToken(String token);
    public void sendDeleteToken(String username,  HttpServletRequest request);
    public void deleteAccount(String token);
    public void removeDeleteToken(String token);
}
