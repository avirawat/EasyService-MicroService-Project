/**
 *
 */
package com.easyservice.repository;

/**
 * @author TharunyaREDDY
 *
 */
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyservice.model.Contractor;
import com.easyservice.model.Maintenance;

@Repository
public interface IContractorRepository extends JpaRepository<Contractor, Integer> {

	// contractor

	Contractor findByContractId(Integer contractId);

	Contractor findByContractName(String contractName);

	Contractor findByContractorName(String contractorName);

	List<Contractor> findByStartDate(LocalDate startDate);

	List<Contractor> findByEndDate(LocalDate endDate);

	@Query("from Contractor c where c.startDate=?1 and c.endDate=?2")
	List<Contractor> findByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);

	@Query("from Maintenance m inner join m.contractor c where c.contractName=?1")
	List<Maintenance> findMaintenanceByContractName(String contractName);

	@Query("from Maintenance m inner join m.contractor c where c.contractorName=?1")
	List<Maintenance> findMaintenanceByContractorName(String contractorName);

	@Query("from Contractor c inner join c.maintenanceList m where m.maintenanceManager=?1")
	List<Contractor> findContractNameByMaintenanceManager(String maintenanceManager);

	@Query("from Contractor c inner join c.maintenanceList m where m.maintenanceName=?1")
	List<Contractor> findContractsByMaintenanceName(String maintenanceName);

}
