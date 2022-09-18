package dao;

@SuppressWarnings("serial")
public class VendingMachinePersistenceException extends Exception {
	
	public VendingMachinePersistenceException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]);
	}
	public VendingMachinePersistenceException(String message, Throwable cause) {
		super(message, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}

}
