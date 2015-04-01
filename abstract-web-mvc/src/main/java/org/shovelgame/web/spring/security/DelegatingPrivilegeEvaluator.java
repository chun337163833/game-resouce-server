package org.shovelgame.web.spring.security;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.util.Assert;

public class DelegatingPrivilegeEvaluator implements WebInvocationPrivilegeEvaluator {

	private Map<String, WebInvocationPrivilegeEvaluator> evaluators;

	public DelegatingPrivilegeEvaluator(Map<String, WebInvocationPrivilegeEvaluator> evaluators) {
		super();
		Assert.notNull(evaluators, "Privilege evaluators cannot be null.");
		this.evaluators = evaluators;
	}

	@Override
	public boolean isAllowed(String uri, Authentication authentication) {
		return isAllowed(null, uri, null, authentication);
	}

	@Override
	public boolean isAllowed(String contextPath, String uri, String method, Authentication authentication) {
		for(Map.Entry<String, WebInvocationPrivilegeEvaluator> entry: evaluators.entrySet()) {
			if(contextPath == null && method == null) {
				if(entry.getValue().isAllowed(uri, authentication)) {
					continue;
				}
			} else {
				if(entry.getValue().isAllowed(contextPath, uri, method, authentication)) {
					continue;
				}
			}
			
			return false;
		}
		return true;
	}

}
