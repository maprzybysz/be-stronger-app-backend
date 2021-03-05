package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

//TMR-Total metabolic Rate
@Entity
public class UserTMR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double tmr;
    private double protein;
    private double carbohydrates;
    private double fat;
    private LocalDate dateAdded;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="app_user_details_id")
    private AppUserDetails appUserDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTmr() {
        return tmr;
    }

    public void setTmr(double tmr) {
        this.tmr = tmr;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public AppUserDetails getAppUserDetails() {
        return appUserDetails;
    }

    public void setAppUserDetails(AppUserDetails appUserDetails) {
        this.appUserDetails = appUserDetails;
    }

    @Override
    public String toString() {
        return "UserTMR{" +
                "id=" + id +
                ", tmr=" + tmr +
                ", protein=" + protein +
                ", carbohydrates=" + carbohydrates +
                ", fat=" + fat +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
