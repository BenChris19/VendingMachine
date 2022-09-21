package service;

import java.math.BigDecimal;
import java.util.List;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.Change;
import dto.Item;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Implements vending machine service layer. It sits between the controller and the dao, and is in charge of carrying out operations.
 * @author benat
 *
 */
@RequiredArgsConstructor
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
	
	@NonNull
	private VendingMachineAuditDao auditDao;
	@NonNull
	private VendingMachineDao dao;

	/**
	 * Buy an item from the vending machine.
	 * @param name
	 * @param cash
	 * @throws VendingMachinePersistenceException
	 * @throws InsufficientFundsException
	 * @throws NoItemInventoryException
	 * @throws DataValidationException
	 * @throws InvalidItemException
	 */
	@Override
	public Change buyItems(String name, BigDecimal cash) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException,
	DataValidationException, InvalidItemException{
	
	validateData(name, cash);
	Change change = null;
	
	if(dao.getItem(name) == null) {
		auditDao.writeAuditEntry("Invalid item was entered");
		throw new InvalidItemException("Sorry, but this item does not exist in the vending machine: "+name);
	}
	
	BigDecimal itemPrice = dao.getItem(name).getPrice();
	int inventoryLevel = dao.getItem(name).getInventoryLevel();
	
	if(itemPrice.compareTo(cash) > 0) {
		auditDao.writeAuditEntry("Insufficient funds detected");
		throw new InsufficientFundsException("Sorry, but you don't have enough funds to buy this item\n"
				+ "This item costs:"+itemPrice+" and you entered "+cash+" you are "+itemPrice.subtract(cash)+" below the price.");
	}
	else if(inventoryLevel == 0) {
		auditDao.writeAuditEntry("Item with 0 stock was tried to be purchased");
		throw new NoItemInventoryException("Sorry, but this item is currently not in the inventory");
	}
	else {
		auditDao.writeAuditEntry("Items were purchased");
		change = dao.buyItems(name, cash);
	}
	return change;
	
}
	
	/**
	 * Gets all items from the vending machine
	 */
	@Override
	public List<Item> getAllItems() throws VendingMachinePersistenceException{
		auditDao.writeAuditEntry("All items have been retrieved");
		return dao.getAllItems();
		
	}

	/**
	 * Check if the data the user has entered is correct
	 * @param name
	 * @param cash
	 * @throws DataValidationException
	 */
	private void validateData(String name, BigDecimal cash) throws DataValidationException {
		if(name == null || name.isBlank() || cash == null || cash.compareTo(BigDecimal.valueOf(0.00)) < 0) {
			throw new DataValidationException("ERROR: You must insert some cash and insert an existing item name\n");
		}
	}

}
