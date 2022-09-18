package controller;

import dao.VendingMachinePersistenceException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import service.DataValidationException;
import service.InsufficientFundsException;
import service.InvalidItemException;
import service.NoItemInventoryException;
import service.VendingMachineServiceLayer;
import ui.VendingMachineView;
import dto.Change;
import dto.Item;
import dto.OptionEnter;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
public class VendingMachineController {
	
	@NonNull
	private VendingMachineView view;
	@NonNull
	private VendingMachineServiceLayer serviceLayer;
	
	public void run() throws InvalidItemException, VendingMachinePersistenceException, InsufficientFundsException, NoItemInventoryException, DataValidationException {
		boolean carryOn = true;
		OptionEnter selection = null;
		
		//try catch
		while(carryOn) {
			selection = getMenuSelection(returnAllItems());
			switch(selection) {
				case YES:
					selectItem();
					break;
				case NO:
					carryOn = false;
					break;
			}		
		}
		exitMessage();
	}
	
	public void selectItem() throws InsufficientFundsException, NoItemInventoryException, VendingMachinePersistenceException, DataValidationException, InvalidItemException {
		BigDecimal userFunds = view.insertFunds();
		String userSelection = view.selectItem();
		Change change = serviceLayer.buyItems(userSelection, userFunds);
		view.displayChange(change.getCoins());
	}
	
	public List<Item> returnAllItems() throws VendingMachinePersistenceException {
		List<Item> items = serviceLayer.getAllItems();
		return items;
	}
	public OptionEnter getMenuSelection(List<Item> items) {
		return view.menuAndShowItems(items);
	}
	public void exitMessage() {
		view.displayExitMessage();
	}
}
