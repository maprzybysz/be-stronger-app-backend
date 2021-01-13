package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.model.AppUser;
import pl.maprzybysz.bestrongerapp.service.AppUserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody AppUser appUser, HttpServletRequest httpServletRequest) {
        appUserService.addNewUser(appUser, httpServletRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/verify-token/{token}")
    public ResponseEntity<?> verifyUser(@PathVariable String token) {
        appUserService.verifyToken(token);
        appUserService.removeToken(token);
        return ResponseEntity.ok().build();
    }
}