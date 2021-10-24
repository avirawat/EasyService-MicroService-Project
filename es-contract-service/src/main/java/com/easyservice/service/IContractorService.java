/**
 *
 */
package com.easyservice.service;

/**
 * @author TharunyaREDDY
 *
 */
import java.time.LocalDate;
import java.util.List;
import com.easyservice.exception.ContractNotFoundException;
import com.easyservice.exception.MaintenanceNotFoundException;
import com.easyservice.model.Contractor;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;

public interface IContractorService {

	// **********************************Contractor*******************************************

	Contractor addContractor(Contractor contractor);

	String updateContractor(Contractor contractor);

	String deleteContractor(int contractId) throws ContractNotFoundException;

	List<Contractor> getAllContracts();

	Contractor getByContractId(int contractId) throws ContractNotFoundException;

	List<Contractor> getContractNameByMaintenanceManager(String maintenanceManager) throws ContractNotFoundException;

	List<Contractor> getContractsByMaintenanceName(String maintenanceName) throws ContractNotFoundException;

	// *************************************Maintenance*******************************************

	Maintenance assignMaintenance(Maintenance maintenance, int contractId);

	void unAssignMaintenance(Maintenance maintenance);

	Maintenance getByMaintenanceId(int maintenanceId) throws MaintenanceNotFoundException;

	List<Maintenance> getAllMaintenance();

	List<Maintenance> getMaintenanceByContractName(String contractName) throws MaintenanceNotFoundException;

	List<Maintenance> getMaintenanceByContractorName(String contractorName) throws MaintenanceNotFoundException;

	// ******************************************************************************************//

	Contractor getByContractName(String contractName) throws ContractNotFoundException;

	Contractor getByContractorName(String contractorName) throws ContractNotFoundException;

	List<Contractor> getByStartDate(LocalDate startDate) throws ContractNotFoundException;

	List<Contractor> getByEndDate(LocalDate endDate) throws ContractNotFoundException;

	List<Contractor> getByStartDateAndEndDate(LocalDate startDate, LocalDate endDate) throws ContractNotFoundException;

	Maintenance getByMaintenanceName(String maintenanceName) throws MaintenanceNotFoundException;

	Maintenance getByMaintenanceManager(String maintenanceManager) throws MaintenanceNotFoundException;

	List<Maintenance> getByMaintenanceStartDate(LocalDate mStartDate) throws MaintenanceNotFoundException;

	List<Maintenance> getByMaintenanceEndDate(LocalDate mEndDate) throws MaintenanceNotFoundException;

	List<Maintenance> getByMaintenanceStatus(Status mStatus) throws MaintenanceNotFoundException;

	List<Maintenance> getByMaintenancePriority(Priority mPriority) throws MaintenanceNotFoundException;

	List<Maintenance> getByMaintenanceStartAndEndDate(LocalDate mStartDate, LocalDate mEndDate)
			throws MaintenanceNotFoundException;

	List<Maintenance> getByMaintenanceStatusAndPriority(Status mStatus, Priority mPriority)
			throws MaintenanceNotFoundException;

	Maintenance getByMaintenanceNameAndStatus(String maintenanceName, Status mStatus)
			throws MaintenanceNotFoundException;

	Maintenance getByMaintenanceNameAndPriority(String maintenanceName, Priority mPriority)
			throws MaintenanceNotFoundException;

}
