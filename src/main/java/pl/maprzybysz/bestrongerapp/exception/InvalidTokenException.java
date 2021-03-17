package pl.maprzybysz.bestrongerapp.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
        super("Token is invalid, try again");
    }
}
