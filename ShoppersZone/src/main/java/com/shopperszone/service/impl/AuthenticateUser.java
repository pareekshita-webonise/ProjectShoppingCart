package com.shopperszone.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUser extends SavedRequestAwareAuthenticationSuccessHandler{
	@Override
	protected String determineTargetUrl(HttpServletRequest request,
			HttpServletResponse response)  {		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String role = authentication.getAuthorities().toString();
		String url = null;
		if(role.contains("ROLE_ADMIN"))
			url="/admin";
		else 
			url="/";
		return url;
	}
}
