package pl.maprzybysz.bestrongerapp.Entity.DTO;

import pl.maprzybysz.bestrongerapp.Entity.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class SignUpDTO {
    private String username;
    private String password;
    private String email;
    private boolean rulesAccepted;
    private String sex;
    private LocalDate birthday;
    private String userActivity;
    private String userGoal;
    private double height;
    private double weight;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isRulesAccepted() {
        return rulesAccepted;
    }

    public void setRulesAccepted(boolean rulesAccepted) {
        this.rulesAccepted = rulesAccepted;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getUserActivity() {
        return userActivity;
    }

    public void setUserActivity(String userActivity) {
        this.userActivity = userActivity;
    }

    public String getUserGoal() {
        return userGoal;
    }

    public void setUserGoal(String userGoal) {
        this.userGoal = userGoal;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", rulesAccepted=" + rulesAccepted +
                ", birthday=" + birthday +
                ", userActivity='" + userActivity + '\'' +
                ", userGoal='" + userGoal + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
