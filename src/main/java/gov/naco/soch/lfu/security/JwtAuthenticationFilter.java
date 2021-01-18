package gov.naco.soch.lfu.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gov.naco.soch.lfu.dto.ErrorResponse;
import gov.naco.soch.lfu.exception.ServiceAuthException;
//import gov.naco.soch.lfu.repository.UserAuthRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	public static final String HEADER_STRING = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@Autowired
//	private UserAuthRepository userAuthRepository;

	private static String[] tokenChecksafeUrls = { "auth/login", "captcha/load", "auth/delete/sid", "system/details",
			"system/version", "system/clearCache", "mhl", "metrics", "env", "beans", "health", "trace", "actuator",
			"auth/mobile/login", "mobile/getListItems", "/notification/sendsystememail/", "/notification/sendsystemsms/","admin/user/generateotp",
			"admin/user/changepassword", "admin/system/info","inventory/facilitystockdailybalance/update","ti/jobs/clientstatus",
			"ti/jobs/dispensestatus","captcha/clean","scheduler/","/scheduler/roupdate","/scheduler/indentupdate","/scheduler/stockupdate","/notification/webnotification/job/send/"};

	private static String[] activeTokenCheckSafeUrls = { "/admin/master", "/api/minimasters", "api/minimasters",
			"address/state", "address/subdistrictbydistrictid", "address/districtbystateid", "admin/auth/disclaimer",
			"admin/auth/logout", "admin/facility/listby", "cst/beneficiaryQueue/queueList",
			"cst/beneficiarydue/duelistlhs", "cst/workloadtiles/tilescount",
			"cst/beneficiary/presentFacilityListBasicSearch", "cst/beneficiarydue/todayslist",
			"v2/ti-ost-beneficiaries", "dashboard/ost/data", "dashboard/data/summary", "v2/ti-beneficiaries/list",
			"admin/product/active/list", "admin/reports/get", "ti/dispensation/v1/beneficiaries",
			"ti/api/ti-ben-scr-details/list", "ti/api/minimaster" };

	/*
	 * private static String[] activeTokenCheckSafeUrls = { "/admin/master",
	 * "admin/facility/listby", "cst/beneficiaryQueue/queueList",
	 * "cst/beneficiarydue/duelistlhs", "cst/workloadtiles/tilescount",
	 * "/notification/sendsystememail/", "/v2/ti-ost-beneficiaries",
	 * "/dashboard/ost/data", "/api/minimasters", "dashboard/data/summary",
	 * "api/v2/ti-beneficiaries/list", "api/minimasters", "address/state",
	 * "address/subdistrictbydistrictid", "address/districtbystateid",
	 * "cst/beneficiary/listById", "admin/auth/disclaimer","admin/auth/logout" };
	 */

	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String username = null;
		try {
			long startTime = System.currentTimeMillis();
			logger.info("Got the request-->{}:", req.getRequestURI());
			String header = req.getHeader(HEADER_STRING);
			List<String> detailsSimplified = new ArrayList<String>();
			ErrorResponse errorResponse = new ErrorResponse();
			String authToken = null;
			if (Arrays.stream(tokenChecksafeUrls).parallel().anyMatch(req.getRequestURI()::contains)) {
				logger.debug("No need to validate token as URL is in safe list");
				chain.doFilter(req, res);
				long durationOne = System.currentTimeMillis() - startTime;
				logger.info("APIEXECUTIONTIME {}-->username-->{}:-->{}", req.getRequestURI(),username,durationOne + " ms");
			} else {
				if (header != null && header.startsWith(TOKEN_PREFIX)) {
					authToken = jwtTokenUtil.getTokenFromHeader(header);
					try {
						username = jwtTokenUtil.getUsernameFromToken(authToken);
						logger.info("Prcessing request --> username-->{}:request-->{}:", username, req.getRequestURI());
					} catch (IllegalArgumentException e) {
						logger.error("An error occured during getting username from token", e);
						detailsSimplified.add(e.getMessage());
						errorResponse.setMessage("An error occured during getting username from token");
						errorResponse.setDetailsSimplified(detailsSimplified);
						res.setStatus(HttpStatus.UNAUTHORIZED.value());
						res.setContentType(MediaType.APPLICATION_JSON_VALUE);
						res.getWriter().write(convertObjectToJson(errorResponse));
						return;
					} catch (ExpiredJwtException e) {
						logger.warn("The token is expired and not valid anymore", e);
						detailsSimplified.add(e.getMessage());
						errorResponse.setMessage("The token is expired and not valid anymore");
						errorResponse.setDetailsSimplified(detailsSimplified);
						res.setStatus(HttpStatus.UNAUTHORIZED.value());
						res.setContentType(MediaType.APPLICATION_JSON_VALUE);
						res.getWriter().write(convertObjectToJson(errorResponse));
						return;
					} catch (SignatureException e) {
						logger.error("Authentication Failed. Invalid token!!");
						detailsSimplified.add(e.getMessage());
						errorResponse.setMessage("Authentication Failed. Invalid token!!");
						errorResponse.setDetailsSimplified(detailsSimplified);
						res.setStatus(HttpStatus.UNAUTHORIZED.value());
						res.setContentType(MediaType.APPLICATION_JSON_VALUE);
						res.getWriter().write(convertObjectToJson(errorResponse));
						return;
					}
				} else {
					logger.error("Authentication Failed. No token!!");
					detailsSimplified.add("Authentication Failed. No token!!");
					errorResponse.setMessage("Authentication Failed. Invalid token!!");
					errorResponse.setDetailsSimplified(detailsSimplified);
					res.setStatus(HttpStatus.UNAUTHORIZED.value());
					res.setContentType(MediaType.APPLICATION_JSON_VALUE);
					res.getWriter().write(convertObjectToJson(errorResponse));
					return;

				}
				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

					if (jwtTokenUtil.validateToken(authToken)) {
						List<String> accessCodes = (List<String>) jwtTokenUtil.getClaimsFromToken(authToken)
								.get("accessCodes");
						Long userId = ((Integer) jwtTokenUtil.getClaimsFromToken(authToken).get("userId")).longValue();

						String activeKey = (String) jwtTokenUtil.getClaimsFromToken(authToken).get("activeKey");

						boolean activeTokenCheckNeeded = Arrays.stream(activeTokenCheckSafeUrls).parallel()
								.anyMatch(req.getRequestURI()::contains) ? false : true;
						String activeToken = null;
						activeTokenCheckNeeded = false; // TODO: Need to remove
						if (activeTokenCheckNeeded) {
							//activeToken = userAuthRepository.findActiveTokenByUserId(userId);
						}

						if (!activeTokenCheckNeeded || (activeKey != null && !StringUtils.isBlank(activeToken)
								&& activeKey.contentEquals(activeToken))) {

							List<SimpleGrantedAuthority> authorityList = accessCodes.stream()
									.map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());

							UsernamePasswordAuthenticationToken authentication = new UserAuthenticationToken(username,
									null, userId, authToken, authorityList);
							authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
							logger.debug("authenticated user " + username + ", setting security context");
							SecurityContextHolder.setStrategyName("MODE_INHERITABLETHREADLOCAL");
							SecurityContextHolder.getContext().setAuthentication(authentication);
						} else {
							logger.error(
									"EXCEPTION in JwtAuthenticationFilter.doFilterInternal activeToken match failed -->Unauthorised access or Session Ended-->username-->{}:Endpoint-->{}:activeTokenCheckNeeded-->{}:activeKey-->{}:activeToken-->{}:",
									username, req.getRequestURI(), activeTokenCheckNeeded, activeKey, activeToken);
							throw new ServiceAuthException("Unauthorised access or Session Ended-->" + username);
						}
					} else {
						logger.error(
								"EXCEPTION in JwtAuthenticationFilter.doFilterInternal validateToken failed -->Unauthorised access or Session Ended-->username-->{}:Endpoint-->{}:authToken-->{}:",
								username, req.getRequestURI(), authToken);
						throw new ServiceAuthException("Unauthorised access or Session Ended-->" + username);
					}
				}

				chain.doFilter(req, res);
				long durationTwo = System.currentTimeMillis() - startTime;
				logger.info("APIEXECUTIONTIME {}-->username-->{}:-->{}", req.getRequestURI(),username,durationTwo + " ms");
			}

		} catch (ServiceAuthException e) {
			logger.error(
					"EXCEPTION -->ServiceAuthException in JwtAuthenticationFilter.doFilterInternal for user-->{}:request-->{}:",
					username, req.getRequestURI(), e);
			res.setStatus(HttpStatus.UNAUTHORIZED.value());
			res.getWriter().write(e.getMessage());
		} catch (Exception e) {
			logger.error("EXCEPTION in JwtAuthenticationFilter.doFilterInternal for username-->{}:request-->{}:", username,
					req.getRequestURI(), e);
			res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			res.getWriter().write(e.getMessage());
		}
	}

	private String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}