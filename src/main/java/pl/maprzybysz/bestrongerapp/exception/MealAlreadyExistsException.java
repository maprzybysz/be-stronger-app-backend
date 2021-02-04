package pl.maprzybysz.bestrongerapp.exception;

public class MealAlreadyExistsException extends RuntimeException{
    public MealAlreadyExistsException() {
        super("Meal already exists");
    }
}
