package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.Entity.Exercise;
import pl.maprzybysz.bestrongerapp.repository.ExerciseRepo;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService{

    private ExerciseRepo exerciseRepo;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepo exerciseRepo) {
        this.exerciseRepo = exerciseRepo;
    }

    @Override
    public List<Exercise> getExercises() {
       List<Exercise> exercises = exerciseRepo.findAll();
        return exercises;
    }

    @Override
    public List<Exercise> searchExercisesByNameContains(String name) {
        Optional<List<Exercise>> exercises = exerciseRepo.findAllByNameContains(name);
        return exercises.get();
    }
}
