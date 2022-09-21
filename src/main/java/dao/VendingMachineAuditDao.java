package dao;

/**
 * Interface for audit. Keeps record of when items are displayed and puchased.
 * @author benat
 *
 */
public interface VendingMachineAuditDao{
    void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}