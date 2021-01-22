package gov.naco.soch.lfu.projection;

import java.time.LocalDate;

/**
 * @author Namitha Kakkanat U86524
 *
 */
public interface ArtLfuFinalPredictionProjection {

	//artLfuFinalPredictionid
	Long getArtLfuFinalPredictionid();
	void setArtLfuFinalPredictionid(Long artLfuFinalPredictionid);
	
	//beneficiaryId
	Long getBeneficiaryId();
	void setBeneficiaryId(Long beneficiaryId);
	
	//uid
	String getUId();
	void setUId(String uid);
	
	//beneficiaryName
	String getBeneficiaryName();
	void setBeneficiaryName(String beneficiaryName);
	
	//gender
	String getGender();
	void setGender(String gender);
	
	//age
	String getAge();
	void setAge(String age);
	
	//lfuScore
	Long getLfuScore();
	void setLfuScore(Long lfuScore);
	
	//nextAppointmentDate
	LocalDate getNextAppointmentDate();
	void setNextAppointmentDate(LocalDate nextAppointmentDate);
	
	
	
}
