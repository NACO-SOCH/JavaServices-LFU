package gov.naco.soch.lfu.lfurepository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.naco.soch.lfu.domains.ArtLfuFinalPredictions;
import gov.naco.soch.lfu.projection.ArtLfuFinalPredictionProjection;


@Repository
public interface ArtLfuFinalPredictionsRepository extends JpaRepository<ArtLfuFinalPredictions, Long> {

	
	@Query(nativeQuery = true, value = " select id as artLfuFinalPredictionid, beneficiary_id as beneficiaryId, " + 
			" uid as uid, beneficiary_name as beneficiaryName, sex as gender, age as age, " + 
			" lfu_score as lfuScore, next_appointment_date as nextAppointmentDate " + 
			" from sochml.art_lfu_final_predictions " + 
			" where facility_id = :facilityId  and " +
			" next_appointment_date BETWEEN :fromDate AND :toDate order by next_appointment_date asc ", countQuery=" select count(id) " + 
					" from sochml.art_lfu_final_predictions " + 
					" where facility_id = :facilityId  and next_appointment_date BETWEEN :fromDate AND :toDate ")
	Page<ArtLfuFinalPredictionProjection> findLfuBenficiaryListWithDate(@Param("facilityId") Long facilityId,@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate,  Pageable pageable);

	@Query(nativeQuery = true, value = " select count(id) " + 
			" from sochml.art_lfu_final_predictions " + 
			" where facility_id = :facilityId  and next_appointment_date BETWEEN :fromDate AND :toDate ")
	Long getActualCountLfuPredictionList(@Param("facilityId") Long facilityId,@Param("fromDate") LocalDate fromDate,
			@Param("toDate") LocalDate toDate);

	

}
