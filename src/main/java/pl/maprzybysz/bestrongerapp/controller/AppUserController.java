package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.exception.EmailAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.model.AppUser;
import pl.maprzybysz.bestrongerapp.model.LoginCredentials;
import pl.maprzybysz.bestrongerapp.service.AppUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/sign-up")
    public ResponseEntity<?> saveUser(@RequestBody AppUser appUser, HttpServletRequest httpServletRequest) {
        try{
            appUserService.addNewUser(appUser, httpServletRequest);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (UserAlreadyExistsException | EmailAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/verify-token/{token}")
    public void verifyUser(@PathVariable String token, HttpServletResponse response) throws IOException {
        appUserService.verifyToken(token);
        appUserService.removeToken(token);
        response.sendRedirect("http://localhost:3000/login");
    }


    //method provide with spring security
    @PostMapping("/login")
    public void fakeLogin(@RequestBody LoginCredentials credentials){}
}