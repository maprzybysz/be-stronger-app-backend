package pl.maprzybysz.bestrongerapp.exception;

public class MealDoesNotExistsException extends RuntimeException{
    public MealDoesNotExistsException(String mealName) {
        super("Meal with name: " + mealName + " does not exist");
    }
}
