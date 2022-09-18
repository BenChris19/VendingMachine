package service;

@SuppressWarnings("serial")
public class InvalidItemException extends Exception {

	public InvalidItemException(String msg) {
		super(msg);
		this.setStackTrace(new StackTraceElement[0]);
	}
	public InvalidItemException(String msg, Throwable cause) {
		super(msg, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}
}

