package ui;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.OptionEnter;

public class UserIOImpl implements UserIO {
	private final Scanner sc = new Scanner(System.in);

	public void print(String msg) {
		System.out.println(msg);
		
	}

	public OptionEnter readOption() {
		OptionEnter option = null;
        boolean validInput = true;
        do {
        	String stringInput = sc.nextLine();
        	option = switch(stringInput.toUpperCase()) {
        		case "YES"-> OptionEnter.YES;
        		case "NO"-> OptionEnter.NO;
        		default-> null;
        	};
           if(option != null) {
               validInput = true;
           }
           else {
               this.print("Please enter either Yes or No!");
               validInput = false;
           }
        }
         while(!validInput);
        return option;
	}

	public BigDecimal readBigDecimal() {
        boolean invalidInput = true;
        BigDecimal decimal = new BigDecimal("0.0");
        while (invalidInput) {
            try {
            	String stringValue = sc.nextLine();
            	decimal = new BigDecimal(stringValue);
            	invalidInput=false;
                
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return decimal;
	}

	/**
	 * Read string from scanner. Only reads words no integer
	 */
	@Override
	public String readString() {	
        String string = null;
        boolean validInput = true;
        do {
            String stringInput= sc.nextLine();
            Pattern p = Pattern.compile("[a-zA-Z\\s]+"); //Regex
            Matcher m = p.matcher(stringInput);
            if(m.matches() && !stringInput.isBlank()) {
            	string=stringInput;
            	validInput = true;
            }
            else {
            	this.print("Please only enter words");
            	validInput = false;
            }
        } while(!validInput);
        return string;
	}

}
