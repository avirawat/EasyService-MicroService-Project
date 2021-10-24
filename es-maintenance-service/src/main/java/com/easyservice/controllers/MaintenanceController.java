package com.easyservice.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import com.easyservice.exception.MaintenanceNotFoundException;
import com.easyservice.exception.TaskNotFoundException;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.service.IMaintenanceService;

/**
 * @author GAYATHRI S
 *
 */

@RestController
@RequestMapping("/maintenance-service")
public class MaintenanceController {

	IMaintenanceService maintenanceService;

	@Autowired
	public void setMaintenanceService(IMaintenanceService maintenanceService) {
		this.maintenanceService = maintenanceService;
	}

	Maintenance maintenances = new Maintenance();
	List<Maintenance> maintenanceList = null;
	Task task = new Task();

	//adding maintenance
	@PostMapping("/maintenance")
	ResponseEntity<Maintenance> addMaintenance(@RequestBody Maintenance maintenance) {
		maintenances = maintenanceService.addMaintenance(maintenance);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Maintenance added");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(maintenances);
	}

	//update maintenance
	@PutMapping("/maintenance")
	ResponseEntity<String> updateMaintenance(@RequestBody Maintenance maintenance) {
		maintenanceService.updateMaintenance(maintenance);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated !!!");
	}

	//delete maintenance
	@DeleteMapping("/maintenance/{maintenanceId}")
	ResponseEntity<String> deleteMaintenance(@PathVariable("maintenanceId") int maintenanceId) {
		maintenanceService.deleteMaintenance(maintenanceId);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
	}

