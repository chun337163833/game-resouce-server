package org.shovelgame.spring.oauth;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class SpringTokenService implements TokenService {

	private JdbcClientDetailsService clientDetails;

	private DefaultTokenServices tokenService;

	private TokenStore tokenStore;

	@Override
	public void revokeToken(String userName) {
		for (ClientDetails client : this.clientDetails.listClientDetails()) {
			for (OAuth2AccessToken token : tokenStore.findTokensByClientIdAndUserName(client.getClientId(), userName)) {
				tokenService.revokeToken(token.getValue());
			}
		}
	}

	public void setTokenService(DefaultTokenServices tokenService) {
		this.tokenService = tokenService;
	}

	public void setTokenStore(TokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}

	public void setClientDetails(JdbcClientDetailsService clientDetails) {
		this.clientDetails = clientDetails;
	}
	
	@Override
	public String retrieveUserName(String tokenValue) {
		OAuth2AccessToken token = this.tokenStore.readAccessToken(tokenValue);
		OAuth2Authentication auth = this.tokenStore.readAuthentication(token.getValue());
		return auth.getName();
	}
	
}
