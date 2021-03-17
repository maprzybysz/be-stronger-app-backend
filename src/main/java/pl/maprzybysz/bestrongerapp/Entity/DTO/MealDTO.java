package pl.maprzybysz.bestrongerapp.Entity.DTO;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class MealDTO {
    private Long id;
    @Size(min=3, max = 50)
    @NotEmpty
    private String name;
    @NotEmpty
    private double grammage;
    @NotEmpty
    private double goodness;
    @NotEmpty
    private double protein;
    @NotEmpty
    private double carbohydrates;
    @NotEmpty
    private double fat;
    @Size(max=400)
    private String description;
    private String imgUrl;
    private LocalDate createDate;
    @NotEmpty
    private String author;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
