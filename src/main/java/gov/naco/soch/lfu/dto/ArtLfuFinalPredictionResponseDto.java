package gov.naco.soch.lfu.dto;

import java.util.List;


/**
 * @author Namitha Kakkanat U86524
 *
 */

public class ArtLfuFinalPredictionResponseDto {

	private Long totalcount;
	private Integer currentCount;
	private Integer pageSize;
	private Integer pageNumber;
	private List<ArtLfuFinalPredictionDto> artLfuFinalPredictionDto;
	public Long getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Long totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(Integer currentCount) {
		this.currentCount = currentCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public List<ArtLfuFinalPredictionDto> getArtLfuFinalPredictionDto() {
		return artLfuFinalPredictionDto;
	}
	public void setArtLfuFinalPredictionDto(List<ArtLfuFinalPredictionDto> artLfuFinalPredictionDto) {
		this.artLfuFinalPredictionDto = artLfuFinalPredictionDto;
	}
	
	
}
