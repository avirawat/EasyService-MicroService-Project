package com.easyservice.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

	@OneToOne
	@JoinColumn(name="task_id")
	@JsonIgnore
	Task task;

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
				+ ", workType=" + workType + ", workDuration=" + workDuration + ", task=" + task + "]";
	}

}
