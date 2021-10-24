package com.easyservice.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Task {
	
	@Id
	@GeneratedValue(generator = "task_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name="task_gen",sequenceName = "task_seq",initialValue = 200,allocationSize = 1)
	private Integer taskId;
	@Column(length = 20)
	private String taskName;
	@Column(length = 20)
	private String organiser;
	private LocalDate tStartDate;
	private LocalDate tEndDate;
	private Integer durationDays;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 20)
	private Status tStatus;
	
	@ManyToOne
	@JoinColumn(name="maintenance_id")
	//@JsonIgnore
	Maintenance maintenance;
	

	public Task(String taskName, String organiser, LocalDate tStartDate, LocalDate tEndDate, Integer durationDays,
			Status tStatus) {
		super();
		this.taskName = taskName;
		this.organiser = organiser;
		this.tStartDate = tStartDate;
		this.tEndDate = tEndDate;
		this.durationDays = durationDays;
		this.tStatus = tStatus;
	}


	@Override
	public String toString() {
		return "Task [taskName=" + taskName + ", organiser=" + organiser + ", tStartDate=" + tStartDate + ", tEndDate="
				+ tEndDate + ", durationDays=" + durationDays + ", tStatus=" + tStatus + "]";
	}
	
	
}
