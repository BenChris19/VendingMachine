package service;

/**
 * Throw error if the user hasn't got enough money to buy an item
 * @author benat
 *
 */
@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
	
	public InsufficientFundsException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]);	//Remove stack trace
	}
	public InsufficientFundsException(String message, Throwable cause) {
		super(message, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}

}
