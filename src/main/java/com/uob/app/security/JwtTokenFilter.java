package com.uob.app.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
	
	private final JwtTokenProvider jwtTokenProvider;
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String token = null;
			String bearerToken = request.getHeader(AUTHORIZATION);
			if (bearerToken != null && bearerToken.startsWith(BEARER)) {
				token = bearerToken.substring(7);
				if (token != null && jwtTokenProvider.validateToken(token)) {
					setTokenToSecurityContext(token, response);
				}
			}
		} catch (ExpiredJwtException e) {
			String newToken = jwtTokenProvider.createToken(e.getClaims());
			setTokenToSecurityContext(newToken, response);
		} catch (Exception e) {
			SecurityContextHolder.clearContext();
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
			return;
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void setTokenToSecurityContext(String token, HttpServletResponse response) {
		Authentication auth = jwtTokenProvider.getAuthentication(token);
		SecurityContextHolder.getContext().setAuthentication(auth);
		response.setHeader(HttpHeaders.AUTHORIZATION, token);
	}

}
