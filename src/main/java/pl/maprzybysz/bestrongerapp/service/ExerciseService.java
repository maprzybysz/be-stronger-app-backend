package pl.maprzybysz.bestrongerapp.service;


import pl.maprzybysz.bestrongerapp.Entity.Exercise;

import java.util.List;

public interface ExerciseService {
    public List<Exercise> getExercises();
    public List<Exercise> searchExercisesByNameContains(String name);
}
