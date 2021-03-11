package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.Entity.DTO.PasswordRecoveryDTO;
import pl.maprzybysz.bestrongerapp.Entity.DTO.SignUpDTO;
import pl.maprzybysz.bestrongerapp.exception.EmailAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.exception.UserAlreadyExistsException;
import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.LoginCredentials;
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
    public ResponseEntity<?> saveUser(@RequestBody SignUpDTO signUpDTO, HttpServletRequest httpServletRequest) {
        try{
            System.out.println(signUpDTO);
            appUserService.addNewUser(signUpDTO, httpServletRequest);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (UserAlreadyExistsException | EmailAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/verify-token/{token}")
    public void verifyUser(@PathVariable String token, HttpServletResponse response) throws IOException {
        appUserService.verifyToken(token);
        appUserService.removeToken(token);
        response.sendRedirect("http://localhost:3000/login");
    }
    @GetMapping("/send-recovery/{username}")
    public void getRecoveryToken(@PathVariable String username) throws IOException {
        appUserService.sendRecoveryToken(username);

    }
    @PostMapping("/restart-password")
    public void restartPassword(@RequestBody PasswordRecoveryDTO passwordRecoveryDTO, HttpServletResponse response) throws IOException {
        appUserService.restartPassword(passwordRecoveryDTO.getToken(), passwordRecoveryDTO.getPassword(), passwordRecoveryDTO.getConfirmPassword());
        response.sendRedirect("http://localhost:3000/login");
    }


    //method provide with spring security
    @PostMapping("/login")
    public void fakeLogin(@RequestBody LoginCredentials credentials){}
}