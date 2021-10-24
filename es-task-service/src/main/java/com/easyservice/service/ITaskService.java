package com.easyservice.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import com.easyservice.exception.TaskNotFoundException;
import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.model.Worker;

/**
 * @author AvinashRavat
 *
 */
@Service
public interface ITaskService {

	// Task-Functions
	
	Task addTask(Task task);

	void deleteTask(int taskId) throws TaskNotFoundException;

	void updateTask(Task task);

	Task getById(int taskId) throws TaskNotFoundException;

	Task getByMaintenanceId(int maintenanceId) throws TaskNotFoundException;

	Task getByOrganiserAndStatus(String organiser, Status tStatus) throws TaskNotFoundException;
	
	List<Task> getAllTask();

	List<Task> getByTaskStartDateAndEndDate(LocalDate tStartDate, LocalDate tEndDate) throws TaskNotFoundException;
	
	List<Task> getWorkerByOrganiserName(String organiser,String workerName)throws WorkerNotFoundException;
	
	List<Task> getByWorkersName(String workerName)throws WorkerNotFoundException;

	// Worker-Functions
	
	Worker getByWorkerId(int workerId) throws WorkerNotFoundException;
	
	Worker assignWorkToWorker(Worker worker,int taskId) throws TaskNotFoundException;
	
	Worker unAssignWorkToWorker(Worker worker);
	
	List<Worker> getByWorkerName(String workerName) throws WorkerNotFoundException;

	List<Worker> getByStatusAndWorkType(String status, String workType) throws WorkerNotFoundException;

	List<Worker> getByDurationAndWorkType(int workDuration, String workType) throws WorkerNotFoundException;
	
	List<Worker> allWorkerList();
	
	List<Worker> getByAvailability(String availability) throws WorkerNotFoundException;
	
	
}
