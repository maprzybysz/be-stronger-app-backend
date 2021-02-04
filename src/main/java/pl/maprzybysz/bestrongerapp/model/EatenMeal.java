package pl.maprzybysz.bestrongerapp.model;

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
    @NotNull
    @Column(nullable = false)
    private String username;

    public EatenMeal() {
    }

    public EatenMeal(Long id, @NotNull String mealName, @NotNull MealTime mealTime, LocalDate mealDate, @NotNull int totalGrammage, @NotNull int totalGoodness, @NotNull int totalProtein, @NotNull int totalCarbohydrates, @NotNull int totalFat, @NotNull String username) {
        this.id = id;
        this.mealName = mealName;
        this.mealTime = mealTime;
        this.mealDate = mealDate;
        this.totalGrammage = totalGrammage;
        this.totalGoodness = totalGoodness;
        this.totalProtein = totalProtein;
        this.totalCarbohydrates = totalCarbohydrates;
        this.totalFat = totalFat;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                ", totalFat=" + totalFat +
                ", username='" + username + '\'' +
                '}';
    }
}
