package pl.maprzybysz.bestrongerapp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Test spring-security authenticated
@RestController
@RequestMapping("/test")
public class CustomController {

    @GetMapping("/test")
    public String get(){
        return "test123";
    }
}
