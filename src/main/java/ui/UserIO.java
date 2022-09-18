package ui;

import java.math.BigDecimal;

import dto.OptionEnter;

public interface UserIO {
	
	void print(String msg);
	OptionEnter readOption();
	BigDecimal readBigDecimal();
	public String readString();
}
