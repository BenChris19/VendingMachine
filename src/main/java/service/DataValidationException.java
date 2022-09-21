package service;

@SuppressWarnings("serial")
/**
 * Throw error if there is a problem with the data type in the vending machine text file.
 * @author benat
 *
 */
public class DataValidationException extends Exception {
	
	public DataValidationException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]); //Remove stack trace
	}
	public DataValidationException(String message, Throwable cause) {
		super(message, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}
}

