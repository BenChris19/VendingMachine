package service;

/**
 * Throw an error if the inventory is empty for a certain item
 * @author benat
 *
 */
@SuppressWarnings("serial")
public class NoItemInventoryException extends Exception {

	public NoItemInventoryException(String message) {
		super(message);
		this.setStackTrace(new StackTraceElement[0]);
	}
	public NoItemInventoryException(String message, Throwable cause) {
		super(message, cause);
		this.setStackTrace(new StackTraceElement[0]);
	}
}
