package app;

import controller.VendingMachineController;
import dao.VendingMachineAuditDao;
import dao.VendingMachineAuditDaoImpl;
import dao.VendingMachineDao;
import dao.VendingMachineDaoImpl;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOImpl;
import ui.VendingMachineView;

/**
 * Entry point of our vending machine app.
 * The Main class acts as the application assembler, it chooses the implementations of the dependencies and 
 * wires them together.
 * @author benat
 *
 */
public class Main {
	
	/**
	 * Main method. Use dependency injection and wire the entire application. Follow MVC tier application
	 */
	public static void main(String[] args) throws Exception {
		UserIO io = new UserIOImpl();
		
		VendingMachineDao dao = new VendingMachineDaoImpl("VendingMachine.txt");
		VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl("Audit.txt");
		
		VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(auditDao, dao);
		VendingMachineView view = new VendingMachineView(io);
		
		VendingMachineController controller = new VendingMachineController(view, service);
		controller.run();
	}

}