	//get by maintenance id
	@GetMapping("/maintenance/{maintenanceId}")
	ResponseEntity<Maintenance> getById(@PathVariable("maintenanceId") int maintenanceId) throws MaintenanceNotFoundException {
		maintenances = maintenanceService.getById(maintenanceId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get One Maintenance By Id");
		headers.add("info", "Returning one Maintenance");
		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(maintenances);
	}

	// http://localhost:8071/maintenance-service/maintenance
	// get all maintenance
	@GetMapping("/maintenance")
	ResponseEntity<List<Maintenance>> getAll() {
		maintenanceList = maintenanceService.getAll();
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get all maintenance");
		return ResponseEntity.accepted().headers(headers).body(maintenanceList);
	}

	//get by maintenanceName
	@GetMapping("/maintenance/maintenanceName/{maintenanceName}")
	ResponseEntity<Maintenance> getByMaintenanceName(@PathVariable("maintenanceName") String maintenanceName) {
		Maintenance maintenances = maintenanceService.getByMaintenanceName(maintenanceName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by maintenance name");
		return ResponseEntity.ok().headers(headers).body(maintenances);

	}

	//get by maintenance manager
	@GetMapping("/maintenance/maintenanceManager/{maintenanceManager}")
	ResponseEntity<Maintenance> getByMaintenanceManager(@PathVariable("maintenanceManager") String maintenanceManager) {
		Maintenance maintenances = maintenanceService.getByMaintenanceManager(maintenanceManager);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by maintenance name");
		return ResponseEntity.ok().headers(headers).body(maintenances);

	}

	//get by maintenance start date
	@GetMapping("/maintenance/maintenanceStartDate/{mStartDate}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStartDate(@PathVariable("mStartDate") String mStartDate) {
		maintenanceList = maintenanceService.getByMaintenanceStartDate(LocalDate.parse(mStartDate));
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by start date");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}

	//get by maintenance end date
	@GetMapping("/maintenance/maintenanceEndDate/{mEndDate}")
	ResponseEntity<List<Maintenance>> getByMaintenanceEndDate(@PathVariable("mEndDate") String mEndDate) {
		maintenanceList = maintenanceService.getByMaintenanceEndDate(LocalDate.parse(mEndDate));
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by end date");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}

	//get by maintenance status
	@GetMapping("/maintenance/maintenanceStatus/{mStatus}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStatus(@PathVariable("mStatus") Status mStatus) {
		maintenanceList = maintenanceService.getByMaintenanceStatus(mStatus);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by status");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}

	//get by maintenance priority
	@GetMapping("/maintenance/maintenancePriority/{mPriority}")
	ResponseEntity<List<Maintenance>> getByMaintenancePriority(@PathVariable("mPriority") Priority mPriority) {
		maintenanceList = maintenanceService.getByMaintenancePriority(mPriority);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by priority");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}

	//get by maintenance start date and end date
	@GetMapping("/maintenance/maintenanceStartDate/{mStartDate}/maintenanceEndDate/{mEndDate}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStartAndEndDate(@PathVariable("mStartDate") String mStartDate,
			@PathVariable("mEndDate") String mEndDate) throws MaintenanceNotFoundException {
		maintenanceList = maintenanceService.getByMaintenanceStartAndEndDate(LocalDate.parse(mStartDate),
				LocalDate.parse(mEndDate));
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by start date and end date");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}

	//get by maintenance status and priority
	@GetMapping("/maintenance/maintenanceStatus/{mStatus}/maintenancePriority/{mPriority}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStatusAndPriority(@PathVariable("mStatus") Status mStatus,
			@PathVariable("mPriority") Priority mPriority) throws MaintenanceNotFoundException {
		maintenanceList = maintenanceService.getByMaintenanceStatusAndPriority(mStatus, mPriority);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by status and priority");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}

	//get by maintenance name and status
	@GetMapping("/maintenance/maintenanceName/{maintenanceName}/maintenanceStatus/{mStatus}")
	ResponseEntity<Maintenance> getByMaintenanceNameAndStatus(@PathVariable("maintenanceName") String maintenanceName,
			@PathVariable("mStatus") Status mStatus) {
		maintenances = maintenanceService.getByMaintenanceNameAndStatus(maintenanceName, mStatus);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by status and name");
		return ResponseEntity.ok().headers(headers).body(maintenances);
	}

	//get by maintenance name and priority
	@GetMapping("/maintenance/maintenanceName/{maintenanceName}/maintenancePriority/{mPriority}")
	ResponseEntity<Maintenance> getByMaintenanceNameAndPriority(@PathVariable("maintenanceName") String maintenanceName,
			@PathVariable("mPriority") Priority mPriority) {
		maintenances = maintenanceService.getByMaintenanceNameAndPriority(maintenanceName, mPriority);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get by start date");
		return ResponseEntity.ok().headers(headers).body(maintenances);
	}
	
	//get by maintenance list by passing task duration
	@GetMapping("/maintenance/duration/{durationDays}")
	ResponseEntity<List<Maintenance>> getByDuration(@PathVariable("durationDays")int durationDays)throws MaintenanceNotFoundException{
		maintenanceList = maintenanceService.getByDuration(durationDays);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get maintenanceList by durations");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
    }
	
	//get by maintenance list by passing task name and manager
	@GetMapping("/maintenance/manager/{maintenanceManager}/taskName/{taskName}")
	ResponseEntity<List<Maintenance>> getByManagerTask(@PathVariable("maintenanceManager")String maintenanceManager,@PathVariable("taskName")String taskName){
		maintenanceList = maintenanceService.getByManagerTask(maintenanceManager, taskName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get maintenanceList by task");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}
	
	//get by maintenance list by passing organiser
	@GetMapping("/maintenance/organiser/{organiser}")
	ResponseEntity<List<Maintenance>> getByOrganiser(@PathVariable("organiser")String organiser)throws MaintenanceNotFoundException{
		maintenanceList = maintenanceService.getByOrganiser(organiser);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get maintenanceList by organiser");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}
	
	//get by maintenance list by passing task and duration
	@GetMapping("/maintenance/taskName/{taskName}/duration/{durationDays}")
	ResponseEntity<List<Maintenance>> getByNameAndDuration(@PathVariable("taskName")String taskName,@PathVariable("durationDays")int durationDays){
		maintenanceList = maintenanceService.getByNameAndDuration(taskName, durationDays);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get maintenanceList by taskname and duration");
		return ResponseEntity.ok().headers(headers).body(maintenanceList);
	}

	//add the task using maintenance by id
	@PostMapping("/task/maintenanceId/{maintenanceId}")
	ResponseEntity<Task> addTask(@RequestBody Task task, @PathVariable("maintenanceId") int maintenanceId) {
		task = maintenanceService.addTask(task, maintenanceId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Task added");
		return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(task);
	}

	//update the task
	@PutMapping("/task")
	ResponseEntity<String> updateTask(@RequestBody Task task) {
		System.out.println(task);
		maintenanceService.updateTask(task);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Update..");
	}

	//delete task
	@DeleteMapping("/task/{taskId}")
	ResponseEntity<String> deleteTask(@PathVariable("taskId") int taskId) {
		maintenanceService.deleteTask(taskId);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Successfully");
	}

	//get the task by id
	@GetMapping("/task/taskId/{taskId}")
	ResponseEntity<Task> getByTaskId(@PathVariable("taskId") int taskId) throws TaskNotFoundException {
		task = maintenanceService.getByTaskId(taskId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get One Task By Id");
		headers.add("info", "Returning one task");
		ResponseEntity<Task> taskResponse = new ResponseEntity<Task>(task, headers, HttpStatus.OK);
		return taskResponse;
	}

	//get all the task
	@GetMapping("/task")
	ResponseEntity<List<Task>> getAllTask() {
		List<Task> taskList = maintenanceService.getAllTask();
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get all tasks");
		return ResponseEntity.accepted().headers(headers).body(taskList);
	}

	//get all the task by maintenance id
	@GetMapping("/task/maintenanceId/{maintenanceId}")
	ResponseEntity<Task> getByMaintenanceId(@PathVariable("maintenanceId") int maintenanceId) throws MaintenanceNotFoundException {
		Task taskList = maintenanceService.getByMaintenanceId(maintenanceId);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get One Task By  maintenanceId");
		headers.add("info", "Returning one task");
		return ResponseEntity.accepted().headers(headers).body(taskList);
	}

	//get the task by organiser and its status
	@GetMapping("/task/organiser/{organiser}/tStatus/{tStatus}")
	ResponseEntity<Task> getByOrganiserAndStatus(@PathVariable("organiser") String organiser,
			@PathVariable("tStatus") String tStatus) {
		task = maintenanceService.getByOrganiserAndStatus(organiser, tStatus);
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get Tasklist By organiser and status");
		return ResponseEntity.ok().headers(headers).body(task);
	}

	//get the task by start date and end date
	@GetMapping("/task/tStartDate/{tStartDate}/tEndDate/{tEndDate}")
	ResponseEntity<List<Task>> getByTaskStartDateAndEndDate(@PathVariable("tStartDate") String tStartDate,
			@PathVariable("tEndDate") String tEndDate) throws TaskNotFoundException {
		List<Task> taskList = maintenanceService.getByTaskStartDateAndEndDate(LocalDate.parse(tStartDate),
				LocalDate.parse(tEndDate));
		HttpHeaders headers = new HttpHeaders();
		headers.add("desc", "Get Task By StartDate and EndDate");
		return ResponseEntity.ok().headers(headers).body(taskList);
	}

}
