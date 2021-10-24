/**
 *
 */
package com.easyservice.model;

/**
 * @author TharunyaREDDY
 *
 */
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

@Entity
public class Contractor {

	@Id
	@GeneratedValue(generator = "contractor_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "contractor_gen", sequenceName = "contractor_seq", initialValue = 1, allocationSize = 1)
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

	public Contractor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Set<Maintenance> getMaintenanceList() {
		return maintenanceList;
	}

	public void setMaintenanceList(Set<Maintenance> maintenanceList) {
		this.maintenanceList = maintenanceList;
	}

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
