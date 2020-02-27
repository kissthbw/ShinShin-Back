package com.bit.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SimpleAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String SPRING_SECURITY_FORM_DOMAIN_KEY = "domain";
	public static final String SOCIAL_NETWORK_TOKEN = "hash:";

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
		
		//Si el inicio de sesion el password es de 32 posiciones y el prefijo hash:
		//es inicio de sesion por red social
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		if (username == null) {
			username = "";
		}
		if (password == null) {
			password = "";
		}
		
		if ( password.contains( SOCIAL_NETWORK_TOKEN ) ) {
			password = password.replace(SOCIAL_NETWORK_TOKEN, "");
			username = username + SOCIAL_NETWORK_TOKEN;
		}

//
//		String usernameDomain = String.format("%s%s%s", username.trim(), String.valueOf(Character.LINE_SEPARATOR),
//				domain);
		return new UsernamePasswordAuthenticationToken(username, password);
	}

	private String obtainDomain(HttpServletRequest request) {
		return request.getParameter(SPRING_SECURITY_FORM_DOMAIN_KEY);
	}
}