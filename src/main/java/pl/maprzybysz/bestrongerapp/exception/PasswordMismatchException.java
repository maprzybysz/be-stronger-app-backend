package pl.maprzybysz.bestrongerapp.exception;

public class PasswordMismatchException extends RuntimeException {
	public PasswordMismatchException() {
		super("Password mismatch");
	}
}
