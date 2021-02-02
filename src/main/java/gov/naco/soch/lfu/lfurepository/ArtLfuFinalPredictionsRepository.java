package gov.naco.soch.lfu.lfurepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.naco.soch.lfu.domains.ArtLfuFinalPredictions;
import gov.naco.soch.lfu.projection.ArtLfuFinalPredictionProjection;
import gov.naco.soch.lfu.projection.ArtLfuPredictionDateProjection;

@Repository
public interface ArtLfuFinalPredictionsRepository extends JpaRepository<ArtLfuFinalPredictions, Long> {

	@Query(nativeQuery = true, value = " select max(created_time) as created_time, prediction_from_date as predictionFromDate, prediction_to_date as predictionToDate"
			+ " from sochml.art_lfu_final_predictions group by prediction_from_date , prediction_to_date,created_time order by created_time desc limit 1;")
	List<ArtLfuPredictionDateProjection> findPredictionDate();

	@Query(nativeQuery = true, value = " select id as artLfuFinalPredictionid, beneficiary_id as beneficiaryId, "
			+ " uid as uid, beneficiary_name as beneficiaryName, sex as gender, age as age, "
			+ " lfu_score as lfuScore, next_appointment_date as nextAppointmentDate "
			+ " from sochml.art_lfu_final_predictions "
			+ " where facility_id = :facilityId and prediction_from_date= :predictionFromDate and prediction_to_date= :predictionToDate "
			+ " and next_appointment_date BETWEEN :fromDate AND :toDate order by next_appointment_date asc ",
			 countQuery= " select count(id) "
						+ " from sochml.art_lfu_final_predictions "
						+ " where facility_id = :facilityId and prediction_from_date= :predictionFromDate and prediction_to_date= :predictionToDate "
						+ " and next_appointment_date BETWEEN :fromDate AND :toDate " )
	Page<ArtLfuFinalPredictionProjection> findLfuBenficiaryListWithAppointmentDate(
			@Param("predictionFromDate") LocalDate predictionFromDate,
			@Param("predictionToDate") LocalDate predictionToDate, @Param("facilityId") Long facilityId,
			@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, Pageable pageable);

	@Query(nativeQuery = true, value = " select count(id) "
			+ " from sochml.art_lfu_final_predictions "
			+ " where facility_id = :facilityId and prediction_from_date= :predictionFromDate and prediction_to_date= :predictionToDate "
			+ " and next_appointment_date BETWEEN :fromDate AND :toDate ")
	Long getActualCountLfuPredictionListWithAppointmentDate(@Param("predictionFromDate") LocalDate predictionFromDate,
			@Param("predictionToDate") LocalDate predictionToDate, @Param("facilityId") Long facilityId,
			@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);

	
	@Query(nativeQuery = true, value = " select id as artLfuFinalPredictionid, beneficiary_id as beneficiaryId, "
			+ " uid as uid, beneficiary_name as beneficiaryName, sex as gender, age as age, "
			+ " lfu_score as lfuScore, next_appointment_date as nextAppointmentDate "
			+ " from sochml.art_lfu_final_predictions "
			+ " where facility_id = :facilityId and prediction_from_date= :predictionFromDate and prediction_to_date= :predictionToDate order by next_appointment_date asc ",
			countQuery= " select count(id) from sochml.art_lfu_final_predictions "
					+ " where facility_id = :facilityId and prediction_from_date= :predictionFromDate and prediction_to_date= :predictionToDate ")
	Page<ArtLfuFinalPredictionProjection> findLfuBenficiaryListWithoutAppointmentDate(@Param("predictionFromDate") LocalDate predictionFromDate,
			@Param("predictionToDate") LocalDate predictionToDate, @Param("facilityId") Long facilityId, Pageable pageable);

	
	@Query(nativeQuery = true, value = " select count(id) from sochml.art_lfu_final_predictions "
			+ " where facility_id = :facilityId and prediction_from_date= :predictionFromDate and prediction_to_date= :predictionToDate ")
	Long getActualCountLfuPredictionListWithoutAppointmentDate(@Param("predictionFromDate") LocalDate predictionFromDate,
			@Param("predictionToDate") LocalDate predictionToDate, @Param("facilityId") Long facilityId);

}
