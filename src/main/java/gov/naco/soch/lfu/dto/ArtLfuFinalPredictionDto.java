package gov.naco.soch.lfu.dto;

import java.time.LocalDate;


public class ArtLfuFinalPredictionDto  extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private Long artLfuFinalPredictionid;
	private Long beneficiaryId;
	private String uid;
	private String beneficiaryName;
	private String gender;
	private String age;
	private Long lfuScore;
	private LocalDate nextAppointmentDate;
	
	public Long getArtLfuFinalPredictionid() {
		return artLfuFinalPredictionid;
	}
	public void setArtLfuFinalPredictionid(Long artLfuFinalPredictionid) {
		this.artLfuFinalPredictionid = artLfuFinalPredictionid;
	}
	public Long getBeneficiaryId() {
		return beneficiaryId;
	}
	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public Long getLfuScore() {
		return lfuScore;
	}
	public void setLfuScore(Long lfuScore) {
		this.lfuScore = lfuScore;
	}
	public LocalDate getNextAppointmentDate() {
		return nextAppointmentDate;
	}
	public void setNextAppointmentDate(LocalDate nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}
	
	@Override
	public String toString() {
		return "ArtLfuFinalPredictionDto [artLfuFinalPredictionid=" + artLfuFinalPredictionid + ", beneficiaryId="
				+ beneficiaryId + ", uid=" + uid + ", beneficiaryName=" + beneficiaryName + ", gender=" + gender
				+ ", age=" + age + ", lfuScore=" + lfuScore + ", nextAppointmentDate=" + nextAppointmentDate + "]";
	}
	

}
