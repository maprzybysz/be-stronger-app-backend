package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Training {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String trainingName;
    private LocalDate endTime;
    @OneToMany(mappedBy = "training", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<CompletedExercise> exercises;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public List<CompletedExercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<CompletedExercise> exercises) {
        this.exercises = exercises;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }


    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", trainingName='" + trainingName + '\'' +
                ", endTime=" + endTime +
                ", exercises=" + exercises +
                '}';
    }
}
