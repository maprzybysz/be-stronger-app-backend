package pl.maprzybysz.bestrongerapp.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(){
        super("Email already exists");
    }
}
