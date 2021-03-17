package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.Entity.DTO.PasswordRecoveryDTO;
import pl.maprzybysz.bestrongerapp.Entity.DTO.SignUpDTO;
import pl.maprzybysz.bestrongerapp.exception.*;
import pl.maprzybysz.bestrongerapp.Entity.AppUser;
import pl.maprzybysz.bestrongerapp.Entity.LoginCredentials;
import pl.maprzybysz.bestrongerapp.service.AppUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AppUserController {

    private AppUserService appUserService;
    @Value("${frontend.url}")
    private String serverUrl;

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
    public ResponseEntity<?> verifyUser(@PathVariable String token, HttpServletResponse response) throws IOException {
        try{
            appUserService.verifyToken(token);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", serverUrl+"/login");
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }catch (InvalidTokenException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/send-recovery/{username}")
    public ResponseEntity<?> getRecoveryToken(@PathVariable String username) throws IOException {
        try{
            appUserService.sendRecoveryToken(username);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (UserDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/restart-password")
    public ResponseEntity<?> restartPassword(@RequestBody PasswordRecoveryDTO passwordRecoveryDTO, HttpServletResponse response) throws IOException {
        try{
            appUserService.restartPassword(passwordRecoveryDTO.getToken(), passwordRecoveryDTO.getPassword(), passwordRecoveryDTO.getConfirmPassword());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (PasswordAlreadyUsedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }catch (PasswordMismatchException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (InvalidTokenException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/send-delete-token/{username}")
    public ResponseEntity<?> getDeleteToken(@PathVariable String username, HttpServletRequest httpServletRequest) throws IOException {
        try{
            appUserService.sendDeleteToken(username, httpServletRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (UserDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/delete-account/{token}")
    public ResponseEntity<?> deleteAccount(@PathVariable String token) throws IOException {
        try{
            appUserService.deleteAccount(token);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", serverUrl+"/login");
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        } catch (InvalidTokenException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    //method provide with spring security
    @PostMapping("/login")
    public void fakeLogin(@RequestBody LoginCredentials credentials){}
}