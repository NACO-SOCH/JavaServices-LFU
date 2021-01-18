package gov.naco.soch.lfu.dto;

import java.time.LocalDateTime;
import java.util.List;

public class LoginResponseDto {

	private String token;
	private String ssoSessionId;
	private Long userId;
	private String firstname;
	private String lastname;
	private String contact;
	private Long divisionId;
	private Long facilityTypeId;
	private Long facilityId;
	private String facilityName;
	private String facilityCode;
	private Long designationId;
	private String designation;
	private String userName;
	private Long roleId;
	private String roleName;
	private String facilityCbStatus;
	private String facilityNumber;
	private Long stateId;
	private String stateAlernateName;
	private Long districtId;
	private String districtAlernateName;
	private LocalDateTime lastLogin;
	private List<String> accessCodes;
	//private List<MenuItemDto> menuItems;
	private String activeToken;
	private LocalDateTime currentLoginTime;
	private Boolean showConfidentialityAgreement;
	private String email;
	private String stateName;
	private String districtName;
	private Long sacsId;

	public LoginResponseDto() {
		super();
	}

	public String getToken() {
		return token;
	}

	public String getSsoSessionId() {
		return ssoSessionId;
	}

	public void setSsoSessionId(String ssoSessionId) {
		this.ssoSessionId = ssoSessionId;
	}

	public Long getUserId() {
		return userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public Long getFacilityTypeId() {
		return facilityTypeId;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public String getFacilityCode() {
		return facilityCode;
	}

	public Long getDesignationId() {
		return designationId;
	}

	public String getDesignation() {
		return designation;
	}

	public String getUserName() {
		return userName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public List<String> getAccessCodes() {
		return accessCodes;
	}

//	public List<MenuItemDto> getMenuItems() {
//		return menuItems;
//	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

	public void setFacilityTypeId(Long facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

	public void setFacilityCode(String facilityCode) {
		this.facilityCode = facilityCode;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setAccessCodes(List<String> accessCodes) {
		this.accessCodes = accessCodes;
	}

//	public void setMenuItems(List<MenuItemDto> menuItems) {
//		this.menuItems = menuItems;
//	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getStateAlernateName() {
		return stateAlernateName;
	}

	public void setStateAlernateName(String stateAlernateName) {
		this.stateAlernateName = stateAlernateName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictAlernateName() {
		return districtAlernateName;
	}

	public void setDistrictAlernateName(String districtAlernateName) {
		this.districtAlernateName = districtAlernateName;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getFacilityCbStatus() {
		return facilityCbStatus;
	}

	public void setFacilityCbStatus(String facilityCbStatus) {
		this.facilityCbStatus = facilityCbStatus;
	}

	public LocalDateTime getCurrentLoginTime() {
		return currentLoginTime;
	}

	public void setCurrentLoginTime(LocalDateTime currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}

	public String getActiveToken() {
		return activeToken;
	}

	public void setActiveToken(String activeToken) {
		this.activeToken = activeToken;
	}

	public String getFacilityNumber() {
		return facilityNumber;
	}

	public void setFacilityNumber(String facilityNumber) {
		this.facilityNumber = facilityNumber;
	}

	public Boolean getShowConfidentialityAgreement() {
		return showConfidentialityAgreement;
	}

	public void setShowConfidentialityAgreement(Boolean showConfidentialityAgreement) {
		this.showConfidentialityAgreement = showConfidentialityAgreement;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Long getSacsId() {
		return sacsId;
	}

	public void setSacsId(Long sacsId) {
		this.sacsId = sacsId;
	}

	@Override
	public String toString() {
		return "LoginResponseDto [token=" + token + ", ssoSessionId=" + ssoSessionId + ", userId=" + userId
				+ ", firstname=" + firstname + ", lastname=" + lastname + ", contact=" + contact + ", divisionId="
				+ divisionId + ", facilityTypeId=" + facilityTypeId + ", facilityId=" + facilityId + ", facilityName="
				+ facilityName + ", facilityCode=" + facilityCode + ", designationId=" + designationId
				+ ", designation=" + designation + ", userName=" + userName + ", roleId=" + roleId + ", roleName="
				+ roleName + ", facilityCbStatus=" + facilityCbStatus + ", facilityNumber=" + facilityNumber
				+ ", stateId=" + stateId + ", stateAlernateName=" + stateAlernateName + ", districtId=" + districtId
				+ ", districtAlernateName=" + districtAlernateName + ", lastLogin=" + lastLogin + ", accessCodes="
				+ accessCodes + ", activeToken=" + activeToken + ", currentLoginTime=" + currentLoginTime
				+ ", showConfidentialityAgreement=" + showConfidentialityAgreement + ", email=" + email + ", stateName="
				+ stateName + ", districtName=" + districtName + ", sacsId=" + sacsId + "]";
	}

	
}
