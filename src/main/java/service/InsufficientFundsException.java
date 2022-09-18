package service;

@SuppressWarnings("serial")
public class InsufficientFundsException extends Exception {
	
	public InsufficientFundsException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]);
	}
	public InsufficientFundsException(String message, Throwable cause) {
		super(message, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}

}
