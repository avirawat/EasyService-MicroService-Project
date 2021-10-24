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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.easyservice.exception.ContractNotFoundException;
import com.easyservice.exception.MaintenanceNotFoundException;
import com.easyservice.model.Contractor;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;
import com.easyservice.repository.IContractorRepository;

@Service
public class ContractorServiceImpl implements IContractorService {

	RestTemplate restTemplate;

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private static final String BASEURL = "http://localhost:8071/maintenance-service/maintenance";

	@Autowired
	IContractorRepository contractorRepository;

	// ********************************************Contractor**************************************

	// To Add the Contract
	@Override
	public Contractor addContractor(Contractor contractor) {
		return contractorRepository.save(contractor);
	}

	// To Update the Contract Details
	@Override
	public String updateContractor(Contractor contractor) {
		contractorRepository.save(contractor);
		return "Contract Updated Successfully";
	}

	// To Delete the Contract By Contract Id
	@Override
	public String deleteContractor(int contractId) throws ContractNotFoundException {
		Contractor contractor = contractorRepository.findById(contractId).get();
		if (contractor == null) {
			throw new ContractNotFoundException("Contract Id Not Found,Invalid ContractId");
		}
		contractorRepository.deleteById(contractId);
		return "Contract Deleted Succesfully";
	}

	// Retrieving Contract By Contract Id
	@Override
	public Contractor getByContractId(int contractId) throws ContractNotFoundException {

		Contractor contract = contractorRepository.findByContractId(contractId);
		if (contract == null) {
			throw new ContractNotFoundException("Contract Not Found,Invalid ContractId");
		}
		return contract;
	}

	// Retrieving All Contracts
	@Override
	public List<Contractor> getAllContracts() {
		List<Contractor> allContracts = contractorRepository.findAll();
		return allContracts;

	}

	// Retrieving Contract By maintenance Manager Name
	@Override
	public List<Contractor> getContractNameByMaintenanceManager(String maintenanceManager)
			throws ContractNotFoundException {
		String url = BASEURL + "/maintenanceManager/" + maintenanceManager;
		List<Contractor> contractList = contractorRepository.findContractNameByMaintenanceManager(maintenanceManager);

		if (contractList.isEmpty()) {
			throw new ContractNotFoundException("Contract Not Found,Invalid Manager Name");
		}

		return contractList;
	}

	// Retrieving Contract By maintenance Name like cleaning,painting etc
	@Override
	public List<Contractor> getContractsByMaintenanceName(String maintenanceName) throws ContractNotFoundException {

		String url = BASEURL + "/maintenanceName/" + maintenanceName;
		List<Contractor> contractList = contractorRepository.findContractsByMaintenanceName(maintenanceName);
		if (contractList.isEmpty()) {
			throw new ContractNotFoundException("Contract Not Found,Invaild Maintenance Name");
		}
		return contractList;
	}

	// Retrieving Contract By Contract Name
	@Override
	public Contractor getByContractName(String contractName) throws ContractNotFoundException {

		Contractor contractByName = contractorRepository.findByContractName(contractName);
		if (contractByName == null) {
			throw new ContractNotFoundException("Contract Name Not Found");
		}
		return contractByName;
	}

	// Retrieving Contract By Contractor Name
	@Override
	public Contractor getByContractorName(String contractorName) throws ContractNotFoundException {

		Contractor contractorByName = contractorRepository.findByContractorName(contractorName);
		if (contractorByName == null) {
			throw new ContractNotFoundException("Contract Not Found,Invalid Contractor Name");
		}
		return contractorByName;
	}

	// Retrieving Contract By Start Date
	@Override
	public List<Contractor> getByStartDate(LocalDate startDate) throws ContractNotFoundException {

		List<Contractor> contractByStartDate = contractorRepository.findByStartDate(startDate);
		if (contractByStartDate.isEmpty()) {
			throw new ContractNotFoundException("Contract Not Found,Invalid Start Date");
		}
		return contractByStartDate;

	}

	// Retrieving Contract By End Date
	@Override
	public List<Contractor> getByEndDate(LocalDate endDate) throws ContractNotFoundException {

		List<Contractor> contractByEndDate = contractorRepository.findByEndDate(endDate);
		if (contractByEndDate.isEmpty()) {
			throw new ContractNotFoundException("Contract Not Found,Invalid End Date");
		}
		return contractByEndDate;

	}

	// Retrieving Contract By Start Date and End Date
	@Override
	public List<Contractor> getByStartDateAndEndDate(LocalDate startDate, LocalDate endDate)
			throws ContractNotFoundException {

		List<Contractor> contractByStartDateAndEndDate = contractorRepository.findByStartDateAndEndDate(startDate,
				endDate);
		if (contractByStartDateAndEndDate.isEmpty()) {
			throw new ContractNotFoundException("Contract Not Found,Invalid Dates");
		}
		return contractByStartDateAndEndDate;
	}

