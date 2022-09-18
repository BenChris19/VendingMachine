package service;

@SuppressWarnings("serial")
public class DataValidationException extends Exception {
	
	public DataValidationException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]);
	}
	public DataValidationException(String message, Throwable cause) {
		super(message, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}
}

