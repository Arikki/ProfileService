package com.profileService.config;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 896452368765L;
	
private Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
		logger.error("Request failed at Auth Entry Point as unauthorized for user " +
				request.getReader().lines().filter(x -> x.contains("email")).findFirst().orElse("").trim());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}


}
