package pl.maprzybysz.bestrongerapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Series {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int repeatNumber;
    private double weight;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="completed_exercise_id")
    private CompletedExercise completedExercise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRepeatNumber() {
        return repeatNumber;
    }

    public void setRepeatNumber(int repeatNumber) {
        this.repeatNumber = repeatNumber;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public CompletedExercise getCompletedExercise() {
        return completedExercise;
    }

    public void setCompletedExercise(CompletedExercise completedExercise) {
        this.completedExercise = completedExercise;
    }
}
