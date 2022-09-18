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

public class Main {

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
