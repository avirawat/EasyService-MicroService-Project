package com.easyservice.model;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Contractor {
	
	@Id
	@GeneratedValue(generator="contractor_gen",strategy = GenerationType.AUTO)
	@SequenceGenerator(name="contractor_gen",sequenceName = "contractor_seq",initialValue = 1,allocationSize = 1)
	private Integer contractId;
	@Column(length = 20)
	private String contractName;
	@Column(length = 20)
	private String contractorName;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "contract_id")
	@JsonIgnore
	Set<Maintenance> maintenanceList;
	
	public Contractor(String contractName, String contractorName, LocalDate startDate, LocalDate endDate,
			Set<Maintenance> maintenanceList) {
		super();
		this.contractName = contractName;
		this.contractorName = contractorName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.maintenanceList = maintenanceList;
	}


	@Override
	public String toString() {
		return "Contractor [contractName=" + contractName + ", contractorName=" + contractorName + ", startDate="
				+ startDate + ", endDate=" + endDate + "]";
	}




	
	
}
