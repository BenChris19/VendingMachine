package dao;

/**
 * Throw error if there is a problem wit the file the items of the vending machine are on.
 * @author benat
 *
 */
@SuppressWarnings("serial")
public class VendingMachinePersistenceException extends Exception {
	
	public VendingMachinePersistenceException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]);	//Remove stack trace
	}
	public VendingMachinePersistenceException(String message, Throwable cause) {
		super(message, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}

}
