package com.easyservice.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
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
public class Maintenance {
	


	@Id
	@GeneratedValue(generator="maintenance_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name="maintenance_gen",sequenceName = "maintenance_seq",initialValue = 100,allocationSize = 1)
	private Integer maintenanceId;
	@Column(length = 20)
	private String maintenanceName;
	@Column(length = 20)
	private String maintenanceManager;
	private LocalDate mStartDate;
	private LocalDate mEndDate;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 8)
	private Status mStatus;
	
	@Enumerated(EnumType.STRING)
    @Column(length = 8)
	private Priority mPriority;
	
	@ManyToOne
	@JoinColumn(name="contract_id")
	@JsonIgnore
	Contractor contractor;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "maintenance_id")
	//@JsonIgnore
	Set<Task> taskList;

	public Maintenance(String maintenanceName, String maintenanceManager, LocalDate mStartDate, LocalDate mEndDate,
			Status mStatus, Priority mPriority, Set<Task> taskList) {
		super();
		this.maintenanceName = maintenanceName;
		this.maintenanceManager = maintenanceManager;
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.mStatus = mStatus;
		this.mPriority = mPriority;
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "Maintenance [maintenanceName=" + maintenanceName + ", maintenanceManager=" + maintenanceManager
				+ ", mStartDate=" + mStartDate + ", mEndDate=" + mEndDate + ", mStatus=" + mStatus + ", mPriority="
				+ mPriority + "]";
	}
	
	

	
	
}

