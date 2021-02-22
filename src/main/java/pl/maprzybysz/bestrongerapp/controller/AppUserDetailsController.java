package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.Entity.DTO.AppUserDetailsDTO;
import pl.maprzybysz.bestrongerapp.Entity.ShoppingListElement;
import pl.maprzybysz.bestrongerapp.Entity.UserWeight;
import pl.maprzybysz.bestrongerapp.service.AppUserDetailsService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserDetailsController {

    private AppUserDetailsService appUserDetailsService;

    @Autowired
    public AppUserDetailsController(AppUserDetailsService appUserDetailsService) {
        this.appUserDetailsService = appUserDetailsService;
    }

    @GetMapping("/getUserDetails/{username}")
    public ResponseEntity<?> getUserDetails(@PathVariable String username){
        try{
            AppUserDetailsDTO appUserDetailsDTO = appUserDetailsService.getUserDetails(username);
            return ResponseEntity.status(HttpStatus.OK).body(appUserDetailsDTO);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/getUserWeights/{username}")
    public ResponseEntity<?> getUserWeights(@PathVariable String username){
        try{
            List<UserWeight> userWeights = appUserDetailsService.getUserWeights(username);
            return ResponseEntity.status(HttpStatus.OK).body(userWeights);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getUserWeight/{username}")
    public ResponseEntity<?> getUserWeight(@PathVariable String username){
        try{
            double weight = appUserDetailsService.getUserWeight(username);
            return ResponseEntity.status(HttpStatus.OK).body(weight);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addWeight/{username}")
    public ResponseEntity<?> addWeight(@RequestBody UserWeight userWeight, @PathVariable String username){
        try{
            appUserDetailsService.addWeight(userWeight, username);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
             return ResponseEntity.notFound().build();
        }

    }
}
