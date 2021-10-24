package com.easyservice.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyservice.model.Status;
import com.easyservice.model.Task;

/**
 * @author AvinashRavat
 *
 */
@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {

	@Query("from Task where maintenance_id=?1")
	Task findByMaintenanceId(int maintenanceId);

	@Query("from Task where organiser=?1 and tStatus=?2")
	Task findByOrganiserAndTStatus(String organiser, Status tStatus);

	@Query("from Task where tStartDate<=?1 and tEndDate>=?2")
	List<Task> findByTaskStartDateAndEndDate(LocalDate tStartDate, LocalDate tEndDate);
	
	@Query("from Task t inner join t.worker w where t.organiser=?1 and w.workerName=?2")
	List<Task> findWorkerByOrganiserName(String organiser,String workerName);
	
	@Query("from Task t inner join t.worker w where w.workerName=?1")
	List<Task> findByWorkerName(String workerName);
}
