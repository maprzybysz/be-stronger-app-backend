package pl.maprzybysz.bestrongerapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maprzybysz.bestrongerapp.Entity.Exercise;
import pl.maprzybysz.bestrongerapp.service.ExerciseService;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {

    private ExerciseService exerciseService;

    @Autowired
    public TrainingController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/exercises")
    public ResponseEntity<?> getExercises(){
        List<Exercise> exercises = exerciseService.getExercises();
        return ResponseEntity.ok(exercises);
    }
    @GetMapping("/exercises/{name}")
    public ResponseEntity<?> searchExercisesByName(@PathVariable String name){
        List<Exercise> exercises = exerciseService.searchExercisesByNameContains(name);
        return ResponseEntity.ok(exercises);
    }
}
