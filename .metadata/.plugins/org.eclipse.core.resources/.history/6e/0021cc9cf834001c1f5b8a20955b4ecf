/**
 *
 */
package com.easyservice.model;

/**
 * @author TharunyaREDDY
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Worker {

	@Id
	@GeneratedValue(generator = "worker_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "worker_gen", sequenceName = "worker_seq", initialValue = 300, allocationSize = 1)

	private Integer workerId;
	@Column(length = 20)
	private String workerName;
	@Column(length = 20)
	private String wStatus;
	@Column(length = 10)
	private String contactNumber;
	@Column(length = 20)
	private String workType;
	private Integer workDuration;

	@OneToOne()
	@JoinColumn(name = "task_id")

	Task task;

	public Worker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Integer workerId) {
		this.workerId = workerId;
	}

	public String getWorkerName() {
		return workerName;
	}

	public void setWorkerName(String workerName) {
		this.workerName = workerName;
	}

	public String getwStatus() {
		return wStatus;
	}

	public void setwStatus(String wStatus) {
		this.wStatus = wStatus;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Integer getWorkDuration() {
		return workDuration;
	}

	public void setWorkDuration(Integer workDuration) {
		this.workDuration = workDuration;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Worker(String workerName, String wStatus, String contactNumber, String workType, Integer workDuration) {
		super();
		this.workerName = workerName;
		this.wStatus = wStatus;
		this.contactNumber = contactNumber;
		this.workType = workType;
		this.workDuration = workDuration;
	}

	@Override
	public String toString() {
		return "Worker [workerName=" + workerName + ", wStatus=" + wStatus + ", contactNumber=" + contactNumber
				+ ", workType=" + workType + ", workDuration=" + workDuration + "]";
	}

}
