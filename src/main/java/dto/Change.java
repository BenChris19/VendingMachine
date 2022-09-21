package dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

/**
 * Change class, gives the user change in pennies, nickles, dimes and/or quarters after purchasing an item from the vending machine.
 * @author benat
 *
 */
public class Change {

	/**
	 * Different types of coins
	 * @author benat
	 *
	 */
	public enum Coin{
		PENNY,
		NICKEL,
		DIME,
		QUARTER
	}
	
	private List<Coin> coins;
	private BigDecimal totalChange;
	
	/**
	 * Gets penny from Coin enum
	 * @return
	 */
	public Coin getPenny() {
		return Coin.PENNY;
	}
	
	/**
	 * Gets Nickel from Coin enum
	 * @return
	 */
	public Coin getNickel() {
		return Coin.NICKEL;
	}
	
	/**
	 * Gets dime from Coin enum
	 * @return
	 */
	public Coin getDime() {
		return Coin.DIME;
	}
	
	/**
	 * Gets quarter from Coin enum
	 * @return
	 */
	public Coin getQuarter() {
		return Coin.QUARTER;
	}
	
}