package org.shovelgame.spring.oauth;

public interface TokenService {
	void revokeToken(String userName);
	String retrieveUserName(String token);
}
