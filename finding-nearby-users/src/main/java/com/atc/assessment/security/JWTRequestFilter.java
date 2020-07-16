package com.atc.assessment.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.atc.assessment.common.utils.JsonWebtokenAuthUtil;
import com.atc.assessment.services.impl.JWTAuthenticationServiceImpl;

/**
 * @author Marappan Sampath
 * JSON web token will generate once per request for authentication
 */
@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JWTAuthenticationServiceImpl userDetailsService;

	@Autowired
	private JsonWebtokenAuthUtil jsonWebtokenAuthUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String userName = null;
		String jwtToken = null;
		final String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Auth-")) {
			jwtToken = authHeader.substring(5);
			userName = jsonWebtokenAuthUtil.extractUsername(jwtToken);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			if (jsonWebtokenAuthUtil.validateToekn(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}
}
