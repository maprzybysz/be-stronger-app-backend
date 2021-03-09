package pl.maprzybysz.bestrongerapp.service;

import pl.maprzybysz.bestrongerapp.Entity.Exercise;
import pl.maprzybysz.bestrongerapp.Entity.Training;

import java.util.List;

public interface TrainingService {
    public List<Exercise> getExercises();
    public List<Exercise> searchExercisesByNameContains(String name);
    public List<Training> getUserTrainingsByUserName(String username);
    public void saveTraining(Training training, String username);
}
