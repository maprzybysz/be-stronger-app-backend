package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity

public class Meal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    @NotNull
    @Column(nullable = false)
    private int grammage;
    @NotNull
    @Column(nullable = false)
    private int goodness;
    @NotNull
    @Column(nullable = false)
    private int protein;
    @NotNull
    @Column(nullable = false)
    private int carbohydrates;
    @NotNull
    @Column(nullable = false)
    private int fat;
    @NotNull
    @ManyToOne
    @JoinColumn(name="app_user_id")
    @JsonIgnore
    private AppUser appUser;
    @OneToOne(fetch = FetchType.EAGER)
    private MealDetails mealDetails;

    public Meal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrammage() {
        return grammage;
    }

    public void setGrammage(int grammage) {
        this.grammage = grammage;
    }

    public int getGoodness() {
        return goodness;
    }

    public void setGoodness(int goodness) {
        this.goodness = goodness;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public MealDetails getMealDetails() {
        return mealDetails;
    }

    public void setMealDetails(MealDetails mealDetails) {
        this.mealDetails = mealDetails;
    }


    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grammage=" + grammage +
                ", goodness=" + goodness +
                ", protein=" + protein +
                ", carbohydrates=" + carbohydrates +
                ", fat=" + fat +
                ", mealDetails=" + mealDetails +
                '}';
    }
}
