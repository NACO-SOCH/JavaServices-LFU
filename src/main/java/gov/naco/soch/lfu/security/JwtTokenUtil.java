package gov.naco.soch.lfu.security;


import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import gov.naco.soch.lfu.dto.LoginResponseDto;
//import gov.naco.soch.dto.LoginResponseDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtTokenUtil implements Serializable {
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60;
	public static final String TOKEN_PREFIX = "Bearer ";


	private static final long serialVersionUID = 7263737472523111162L;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	//@Value("${jwt.secret}")
	@Value("${jwtSecret}")
	private String secret;

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getClaimsFromToken(String token) {
		return getAllClaimsFromToken(token);
	}

	// for retrieving any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		boolean isExpired = expiration.before(new Date());
		if (isExpired) {
			logger.error("ERROR This token is expired --> expirationDate-->{}:currentDate-->{}:-->token-->{}:",
					expiration, new Date(), token);
		}
		return isExpired;
	}

	// generate token for user
	public String generateToken(LoginResponseDto user) {
		return doGenerateToken(user);
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String doGenerateToken(LoginResponseDto user) {

		Claims claims = Jwts.claims().setSubject(user.getUserName());
		claims.put("accessCodes", user.getAccessCodes());
		claims.put("userId", user.getUserId());
		claims.put("firstname", user.getFirstname());
		claims.put("lastname", user.getLastname());
		claims.put("divisionId", user.getDivisionId());
		claims.put("facilityTypeId", user.getFacilityTypeId());
		claims.put("facilityId", user.getFacilityId());
		claims.put("designationId", user.getDesignationId());
		claims.put("designation", user.getDesignation());
		claims.put("username", user.getUserName());
		claims.put("roleId", user.getRoleId());
		claims.put("roleName", user.getRoleName());
		claims.put("activeKey", user.getActiveToken());

		return Jwts.builder().setClaims(claims).setIssuer("NACO_ADMIN")
				.setIssuedAt(new Date(System.currentTimeMillis())) // TODO change date to java.time.LocalDateTime
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

	// validate token
	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

	public String getTokenFromHeader(String header) {
		return header.replace(TOKEN_PREFIX, "");
	}

}
