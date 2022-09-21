package ui;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dto.OptionEnter;

/**
 * Handles user input
 * @author benat
 *
 */
public class UserIOImpl implements UserIO {
	private final Scanner sc = new Scanner(System.in);

	/**
	 * Print a message to the user.
	 */
	public void print(String msg) {
		System.out.println(msg);
		
	}

	/**
	 * Read yes or no option from the user if they want to buy something or leave the vending machine.
	 */
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

	/**
	 * Read a decimal number from the user
	 */
	public BigDecimal readBigDecimal() {
        boolean invalidInput = true;
        BigDecimal decimal = new BigDecimal("0.0");
        while (invalidInput) {
            try {
            	String stringValue = sc.nextLine();
            	decimal = new BigDecimal(stringValue);
            	if(decimal.compareTo(BigDecimal.ZERO)<=0) {
            		this.print("Please enter a number larger than 0");
            		invalidInput = true;
            	}
            	else {
            		invalidInput=false;
            	}
                
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
	
    /**
	* Read if the user has entered any blank spaces. Used for the user to continue with the program.
	*/
	@Override
	public void readAnything() {
		sc.nextLine();
	}

}
