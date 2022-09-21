package dao;

import java.math.BigDecimal;
import java.util.List;

import dto.Item;
import dto.Change;

/**
 * Interface for vending machine data access.
 * @author benat
 *
 */
public interface VendingMachineDao {
	Change buyItems(String name, BigDecimal price) throws VendingMachinePersistenceException;
	List<Item> getAllItems() throws VendingMachinePersistenceException;
	Item getItem(String name) throws VendingMachinePersistenceException;
	Change getChange(BigDecimal itemPrice, BigDecimal cash) throws VendingMachinePersistenceException;
}
