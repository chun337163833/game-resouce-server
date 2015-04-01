package org.shovelgame.web.spring.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shovelgame.web.spring.security.DelegatingPrivilegeEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PrivilegeEvaluatorResolverInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ServletContext context;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		Map<String, WebInvocationPrivilegeEvaluator> wipes = ctx.getBeansOfType(WebInvocationPrivilegeEvaluator.class);
		if (!wipes.isEmpty()) {
			request.setAttribute(WebAttributes.WEB_INVOCATION_PRIVILEGE_EVALUATOR_ATTRIBUTE, new DelegatingPrivilegeEvaluator(wipes));
		}
		return super.preHandle(request, response, handler);
	}

}
