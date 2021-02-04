package pl.maprzybysz.bestrongerapp.exception;

public class UserDoesNotExistsException extends RuntimeException {
	public UserDoesNotExistsException(String username) {
		super("User with username: " + username + " does not exist");
	}
}
