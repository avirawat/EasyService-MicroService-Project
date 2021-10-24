/**
 *
 */
package com.easyservice.controller;

/**
 * @author TharunyaREDDY
 *
 */
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyservice.model.Contractor;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;
import com.easyservice.service.IContractorService;

@RestController
@RequestMapping("/contractor-service")
public class ContractorController {

	@Autowired
	IContractorService contractorService;

	// *************************************Contractor*********************************************

	// http://localhost:8070/contractor-service/contracts
	@PostMapping("/contracts")
	public Contractor addContractor(@RequestBody Contractor contractor) {
		return contractorService.addContractor(contractor);
	}

	// http://localhost:8070/contractor-service/contracts
	@PutMapping("/contracts")
	public void updateContractor(@RequestBody Contractor contractor) {
		contractorService.updateContractor(contractor);
	}

	// http://localhost:8070/contractor-service/contracts/1
	@DeleteMapping("/contracts/{contractId}")
	public void deleteContractor(@PathVariable("contractId") int contractId) {
		contractorService.deleteContractor(contractId);
	}

	// http://localhost:8070/contractor-service/contracts
	@GetMapping("/contracts")
	ResponseEntity<List<Contractor>> getAll() {
		List<Contractor> allContracts = contractorService.getAllContracts();
		return ResponseEntity.ok().body(allContracts);
	}

	// http://localhost:8070/contractor-service/contracts/contractId/1
	@GetMapping("/contracts/contractId/{contractId}")
	ResponseEntity<Contractor> getByContractId(@PathVariable("contractId") int contractId) {
		Contractor contractById = contractorService.getByContractId(contractId);
		return ResponseEntity.ok().body(contractById);
	}

