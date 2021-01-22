package gov.naco.soch.lfu.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Namitha Kakkanat U86524 
 * 
 * Date : 06-01-2021
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gov.naco.soch.lfu.dto.ArtLfuFinalPredictionResponseDto;
import gov.naco.soch.lfu.service.ArtLfuFinalPredictionsService;

//import gov.naco.soch.cst.dto.ArtLfuFinalPredictionResponseDto;
//import gov.naco.soch.cst.dto.ArtPepDispensationListDto;
//import gov.naco.soch.cst.service.ArtLfuFinalPredictionsService;

@RestController
@RequestMapping("/lfufinalprediction")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ArtLfuFinalPredictionsController {

	private static final Logger logger = LoggerFactory.getLogger(ArtLfuFinalPredictionsController.class);

	@Autowired
	private ArtLfuFinalPredictionsService artLfuFinalPredictionsService;

	// LFU Pipeline Output - Display in ART Counsellor Screen - Potential LFU -
	// SOCH2-3209
	// LFU Beneficiary Listing API with normal search
	@GetMapping("/list")
	public @ResponseBody ArtLfuFinalPredictionResponseDto getLfuBeneficiaryFinalPredictionsList(
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fromDate,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
			@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
		logger.info("getLfuBeneficiaryFinalPredictionsList() invoked");
		ArtLfuFinalPredictionResponseDto artLfuFinalPredictionResponseDto = new ArtLfuFinalPredictionResponseDto();
		artLfuFinalPredictionResponseDto = artLfuFinalPredictionsService.getLfuBeneficiaryFinalPredictionsList(fromDate, toDate, pageNumber,
				pageSize);
		logger.info("getLfuBeneficiaryFinalPredictionsList() Returns->{}", artLfuFinalPredictionResponseDto);
		return artLfuFinalPredictionResponseDto;

	}

}
