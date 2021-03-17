package pl.maprzybysz.bestrongerapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.maprzybysz.bestrongerapp.Entity.*;
import pl.maprzybysz.bestrongerapp.exception.UserDoesNotExistsException;
import pl.maprzybysz.bestrongerapp.repository.AppUserRepo;
import pl.maprzybysz.bestrongerapp.repository.ExerciseRepo;
import pl.maprzybysz.bestrongerapp.repository.TrainingRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService{

    private AppUserRepo appUserRepo;
    private ExerciseRepo exerciseRepo;
    private TrainingRepo trainingRepo;

    @Autowired
    public TrainingServiceImpl(AppUserRepo appUserRepo, ExerciseRepo exerciseRepo, TrainingRepo trainingRepo) {
        this.appUserRepo = appUserRepo;
        this.exerciseRepo = exerciseRepo;
        this.trainingRepo = trainingRepo;
    }

    @Override
    public List<Exercise> getExercises() {
        List<Exercise> exercises = exerciseRepo.findAllByOrderByNameAsc().get();
        return exercises;
    }

    @Override
    public List<Exercise> searchExercisesByNameContains(String name) {
        Optional<List<Exercise>> exercises = exerciseRepo.findByNameContainsOrderByNameAsc(name);
        return exercises.get();
    }

    @Override
    public List<Training> getUserTrainingsByUserName(String username) {
        AppUser user = appUserRepo.findByUsername(username).get();
        return user.getTrainings();
    }

    @Override
    public void saveTraining(Training training, String username) {
        Optional<AppUser> user = appUserRepo.findByUsername(username);
        if(user.isPresent()){
            training.setAppUser(user.get());
            training.setExercises(training.getExercises());
            for (CompletedExercise completedExercise: training.getExercises()) {
                completedExercise.setTraining(training);
                for (Series series: completedExercise.getSeries()){
                    series.setCompletedExercise(completedExercise);
                }
            }
            training.setEndTime(LocalDate.now());
        }else {
            throw new UserDoesNotExistsException();
        }
        trainingRepo.save(training);
    }

    @Override
    public void deleteTrainingById(Long id) {
        trainingRepo.deleteById(id);
    }
}
