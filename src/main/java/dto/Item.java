package dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Item {
	
	private String itemName;
	private BigDecimal price;
	private int inventoryLevel;

}
