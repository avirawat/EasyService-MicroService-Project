package com.easyservice.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.easyservice.exception.TaskNotFoundException;
import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Status;
import com.easyservice.model.Task;
import com.easyservice.model.Worker;
import com.easyservice.repository.ITaskRepository;

/**
 * @author AvinashRavat
 *
 */
@Service
@Transactional
public class TaskServiceImpl implements ITaskService {

	@Autowired
	RestTemplate restTemplate;

	private static final String BASEURL = "http://localhost:8073/work-service/work";

	@Autowired
	ITaskRepository taskRepository;

	@Override
	public Task addTask(Task task) {
		return taskRepository.save(task);

	}

	@Override
	public void deleteTask(int taskId) {
		taskRepository.deleteById(taskId);

	}

	@Override
	public void updateTask(Task task) {
		taskRepository.save(task);

	}

	@Override
	public Task getById(int taskId) {
		return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException("Task Id not Found"));
	}

	@Override
	public List<Task> getAllTask() {
		return taskRepository.findAll();
	}

	@Override
	public Task getByMaintenanceId(int maintenanceId) throws WorkerNotFoundException {
		Task taskByMaintenanceId = taskRepository.findByMaintenanceId(maintenanceId);
		if (taskByMaintenanceId == null) {
			throw new WorkerNotFoundException("Id Not Found");
		}
		return taskByMaintenanceId;

	}

	@Override
	public Task getByOrganiserAndStatus(String organiser, Status tStatus) throws TaskNotFoundException {
		Task taskByOrganiserAndStatus = taskRepository.findByOrganiserAndTStatus(organiser, tStatus);
		if (taskByOrganiserAndStatus == null) {
			throw new TaskNotFoundException("Organiser Not Found");
		}
		return taskByOrganiserAndStatus;
	}

	@Override
	public List<Task> getByTaskStartDateAndEndDate(LocalDate tStartDate, LocalDate tEndDate)
			throws TaskNotFoundException {
		List<Task> taskByTaskStartDateAndEndDate = taskRepository.findByTaskStartDateAndEndDate(tStartDate, tEndDate);
		if (taskByTaskStartDateAndEndDate.isEmpty()) {
			throw new TaskNotFoundException("Organiser Not Found");
		}
		return taskByTaskStartDateAndEndDate;
	}

	// Worker Table

	@Override
	public List<Worker> getByWorkerName(String workerName) {
		String url = BASEURL + "/workerName/" + workerName;
		ResponseEntity<List> responseByWorkerName = restTemplate.getForEntity(url,List.class);
		return responseByWorkerName.getBody();
	}

	@Override
	public List<Worker> getByStatusAndWorkType(String status, String workType) {
		String url = BASEURL + "/status/" + status + "/workType/" + workType;
		ResponseEntity<List> responseByStatusAndWorkType = restTemplate.getForEntity(url,List.class);
		return responseByStatusAndWorkType.getBody();
	}

	@Override
	public List<Worker> getByDurationAndWorkType(int workDuration, String workType) {
		String url = BASEURL + "/workDuration/" + workDuration + "/workType/" + workType;
		ResponseEntity<List> responseByDurationAndWorkType = restTemplate.getForEntity(url,List.class);
		return responseByDurationAndWorkType.getBody();
	}

	@Override
	public List<Worker> allWorkerList() {
		String url = BASEURL;
		ResponseEntity<List> responseAllWorker = restTemplate.getForEntity(url, List.class);
		return responseAllWorker.getBody();
	}

	@Override
	public List<Worker> getByAvailability(String availability) throws WorkerNotFoundException {
		String url = BASEURL + "/avl/" + availability;
		ResponseEntity<List> responseWorkerByAvailability = restTemplate.getForEntity(url, List.class);
		return responseWorkerByAvailability.getBody();
	}

	@Override
	public Worker getByWorkerId(int workerId) throws WorkerNotFoundException {
		String url = BASEURL + "/" + workerId;
		ResponseEntity<Worker> responseByWorkerId = restTemplate.getForEntity(url, Worker.class);
		return responseByWorkerId.getBody();
	}

	@Override
	public Worker assignWorkToWorker(Worker worker, int taskId) throws TaskNotFoundException{
		Task task = taskRepository.findById(taskId).get();
		if(task==null) {
			throw new TaskNotFoundException("Task Id not Found ,Task Can not be assigned");
		}
		worker.setTask(task);
		worker.setWStatus("NA");
		String url = BASEURL;
		restTemplate.put(url, worker);
		return worker;
	}

	@Override
	public Worker unAssignWorkToWorker(Worker worker) {
		String url = BASEURL;
		worker.setWStatus("A");
		restTemplate.put(url, worker);
		return worker;
	}

	@Override
	public List<Task> getWorkerByOrganiserName(String organiser,String workerName) throws WorkerNotFoundException {
		List<Task> taskByOrganiserNameANdWorkerName=taskRepository.findWorkerByOrganiserName(organiser,workerName);
		if(taskByOrganiserNameANdWorkerName.isEmpty()) {
			throw new WorkerNotFoundException("worker not found");
		}
		return taskByOrganiserNameANdWorkerName;
	}

	@Override
	public List<Task> getByWorkersName(String workerName) throws WorkerNotFoundException {
		List<Task> taskByWorkerName=taskRepository.findByWorkerName(workerName);
		if(taskByWorkerName.isEmpty()) {
			throw new WorkerNotFoundException("worker not found");
		}
		return taskByWorkerName;
	}

}
