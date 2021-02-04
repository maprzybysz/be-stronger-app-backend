package pl.maprzybysz.bestrongerapp.model;

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
    private String description;
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

    public Meal() {
    }

    public Meal(String name, String description, int grammage, int goodness, int protein, int carbohydrates, int fat) {
        this.name = name;
        this.description = description;
        this.grammage = grammage;
        this.goodness = goodness;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", grammage=" + grammage +
                ", goodness=" + goodness +
                ", protein=" + protein +
                ", carbohydrates=" + carbohydrates +
                ", fat=" + fat +
                '}';
    }
}
