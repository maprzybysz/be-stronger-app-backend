package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class CompletedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exerciseName;
    @OneToMany(mappedBy = "completedExercise", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Series> series;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="training_id")
    private Training training;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }


    @Override
    public String toString() {
        return "CompletedExercise{" +
                "id=" + id +
                ", exerciseName='" + exerciseName + '\'' +
                ", series=" + series +
                '}';
    }
}
