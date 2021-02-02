package gov.naco.soch.lfu.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import gov.naco.soch.lfu.dto.ArtLfuFinalPredictionDto;
import gov.naco.soch.lfu.dto.ArtLfuFinalPredictionResponseDto;
import gov.naco.soch.lfu.dto.LoginResponseDto;
import gov.naco.soch.lfu.lfurepository.ArtLfuFinalPredictionsRepository;
import gov.naco.soch.lfu.projection.ArtLfuFinalPredictionProjection;
import gov.naco.soch.lfu.projection.ArtLfuPredictionDateProjection;
import gov.naco.soch.lfu.util.UserUtils;

//import gov.naco.soch.cst.dto.ArtLfuFinalPredictionDto;
//import gov.naco.soch.cst.dto.ArtLfuFinalPredictionResponseDto;
//import gov.naco.soch.cst.dto.SacepReferralDto;
//import gov.naco.soch.projection.ArtLfuFinalPredictionProjection;
//import gov.naco.soch.projection.SacepReferralProjection;
//import gov.naco.soch.repository.ArtLfuFinalPredictionsRepository;

/**
 * @author Namitha Kakkanat U86524
 *
 */
@Transactional
@Service
public class ArtLfuFinalPredictionsService {

	@Autowired
	private ArtLfuFinalPredictionsRepository artLfuFinalPredictionsRepository;

	@Value("${exportRecordsLimit}")
	private Integer exportRecordsLimit;

	private static final Logger logger = LoggerFactory.getLogger(ArtLfuFinalPredictionsService.class);

	public ArtLfuFinalPredictionResponseDto getLfuBeneficiaryFinalPredictionsList(LocalDate fromDate, LocalDate toDate,
			Integer pageNumber, Integer pageSize) {
		logger.debug(
				"Entering into getLfuBeneficiaryFinalPredictionsList method --- in ArtLfuFinalPredictionsService ");
		if (pageNumber == null || pageSize == null) {
			pageNumber = 0;
			pageSize = exportRecordsLimit;
		}
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		ArtLfuFinalPredictionResponseDto artLfuFinalPredictionResponseDto = new ArtLfuFinalPredictionResponseDto();
		List<ArtLfuFinalPredictionDto> artLfuFinalPredictionDtoList = new ArrayList<ArtLfuFinalPredictionDto>();
		Page<ArtLfuFinalPredictionProjection> artLfuFinalPredictionProjection = null;
		List<ArtLfuFinalPredictionProjection> artLfuFinalPredictionProjectionList = new ArrayList<ArtLfuFinalPredictionProjection>();
		List<ArtLfuPredictionDateProjection> artLfuFinalPredictionDateProjections = new ArrayList<ArtLfuPredictionDateProjection>();
		Optional<List> predictionListOptional =null;
		Long actualCount =null;
		LoginResponseDto currentUser = UserUtils.getLoggedInUserDetails();
		Long facilityId = currentUser.getFacilityId();
		artLfuFinalPredictionDateProjections = artLfuFinalPredictionsRepository.findPredictionDate();
		if(!CollectionUtils.isEmpty(artLfuFinalPredictionDateProjections)) {
			artLfuFinalPredictionDateProjections.forEach(row ->{
				artLfuFinalPredictionResponseDto.setPredictionFromDate(row.getPredictionFromDate());
				artLfuFinalPredictionResponseDto.setPredictionToDate(row.getPredictionToDate());
			});
		}
		if (fromDate != null && toDate != null) {
			artLfuFinalPredictionProjection = artLfuFinalPredictionsRepository.findLfuBenficiaryListWithAppointmentDate(artLfuFinalPredictionResponseDto.getPredictionFromDate(),artLfuFinalPredictionResponseDto.getPredictionToDate(),facilityId,fromDate,toDate, pageable);
			actualCount=artLfuFinalPredictionsRepository.getActualCountLfuPredictionListWithAppointmentDate(artLfuFinalPredictionResponseDto.getPredictionFromDate(),artLfuFinalPredictionResponseDto.getPredictionToDate(),facilityId,fromDate,toDate);
		
		} else {
		/*	fromDate = LocalDate.now();
			toDate = LocalDate.now().plusDays(7);*/
			artLfuFinalPredictionProjection = artLfuFinalPredictionsRepository.findLfuBenficiaryListWithoutAppointmentDate(artLfuFinalPredictionResponseDto.getPredictionFromDate(),artLfuFinalPredictionResponseDto.getPredictionToDate(),facilityId,pageable);
			actualCount=artLfuFinalPredictionsRepository.getActualCountLfuPredictionListWithoutAppointmentDate(artLfuFinalPredictionResponseDto.getPredictionFromDate(),artLfuFinalPredictionResponseDto.getPredictionToDate(),facilityId);
		}
		

		predictionListOptional =  Optional.ofNullable(artLfuFinalPredictionProjection.getContent());
		if(predictionListOptional!=null && predictionListOptional.isPresent()) {
			artLfuFinalPredictionProjectionList =predictionListOptional.get();
		}
		
		if (!CollectionUtils.isEmpty(artLfuFinalPredictionProjectionList)) {
			artLfuFinalPredictionProjectionList.forEach(row -> {
				ArtLfuFinalPredictionDto artLfuFinalPredictionDto = new ArtLfuFinalPredictionDto();
				artLfuFinalPredictionDto.setArtLfuFinalPredictionid(row.getArtLfuFinalPredictionid());
				artLfuFinalPredictionDto.setBeneficiaryId(row.getBeneficiaryId());
				artLfuFinalPredictionDto.setAge(row.getAge());
				artLfuFinalPredictionDto.setBeneficiaryName(row.getBeneficiaryName());
				artLfuFinalPredictionDto.setGender(row.getGender());
				artLfuFinalPredictionDto.setLfuScore(row.getLfuScore());
				artLfuFinalPredictionDto.setNextAppointmentDate(row.getNextAppointmentDate());
				artLfuFinalPredictionDto.setUid(row.getUId());
				artLfuFinalPredictionDtoList.add(artLfuFinalPredictionDto);
			});
			//artLfuFinalPredictionResponseDto.setTotalcount(artLfuFinalPredictionProjection.getTotalElements());
			
		}
		artLfuFinalPredictionResponseDto.setArtLfuFinalPredictionDto(artLfuFinalPredictionDtoList);
		artLfuFinalPredictionResponseDto.setCurrentCount(artLfuFinalPredictionDtoList.size());
		artLfuFinalPredictionResponseDto.setTotalcount(actualCount);
		artLfuFinalPredictionResponseDto.setPageSize(pageSize);
		artLfuFinalPredictionResponseDto.setPageNumber(pageNumber);
		return artLfuFinalPredictionResponseDto;
	}
}
