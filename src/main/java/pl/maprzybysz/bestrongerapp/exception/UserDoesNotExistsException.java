package pl.maprzybysz.bestrongerapp.exception;

public class UserDoesNotExistsException extends RuntimeException {
	public UserDoesNotExistsException() {
		super("User does not exist");
	}
}
