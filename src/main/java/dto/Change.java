package dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Change {

	public enum Coin{
		PENNY,
		NICKEL,
		DIME,
		QUARTER
	}
	
	private List<Coin> coins;
	private BigDecimal totalChange;
	
	
	public Coin getPenny() {
		return Coin.PENNY;
	}
	public Coin getNickel() {
		return Coin.NICKEL;
	}
	public Coin getDime() {
		return Coin.DIME;
	}
	public Coin getQuarter() {
		return Coin.QUARTER;
	}
	
}