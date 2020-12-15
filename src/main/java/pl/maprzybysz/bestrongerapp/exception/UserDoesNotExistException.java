package pl.maprzybysz.bestrongerapp.exception;

public class UserDoesNotExistException extends Exception {
	public UserDoesNotExistException(String username) {
		super("User with username: " + username + " does not exist");
	}
}
