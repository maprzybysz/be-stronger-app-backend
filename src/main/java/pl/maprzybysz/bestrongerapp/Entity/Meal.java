package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;


@Entity
public class Meal {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(nullable = false, unique = true)
    @Length(min = 5, max = 50)
    private String name;
    @Column(nullable = false)
    private double grammage;
    @Column(nullable = false)
    private double goodness;
    @Column(nullable = false)
    private double protein;
    @Column(nullable = false)
    private double carbohydrates;
    @Column(nullable = false)
    private double fat;
    @ManyToOne
    @JoinColumn(name="app_user_id")
    @JsonIgnore
    private AppUser appUser;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public double getGrammage() {
        return grammage;
    }

    public void setGrammage(double grammage) {
        this.grammage = grammage;
    }

    public double getGoodness() {
        return goodness;
    }

    public void setGoodness(double goodness) {
        this.goodness = goodness;
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
