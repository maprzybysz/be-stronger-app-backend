package pl.maprzybysz.bestrongerapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class EatenMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(nullable = false)
    private String mealName;
    @NotNull
    @Column(name = "meal_time", nullable = false)
    @Enumerated(EnumType.STRING)
    private MealTime mealTime;
    @Column(nullable = false)
    private LocalDate mealDate;
    @NotNull
    @Column(nullable = false)
    private int totalGrammage;
    @NotNull
    @Column(nullable = false)
    private int totalGoodness;
    @NotNull
    @Column(nullable = false)
    private int totalProtein;
    @NotNull
    @Column(nullable = false)
    private int totalCarbohydrates;
    @NotNull
    @Column(nullable = false)
    private int totalFat;
    @ManyToOne
    @JoinColumn(name="app_user_id")
    @JsonIgnore
    private AppUser appUser;

    public EatenMeal() {
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MealTime getMealTime() {
        return mealTime;
    }

    public void setMealTime(MealTime mealTime) {
        this.mealTime = mealTime;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public int getTotalGrammage() {
        return totalGrammage;
    }

    public void setTotalGrammage(int totalGrammage) {
        this.totalGrammage = totalGrammage;
    }

    public int getTotalGoodness() {
        return totalGoodness;
    }

    public void setTotalGoodness(int totalGoodness) {
        this.totalGoodness = totalGoodness;
    }

    public int getTotalProtein() {
        return totalProtein;
    }

    public void setTotalProtein(int totalProtein) {
        this.totalProtein = totalProtein;
    }

    public int getTotalCarbohydrates() {
        return totalCarbohydrates;
    }

    public void setTotalCarbohydrates(int totalCrbohydrates) {
        this.totalCarbohydrates = totalCrbohydrates;
    }

    public int getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(int totalFat) {
        this.totalFat = totalFat;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "EatenMeal{" +
                "id=" + id +
                ", mealName='" + mealName + '\'' +
                ", mealTime='" + mealTime + '\'' +
                ", mealDate=" + mealDate +
                ", totalGrammage=" + totalGrammage +
                ", totalGoodness=" + totalGoodness +
                ", totalProtein=" + totalProtein +
                ", totalCarbohydrates=" + totalCarbohydrates +
                ", totalFat=" + totalFat;
    }
}
