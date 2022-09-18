package ui;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import dto.Change;
import dto.Item;
import dto.OptionEnter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VendingMachineView {
	
	@NonNull
	private UserIO io;
	
	public OptionEnter menuAndShowItems(List<Item> items) {
		io.print("You are in front of a vending machine, it contains the following items:\n");
		
		items.forEach((item)-> {io.print("Item name: " + item.getItemName());	//Lambda expression
								io.print("Price: " + item.getPrice());
								io.print("Items remaining: " + item.getInventoryLevel());
								if(item.getInventoryLevel()<=0) {
									io.print("(**Currently Unavailable**)");}
								io.print("");
								});
		
		io.print("Do you want to insert funds or exit the program. Type YES or NO");
		return io.readOption();
	}
	
	public void displayChange(List<Change.Coin> coins) {
		
		int penny_counter = 0;
		int nickel_counter = 0;
		int dime_counter = 0;
		int quarter_counter = 0;
		
		for(Change.Coin coin : coins) {
			if (coin == Change.Coin.PENNY) {
				penny_counter += 1;
			}
			else if(coin == Change.Coin.NICKEL) {
				nickel_counter += 1;
			}
			else if(coin == Change.Coin.DIME) {
				dime_counter += 1;
			}
			else if(coin == Change.Coin.QUARTER) {
				quarter_counter += 1;
			}
		}
		io.print("Your change contains the following:");
		io.print(penny_counter + " Pennies");
		io.print(nickel_counter + " Nickels");
		io.print(dime_counter + " Dimes");
		io.print(quarter_counter + " Quarters");
		
	}

	public BigDecimal insertFunds() {
		io.print("Please insert some money into the vending machine, including decimals");
		BigDecimal userInput = io.readBigDecimal();
		userInput.setScale(2, RoundingMode.HALF_UP);
		return userInput;
	}
	
	public String selectItem() {
		io.print("Please select the item that you want to buy");
		String userInput = io.readString();
		return userInput;
	}
	public void displayExitMessage() {
		io.print("You leave the vending machine. End of programme.");
	}
	

}
