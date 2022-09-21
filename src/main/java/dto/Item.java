package dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * Item from the vending machine. With its name, price and amount
 * @author benat
 *
 */
public class Item {
	
	private String itemName;
	private BigDecimal price;
	private int inventoryLevel;

}
