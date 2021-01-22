package gov.naco.soch.lfu.domains;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The persistent class for the art_lfu_final_predictions database table.
 * 
 */

@GenericGenerator(name = "art_lfu_final_predictions", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
		@Parameter(name = "sequence_name", value = "art_followup_list_id_seq"),
		@Parameter(name = "increment_size", value = "1"), @Parameter(name = "optimizer", value = "hilo") })
@Entity
@Table(name = "art_lfu_final_predictions")
@NamedQuery(name = "ArtLfuFinalPredictions.findAll", query = "SELECT a FROM ArtLfuFinalPredictions a")
public class ArtLfuFinalPredictions  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "art_lfu_final_predictions")
	private Long id;

	@Column(name = "beneficiary_id")
	private Long beneficiaryId;

	@Column(name = "uid")
	private Long uId;
	
	@Column(name = "beneficiary_name")
	private String beneficiaryName;

	@Column(name="facility_id")
	private Long facilityId;
	
	@Column(name="age")
	private Long age;

	@Column(name="sex")
	private String sex;
	
	@Column(name="lfu_score")
	private Long lfuScore;
	
	@Column(name="next_appointment_date")
	private LocalDate  nextAppointmentDate;
	
	@Column(name="prediction_from_date ")
	private LocalDate  predictionFromDate ;
	
	@Column(name="prediction_to_date ")
	private LocalDate  predictionToDate ;
	
	@Column(name="reported_year")
	private Long  reportedYear;
	
	@Column(name="reported_month")
	private Long  reportedMonth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(Long beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public LocalDate getPredictionFromDate() {
		return predictionFromDate;
	}

	public void setPredictionFromDate(LocalDate predictionFromDate) {
		this.predictionFromDate = predictionFromDate;
	}

	public LocalDate getPredictionToDate() {
		return predictionToDate;
	}

	public void setPredictionToDate(LocalDate predictionToDate) {
		this.predictionToDate = predictionToDate;
	}

	public Long getReportedYear() {
		return reportedYear;
	}

	public void setReportedYear(Long reportedYear) {
		this.reportedYear = reportedYear;
	}

	public Long getReportedMonth() {
		return reportedMonth;
	}

	public void setReportedMonth(Long reportedMonth) {
		this.reportedMonth = reportedMonth;
	}



	@Override
	public String toString() {
		return "ArtLfuFiinalPredictions [id=" + id + ", beneficiaryId=" + beneficiaryId + ", uId=" + uId
				+ ", beneficiaryName=" + beneficiaryName + ", facilityId=" + facilityId + ", age=" + age + ", sex="
				+ sex + ", lfuScore=" + lfuScore + ", nextAppointmentDate=" + nextAppointmentDate
				+ ", predictionFromDate=" + predictionFromDate + ", predictionToDate=" + predictionToDate
				+ ", reportedYear=" + reportedYear + ", reportedMonth=" + reportedMonth + "]";
	}
	
	
	
	
}
