package com.easyservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyservice.exception.WorkerNotFoundException;
import com.easyservice.model.Worker;



/**
 * @author AvinashRavat
 *
 */
@Repository
public interface IWorkRepository extends JpaRepository<Worker,Integer>{
	
	List<Worker> findByWorkerName(String workerName);
	
	List<Worker> findByWorkDuration(int workDuration) throws WorkerNotFoundException;
	
	@Query("From Worker where wStatus=?1 and workType=?2")
	List<Worker> findByWStatusAndWorkType(String status, String workType) throws WorkerNotFoundException;
	
	@Query("From Worker where workDuration=?1 and workType=?2")
	List<Worker> findByDurationAndWorkType(int workDuration, String workType) throws WorkerNotFoundException;
	
	@Query("From Worker where wStatus=?1")
	List<Worker> findByAvailability(String availability);
	
	@Query("From Worker w inner join w.task t where t.taskName=?1")
	List<Worker> findByTaskName(String taskName);

}
