package gov.naco.soch.lfu.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private Long userId;
	private String userToken;

	public UserAuthenticationToken(Object principal, Object credentials, Long userId, String userToken) {
		super(principal, credentials);
		this.userId = userId;
		this.userToken = userToken;
		super.setAuthenticated(false);
	}

	public UserAuthenticationToken(Object principal, Object credentials, Long userId, String userToken,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
		this.userId = userId;
		this.userToken = userToken;
	}

	public long getUserId() {
		return this.userId;
	}

	public String getUserToken() {
		return this.userToken;
	}

}
