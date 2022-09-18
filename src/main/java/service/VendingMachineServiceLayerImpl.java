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

@RequiredArgsConstructor
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {
	
	@NonNull
	private VendingMachineAuditDao auditDao;
	@NonNull
	private VendingMachineDao dao;

	@Override
	public Change buyItems(String name, BigDecimal cash) throws VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException,
	DataValidationException, InvalidItemException{
	
	validateData(name, cash);
	Change change = null;
	
	if(dao.getItem(name) == null) {
		throw new InvalidItemException("Sorry, but this item does not exist in the vending machine: "+name);
	}
	
	BigDecimal itemPrice = dao.getItem(name).getPrice();
	int inventoryLevel = dao.getItem(name).getInventoryLevel();
	
	if(itemPrice.compareTo(cash) > 0) {
		throw new InsufficientFundsException("Sorry, but you don't have enough funds to buy this item\n"
				+ "This item costs:"+itemPrice+" and you entered "+cash+" you are "+itemPrice.subtract(cash)+" below the price.");
	}
	else if(inventoryLevel == 0) {
		throw new NoItemInventoryException("Sorry, but this item is currently not in the inventory");
	}
	else {
		auditDao.writeAuditEntry("Items were purchased");
		change = dao.buyItems(name, cash);
	}
	return change;
	
}

	@Override
	public List<Item> getAllItems() throws VendingMachinePersistenceException{
		auditDao.writeAuditEntry("All items have been retrieved");
		return dao.getAllItems();
		
	}

	private void validateData(String name, BigDecimal cash) throws DataValidationException {
		if(name == null || name.trim().length() == 0|| cash == null || cash.compareTo(BigDecimal.valueOf(0.00)) < 0) {
			throw new DataValidationException("ERROR: You must insert some cash and insert an existing item name\n");
		}
	}

}