	// ***************************************Maintenance***************************************

	// Assigning Maintenance to the Contract
	@Override
	public Maintenance assignMaintenance(Maintenance maintenance, int contractId) {
		Contractor contractor = contractorRepository.findById(contractId).get();
		maintenance.setContractor(contractor);
		String url = BASEURL;
		restTemplate.put(url, maintenance);
		return maintenance;
	}

	// Un-Assigning the maintenance to the contract
	@Override
	public void unAssignMaintenance(Maintenance maintenance) {

		String url = BASEURL;
		restTemplate.put(url, maintenance);
	}

	// Retrieving Maintenance By Maintenance Id
	@Override
	public Maintenance getByMaintenanceId(int maintenanceId) throws MaintenanceNotFoundException {
		String url = BASEURL + maintenanceId;
		Maintenance maintenance = restTemplate.getForEntity(url, Maintenance.class).getBody();
		if (maintenance == null) {
			throw new MaintenanceNotFoundException("Maintenance Not Found,Invalid Maintenance Id");
		}
		return maintenance;
	}

	// Retrieving All Maintenances
	@Override
	public List<Maintenance> getAllMaintenance() {
		String url = BASEURL;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Contract Name
	@Override
	public List<Maintenance> getMaintenanceByContractName(String contractName) throws MaintenanceNotFoundException {
		String url = BASEURL + "/contractName/" + contractName;
		List<Maintenance> maintenanceList = contractorRepository.findMaintenanceByContractName(contractName);

		if (maintenanceList.isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found,Invalid Contract Name");
		}

		return maintenanceList;
	}

	// Retrieving Maintenance By Contractor Name
	@Override
	public List<Maintenance> getMaintenanceByContractorName(String contractorName) throws MaintenanceNotFoundException {
		String url = BASEURL + "/contractorName/" + contractorName;
		List<Maintenance> maintenanceList = contractorRepository.findMaintenanceByContractorName(contractorName);

		if (maintenanceList.isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found,Invalid Contractor Name");
		}

		return maintenanceList;
	}

	// Retrieving Maintenance By Maintenance Name
	@Override
	public Maintenance getByMaintenanceName(String maintenanceName) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceName/" + maintenanceName;
		Maintenance maintenance = restTemplate.getForEntity(url, Maintenance.class).getBody();
		if (maintenance == null) {
			throw new MaintenanceNotFoundException("Maintenance Name Not Found");
		}
		return maintenance;
	}

	// Retrieving Maintenance By Maintenance Manager
	@Override
	public Maintenance getByMaintenanceManager(String maintenanceManager) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceManager/" + maintenanceManager;
		Maintenance maintenance = restTemplate.getForEntity(url, Maintenance.class).getBody();
		if (maintenance == null) {
			throw new MaintenanceNotFoundException("Maintenance Manager Not Found");
		}
		return maintenance;
	}

	// Retrieving Maintenance By Maintenance Start Date
	@Override
	public List<Maintenance> getByMaintenanceStartDate(LocalDate mStartDate) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceStartDate/" + mStartDate;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found,Invalid Start Date");
		}
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Maintenance End Date
	@Override
	public List<Maintenance> getByMaintenanceEndDate(LocalDate mEndDate) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceEndDate/" + mEndDate;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found,Invalid End Date");
		}
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Maintenance Status
	@Override
	public List<Maintenance> getByMaintenanceStatus(Status mStatus) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceStatus/" + mStatus;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Maintenance Priority
	@Override
	public List<Maintenance> getByMaintenancePriority(Priority mPriority) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenancePriority/" + mPriority;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Start Date and End Date
	@Override
	public List<Maintenance> getByMaintenanceStartAndEndDate(LocalDate mStartDate, LocalDate mEndDate)
			throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceStartDate/" + mStartDate + "/maintenanceEndDate/" + mEndDate;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Status and Priority
	@Override
	public List<Maintenance> getByMaintenanceStatusAndPriority(Status mStatus, Priority mPriority)
			throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceStatus/" + mStatus + "/maintenancePriority/" + mPriority;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Maintenance Name and Status
	@Override
	public Maintenance getByMaintenanceNameAndStatus(String maintenanceName, Status mStatus)
			throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceName/" + maintenanceName + "/maintenanceStatus/" + mStatus;
		Maintenance maintenance = restTemplate.getForEntity(url, Maintenance.class).getBody();
		if (maintenance == null) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenance;
	}

	// Retrieving Maintenance By Maintenance Name and Priority
	@Override
	public Maintenance getByMaintenanceNameAndPriority(String maintenanceName, Priority mPriority)
			throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceName/" + maintenanceName + "/maintenancePriority/" + mPriority;
		Maintenance maintenance = restTemplate.getForEntity(url, Maintenance.class).getBody();
		if (maintenance == null) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenance;
	}

}
