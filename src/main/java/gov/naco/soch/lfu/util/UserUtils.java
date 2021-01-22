package gov.naco.soch.lfu.util;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import gov.naco.soch.lfu.dto.LoginResponseDto;
import gov.naco.soch.lfu.security.JwtTokenUtil;
import gov.naco.soch.lfu.security.UserAuthenticationToken;
import io.jsonwebtoken.Claims;

@Component
public class UserUtils {

	private static JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtTokenUtil tokenUtil;

	@PostConstruct
	public void init() {
		UserUtils.jwtTokenUtil = tokenUtil;
	}

	@SuppressWarnings("unchecked")
	public static LoginResponseDto getLoggedInUserDetails() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String token = ((UserAuthenticationToken) authentication).getUserToken();
		LoginResponseDto loginUserDetails = new LoginResponseDto();
		String username = jwtTokenUtil.getUsernameFromToken(token);

		Claims tokenClaims = jwtTokenUtil.getClaimsFromToken(token);
		List<String> accessCodes = (List<String>) tokenClaims.get("accessCodes");
		Long userId = ((Integer) tokenClaims.get("userId")).longValue();
		String firstname = (String) tokenClaims.get("firstname");
		String lastname = (String) tokenClaims.get("lastname");
		Long roleId = ((Integer) tokenClaims.get("roleId")).longValue();
		String roleName = (String) tokenClaims.get("roleName");
		Long divisionId = ((Integer) tokenClaims.get("divisionId")).longValue();
		Long facilityTypeId = ((Integer) tokenClaims.get("facilityTypeId")).longValue();
		Optional<Integer> facilityIdOpt = Optional.ofNullable((Integer) tokenClaims.get("facilityId"));
		Long facilityId = facilityIdOpt.isPresent() ? facilityIdOpt.get().longValue() : null;
		if(tokenClaims.get("designationId")!=null) {
			Long designationId = ((Integer) tokenClaims.get("designationId")).longValue();
			String designation = ((String) tokenClaims.get("designation"));			
			loginUserDetails.setDesignationId(designationId);
			loginUserDetails.setDesignation(designation);
		}
		
		loginUserDetails.setUserId(userId);
		loginUserDetails.setFirstname(firstname);
		loginUserDetails.setLastname(lastname);
		loginUserDetails.setDivisionId(divisionId);
		loginUserDetails.setFacilityTypeId(facilityTypeId);
		loginUserDetails.setFacilityId(facilityId);
		loginUserDetails.setUserName(username);
		loginUserDetails.setRoleId(roleId);
		loginUserDetails.setRoleName(roleName);
		loginUserDetails.setAccessCodes(accessCodes);
		loginUserDetails.setToken(token);
		return loginUserDetails;
	}

}
