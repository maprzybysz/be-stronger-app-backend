package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.maprzybysz.bestrongerapp.Entity.Exercise;
import pl.maprzybysz.bestrongerapp.Entity.Training;
import pl.maprzybysz.bestrongerapp.service.TrainingService;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {


    private TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/exercises")
    public ResponseEntity<?> getExercises(){
        List<Exercise> exercises = trainingService.getExercises();
        return ResponseEntity.ok(exercises);
    }
    @GetMapping("/exercises/{name}")
    public ResponseEntity<?> searchExercisesByName(@PathVariable String name){
        List<Exercise> exercises = trainingService.searchExercisesByNameContains(name);
        return ResponseEntity.ok(exercises);
    }
    @GetMapping("/trainings/{username}")
    public ResponseEntity<?> getUserTrainingsByUserName(@PathVariable String username){
        List<Training> trainings = trainingService.getUserTrainingsByUserName(username);
        return ResponseEntity.ok(trainings);
    }
    @PostMapping("/save/{username}")
    public ResponseEntity<?> saveTrainingByUsername(@RequestBody Training training, @PathVariable String username){
        trainingService.saveTraining(training, username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTrainingById(@PathVariable Long id){
        try{
            trainingService.deleteTrainingById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
