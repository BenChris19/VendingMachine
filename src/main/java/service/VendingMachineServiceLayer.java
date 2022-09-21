package service;

import java.math.BigDecimal;
import java.util.List;

import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;

/**
 * Interface for vending machine service layer.
 * @author benat
 *
 */
public interface VendingMachineServiceLayer {

	Change buyItems(String itemNameOption, BigDecimal cash) throws
	InsufficientFundsException,
	NoItemInventoryException,
	VendingMachinePersistenceException,
	DataValidationException,
	InvalidItemException;
	
	List<Item> getAllItems() throws
	VendingMachinePersistenceException;
}
