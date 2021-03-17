package pl.maprzybysz.bestrongerapp.Entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class MealDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(max = 400)
    private String description;
    private String imgUrl;
    private LocalDate createDate;

    public MealDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "MealDetails{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
