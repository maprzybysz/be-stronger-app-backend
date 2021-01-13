package pl.maprzybysz.bestrongerapp.service;

import pl.maprzybysz.bestrongerapp.model.AppUser;

import javax.servlet.http.HttpServletRequest;

public interface AppUserService {
    public void addNewUser(AppUser appUser, HttpServletRequest request);
    public void verifyToken(String token);
    public void removeToken(String token);
}