	// http://localhost:8070/contractor-service/contracts/maintenance/maintenanceName/Cleaning
	@GetMapping("contracts/maintenance/maintenanceName/{maintenanceName}")
	ResponseEntity<List<Contractor>> getContractsByMaintenanceName(
			@PathVariable("maintenanceName") String maintenanceName) {
		List<Contractor> contractsList = contractorService.getContractsByMaintenanceName(maintenanceName);
		return ResponseEntity.accepted().body(contractsList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenance/maintenanceManager/Avinash
	@GetMapping("contracts/maintenance/maintenanceManager/{maintenanceManager}")
	ResponseEntity<List<Contractor>> getContractNameByManagerName(
			@PathVariable("maintenanceManager") String maintenanceManager) {
		List<Contractor> contractList = contractorService.getContractNameByMaintenanceManager(maintenanceManager);
		return ResponseEntity.accepted().body(contractList);
	}

	// http://localhost:8070/contractor-service/contracts/IBIS
	@GetMapping("/contracts/{contractName}")
	ResponseEntity<Contractor> getByContractName(@PathVariable("contractName") String contractName) {
		Contractor contractByName = contractorService.getByContractName(contractName);
		return ResponseEntity.ok().body(contractByName);
	}

	// http://localhost:8070/contractor-service/contracts/contractorName/Ayan
	@GetMapping("/contracts/contractorName/{contractorName}")
	ResponseEntity<Contractor> getByContractorName(@PathVariable("contractorName") String contractorName) {
		Contractor contractorByName = contractorService.getByContractorName(contractorName);
		return ResponseEntity.ok().body(contractorByName);
	}

	// http://localhost:8070/contractor-service/contracts/startdate/2021-10-12
	@GetMapping("/contracts/startdate/{startDate}")
	ResponseEntity<List<Contractor>> getByStartDate(@PathVariable("startDate") String startDate) {
		List<Contractor> contractByStartDate = contractorService.getByStartDate(LocalDate.parse(startDate));
		return ResponseEntity.ok().body(contractByStartDate);
	}

	// http://localhost:8070/contractor-service/contracts/enddate/2021-10-22
	@GetMapping("/contracts/enddate/{endDate}")
	ResponseEntity<List<Contractor>> getByEndDate(@PathVariable("endDate") String endDate) {
		List<Contractor> contractByEndDate = contractorService.getByEndDate(LocalDate.parse(endDate));
		return ResponseEntity.ok().body(contractByEndDate);
	}

	// http://localhost:8070/contractor-service/contracts/startdate/2021-10-12/enddate/2021-10-22
	@GetMapping("/contracts/startdate/{startDate}/enddate/{endDate}")
	ResponseEntity<List<Contractor>> getByStartAndEndDate(@PathVariable("startDate") String startDate,
			@PathVariable("endDate") String endDate) {
		List<Contractor> contractByDate = contractorService.getByStartDateAndEndDate(LocalDate.parse(startDate),
				LocalDate.parse(endDate));
		return ResponseEntity.ok().body(contractByDate);
	}

	// **************************************Maintenance******************************************

	// http://localhost:8070/contractor-service/contracts/maintenance/1
	@PostMapping("/contracts/maintenance/{contractId}")
	ResponseEntity<String> assignMaintenance(@RequestBody Maintenance maintenance,
			@PathVariable("contractId") int contractId) {
		contractorService.assignMaintenance(maintenance, contractId);
		ResponseEntity<String> maintenanceResponse = new ResponseEntity<String>("Project Assigned", HttpStatus.OK);
		return maintenanceResponse;
	}

	// http://localhost:8070/contractor-service/contracts/maintenance
	@PutMapping("/contracts/maintenance")
	ResponseEntity<String> unAssignMaintenance(@RequestBody Maintenance maintenance) {
		contractorService.unAssignMaintenance(maintenance);
		ResponseEntity<String> maintenanceResponse = new ResponseEntity<String>("Project Un-Assigned", HttpStatus.OK);
		return maintenanceResponse;
	}

	// http://localhost:8070/contractor-service/contracts/maintenance/contractName/IBIS
	@GetMapping("contracts/maintenance/contractName/{contractName}")
	ResponseEntity<List<Maintenance>> getMaintenanceByContractName(@PathVariable("contractName") String contractName) {
		List<Maintenance> maintenanceList = contractorService.getMaintenanceByContractName(contractName);
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenance/contractorName/Ayan
	@GetMapping("contracts/maintenance/contractorName/{contractorName}")
	ResponseEntity<List<Maintenance>> getMaintenanceByContractorName(
			@PathVariable("contractorName") String contractorName) {
		List<Maintenance> maintenanceList = contractorService.getMaintenanceByContractorName(contractorName);
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceName/Cleaning
	@GetMapping("/contracts/maintenanceName/{maintenanceName}")
	ResponseEntity<Maintenance> getByMaintenanceName(@PathVariable("maintenanceName") String maintenanceName) {
		Maintenance maintenance = contractorService.getByMaintenanceName(maintenanceName);
		ResponseEntity<Maintenance> maintenanceResponse = new ResponseEntity<Maintenance>(maintenance, HttpStatus.OK);
		return maintenanceResponse;
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceManager/Avinash
	@GetMapping("/contracts/maintenanceManager/{maintenanceManager}")
	ResponseEntity<Maintenance> getByMaintenanceManager(@PathVariable("maintenanceManager") String maintenanceManager) {
		Maintenance maintenance = contractorService.getByMaintenanceManager(maintenanceManager);
		ResponseEntity<Maintenance> maintenanceResponse = new ResponseEntity<Maintenance>(maintenance, HttpStatus.OK);
		return maintenanceResponse;
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceStartDate/2021-10-13
	@GetMapping("/contracts/maintenanceStartDate/{mStartDate}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStartDate(@PathVariable("mStartDate") String mStartDate) {
		List<Maintenance> maintenanceList = contractorService.getByMaintenanceStartDate(LocalDate.parse(mStartDate));
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceEndDate/2021-10-25
	@GetMapping("/contracts/maintenanceEndDate/{mEndDate}")
	ResponseEntity<List<Maintenance>> getByMaintenanceEndDate(@PathVariable("mEndDate") String mEndDate) {
		List<Maintenance> maintenanceList = contractorService.getByMaintenanceEndDate(LocalDate.parse(mEndDate));
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceStatus/INPROGRESS
	@GetMapping("/contracts/maintenanceStatus/{mStatus}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStatus(@PathVariable("mStatus") Status mStatus) {
		List<Maintenance> maintenanceList = contractorService.getByMaintenanceStatus(mStatus);
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenancePriority/HIGH
	@GetMapping("/contracts/maintenancePriority/{mPriority}")
	ResponseEntity<List<Maintenance>> getByMaintenancePriority(@PathVariable("mPriority") Priority mPriority) {
		List<Maintenance> maintenanceList = contractorService.getByMaintenancePriority(mPriority);
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceStartDate/2021-10-12/maintenanceEndDate/2021-10-22
	@GetMapping("/contracts/maintenanceStartDate/{mStartDate}/maintenanceEndDate/{mEndDate}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStartAndEndDate(@PathVariable("mStartDate") String mStartDate,
			@PathVariable("mEndDate") String mEndDate) {
		List<Maintenance> maintenanceList = contractorService
				.getByMaintenanceStartAndEndDate(LocalDate.parse(mStartDate), LocalDate.parse(mEndDate));
		return ResponseEntity.accepted().body(maintenanceList);

	}

	// http://localhost:8070/contractor-service/contracts/maintenanceStatus/INPROGRESS/maintenancePriority/HIGH
	@GetMapping("/contracts/maintenanceStatus/{mStatus}/maintenancePriority/{mPriority}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStatusAndPriority(@PathVariable("mStatus") Status mStatus,
			@PathVariable("mPriority") Priority mPriority) {

		List<Maintenance> maintenanceList = contractorService.getByMaintenanceStatusAndPriority(mStatus, mPriority);
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceName/Cleaning/maintenanceStatus/INPROGRESS
	@GetMapping("/contracts/maintenanceName/{maintenanceName}/maintenanceStatus/{mStatus}")
	ResponseEntity<Maintenance> getByMaintenanceNameAndStatus(@PathVariable("maintenanceName") String maintenanceName,
			@PathVariable("mStatus") Status mStatus) {
		Maintenance maintenance = contractorService.getByMaintenanceNameAndStatus(maintenanceName, mStatus);
		return ResponseEntity.accepted().body(maintenance);
	}

	// http://localhost:8070/contractor-service/contracts/maintenanceName/Cleaning/maintenancePriority/HIGH
	@GetMapping("/contracts/maintenanceName/{maintenanceName}/maintenancePriority/{mPriority}")
	ResponseEntity<Maintenance> getByMaintenanceNameAndPriority(@PathVariable("maintenanceName") String maintenanceName,
			@PathVariable("mPriority") Priority mPriority) {
		Maintenance maintenance = contractorService.getByMaintenanceNameAndPriority(maintenanceName, mPriority);
		return ResponseEntity.accepted().body(maintenance);
	}

	// http://localhost:8070/contractor-service/contracts/maintenance
	@GetMapping("contracts/maintenance")
	ResponseEntity<List<Maintenance>> getAllMaintenance() {
		List<Maintenance> maintenanceList = contractorService.getAllMaintenance();
		return ResponseEntity.accepted().body(maintenanceList);
	}

}
