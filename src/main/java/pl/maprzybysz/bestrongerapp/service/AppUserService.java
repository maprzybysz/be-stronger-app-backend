package pl.maprzybysz.bestrongerapp.service;


import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.ShoppingListElement;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AppUserService{
    public void addNewUser(AppUser appUser, HttpServletRequest request);
    public void verifyToken(String token);
    public void removeToken(String token);
    public List<ShoppingListElement> getShoppingList(String username);
    public void deleteShoppingListElement(Long id);
    public void addShoppingListElement(String username, String listItem);
}
