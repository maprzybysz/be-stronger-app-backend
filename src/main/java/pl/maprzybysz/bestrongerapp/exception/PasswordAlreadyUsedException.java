package pl.maprzybysz.bestrongerapp.exception;

public class PasswordAlreadyUsedException extends RuntimeException{
    public PasswordAlreadyUsedException(){
        super("Password already used");
    }
}
