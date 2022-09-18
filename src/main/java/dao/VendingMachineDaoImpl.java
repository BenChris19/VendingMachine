package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import dto.Change;
import dto.Change.Coin;
import dto.Item;


public class VendingMachineDaoImpl implements VendingMachineDao{

	private static String VENDING_MACHINE_FILE;
	
	private static final String DELIMITER = "::";
	
	private HashMap<String,Item> inventory = new HashMap<>();
	
	public VendingMachineDaoImpl(String vendingMachineTextFile) {
		VENDING_MACHINE_FILE = vendingMachineTextFile;
	}	

	@Override
	public Change buyItems(String name, BigDecimal cash) throws VendingMachinePersistenceException {
		loadItem();
		Item userItem = inventory.get(name);
		userItem.setInventoryLevel(userItem.getInventoryLevel() - 1);
		inventory.replace(name, userItem);
		
		Change change = getChange(userItem.getPrice(), cash);
		writeItem();
		return change;
	}

	@Override
	public List<Item> getAllItems() throws VendingMachinePersistenceException{
		loadItem();
		return new ArrayList<Item>(inventory.values());
	}

	@Override
	public void addItem(Item item) throws VendingMachinePersistenceException {
		loadItem();
		inventory.put(item.getItemName(), item);
		writeItem();
		return;
	}

	@Override
	public Item getItem(String name) throws VendingMachinePersistenceException {
		loadItem();
		return inventory.get(name);
	}

	@Override
	public Change getChange(BigDecimal itemPrice, BigDecimal cash) throws VendingMachinePersistenceException{
		
		Change change = new Change();
		List<Coin> coins = new ArrayList<Coin>();
		BigDecimal moneyDiff = cash.subtract(itemPrice);
		change.setTotalChange(moneyDiff);
		
		while(moneyDiff.compareTo(BigDecimal.ZERO) != 0) {
			
			if(moneyDiff.compareTo(BigDecimal.valueOf(0.25)) >= 0) {
				moneyDiff = moneyDiff.subtract(BigDecimal.valueOf(0.25));
				coins.add(change.getQuarter());
			}
			else if(moneyDiff.compareTo(BigDecimal.valueOf(0.10)) >= 0) {
				moneyDiff = moneyDiff.subtract(BigDecimal.valueOf(0.10));
				coins.add(change.getDime());
			}
			else if(moneyDiff.compareTo(BigDecimal.valueOf(0.05)) >= 0) {
				moneyDiff = moneyDiff.subtract(BigDecimal.valueOf(0.05));
				coins.add(change.getNickel());
			}
			else if(moneyDiff.compareTo(BigDecimal.valueOf(0.01)) >= 0) {
				moneyDiff = moneyDiff.subtract(BigDecimal.valueOf(0.01));
				coins.add(change.getPenny());
			}
		}
		change.setCoins(coins);
		return change;
	}

	private Item unmarshallItem(String ItemAsText) {
		
		String[] ItemAsElements = ItemAsText.split(DELIMITER);
		String itemName = ItemAsElements[0];
		Item itemFromFile = new Item();
		itemFromFile.setItemName(itemName);
		
		BigDecimal itemPrice = new BigDecimal(ItemAsElements[1]);
		itemFromFile.setPrice(itemPrice.setScale(2, RoundingMode.HALF_UP));
		itemFromFile.setInventoryLevel(Integer.parseInt(ItemAsElements[2]));
		
		return itemFromFile;
	}
	

	@SuppressWarnings("unused")
	private void loadItem() throws VendingMachinePersistenceException{
		
		Scanner sc;
		
		try {
			sc = new Scanner(new BufferedReader(new FileReader(VENDING_MACHINE_FILE)));
		}
		catch(FileNotFoundException e) {
			throw new VendingMachinePersistenceException("Could not locate the file", e);
		}
		
		String currentLine;
		Item currentItem;
		
		while(sc.hasNextLine()) {
			currentLine = sc.nextLine();
			currentItem = unmarshallItem(currentLine);
			inventory.put(currentItem.getItemName(), currentItem);
		}
		sc.close();
	}
	
	//Translate data from object in memory into a text file.
	private String marshallItem(Item item) {
		
		String ItemAsText = item.getItemName() + DELIMITER;
		ItemAsText += item.getPrice() + DELIMITER;
		ItemAsText += item.getInventoryLevel();
		
		return ItemAsText;
	}
	

	@SuppressWarnings("unused")
	private void writeItem() throws VendingMachinePersistenceException{
		
		PrintWriter out;
		
		try {
			out = new PrintWriter(new FileWriter(VENDING_MACHINE_FILE));
		}
		catch(Exception e) {
			throw new VendingMachinePersistenceException("Could not save DVD data", e);
		}
		
		String ItemAsText;
		List<Item> ItemList = this.getAllItems();
		for(Item currentItem : ItemList) {
			ItemAsText = marshallItem(currentItem);
			out.println(ItemAsText);
			out.flush();
		}
		out.close();
	}

}
