package gov.naco.soch.lfu.projection;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface ArtLfuPredictionDateProjection {

	// predictionToDate
	LocalDate getPredictionToDate();
	void setPredictionToDate(LocalDate predictionToDate);

	// predictionFromDate
	LocalDate getPredictionFromDate();
	void setPredictionFromDate(LocalDate predictionFromDate);
	
	//createdTime 
	LocalDateTime getCreatedTime();
	void setCreatedTime(LocalDateTime createdTime);
}
