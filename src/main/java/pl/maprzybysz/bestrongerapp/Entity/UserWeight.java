package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class UserWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double weight;
    private LocalDate dateAdded;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="app_user_details_id")
    private AppUserDetails appUserDetails;

    public UserWeight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
        return "UserWeight{" +
                "id=" + id +
                ", weight=" + weight +
                ", dateAdded=" + dateAdded +
                ", appUserDetails=" + appUserDetails +
                '}';
    }


}
