import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;

import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditDaoImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import dao.VendingMachinePersistenceException;
import dto.Change;
import service.InsufficientFundsException;
import service.InvalidItemException;
import service.NoItemInventoryException;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;

/**
 * Unit testing for Vending machine. Checks if all operations have been performed appropriately.
 * @author benat
 *
 */
class VendingMachineServiceLayerImplTest {

	private VendingMachineServiceLayer service;
	
	/**
	 * Creates test constructor
	 */
	public VendingMachineServiceLayerImplTest() {
		VendingMachineDao dao = new VendingMachineDaoImpl("VendingMachineTest.txt");
		VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl("AuditTest.txt");
		
		service = new VendingMachineServiceLayerImpl(auditDao,dao);
	}
	
	@BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Check if correct value is returned and that actual item is bought.
     * @throws Exception
     */
    @Test
    public void buyValidItem() throws Exception{
    	
    	String itemName = "Fanta";
    	BigDecimal cash = new BigDecimal("3.00");
    	
    	try {
    		Change change = service.buyItems(itemName, cash);
    		int penny_counter = 0;
    		int nickel_counter = 0;
    		int dime_counter = 0;
    		int quarter_counter = 0;
    		
    		for(Change.Coin coin : change.getCoins()) {
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
    		assertEquals(penny_counter,0);
    		assertEquals(nickel_counter,0);
    		assertEquals(dime_counter,0);
    		assertEquals(quarter_counter,2);
    	}
    	catch (InsufficientFundsException | NoItemInventoryException | VendingMachinePersistenceException | DataValidationException e){
    		fail("Item and money were valid, no exception should've occured here");
    	}
    }
    
    /**
     * Test if program fails if user hasn't got enough money
     * @throws service.DataValidationException 
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException
     * @throws VendingMachinePersistenceException
     * @throws DataValidationException
     * @throws InvalidItemException
     * @throws service.DataValidationException
     */
    @Test
    public void testInsufficeintFunds() throws service.DataValidationException{
    	String itemName = "Milkyway";
    	BigDecimal cash = new BigDecimal("1.00");
    	
    	try {
    		service.buyItems(itemName, cash);
    		fail("InsufficientFundsException was not thrown");
    	}
    	catch (NoItemInventoryException | VendingMachinePersistenceException | DataValidationException | InvalidItemException e) {
    		fail("Incorrect Exception thrown");
    	}
    	catch(InsufficientFundsException e) {
    		return;
    	}
    }
    
    /**
     * 
     * @throws InsufficientFundsException
     * @throws NoItemInventoryException
     * @throws VendingMachinePersistenceException
     * @throws DataValidationException
     * @throws InvalidItemException
     * @throws service.DataValidationException
     */
    @Test
    public void testNoItemsLeft() throws InsufficientFundsException, NoItemInventoryException, 
    VendingMachinePersistenceException, DataValidationException, InvalidItemException, service.DataValidationException {
    	String itemName = "Milkyway";
    	BigDecimal cash = new BigDecimal("3.00");
    	//Update inventory in VendingMachineTest.txt to 10 so that test works
    	
    	for(int i = 0; i < 10; i++) {
    		service.buyItems(itemName, cash);
    	}
    	
    	try {
    		service.buyItems(itemName, cash);
    		fail("NoItemInventoryException was not thrown");
    	}
    	catch (InsufficientFundsException | VendingMachinePersistenceException | DataValidationException | InvalidItemException e) {
    		fail("Incorrect exception was thrown");
    	}
    	catch(NoItemInventoryException e) {
    		return;
    	}
    }
    
    @Test
    /**
     * Test if the user want's to purchase an item not in the vending machine
     * @throws service.DataValidationException
     */
    public void testInvalidItem() throws service.DataValidationException{
    	
    	String itemName = "Dairymilk";
    	BigDecimal cash = new BigDecimal("3.00");
    	
    	try {
    		service.buyItems(itemName, cash);
    		fail("InvalidItemException was not thrown");
    	}
    	catch(InsufficientFundsException | VendingMachinePersistenceException | DataValidationException | NoItemInventoryException e) {
    		fail("Incorrect exception was thrown");
    	}
    	catch(InvalidItemException e) {
    		return;
    	}
    }

}