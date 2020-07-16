package com.atc.assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atc.assessment.common.utils.JsonWebtokenAuthUtil;
import com.atc.assessment.exception.UserAuthenticationException;
import com.atc.assessment.services.model.UserAuthenticationRequest;
import com.atc.assessment.services.model.UserAuthenticationResponse;

@RestController
public class UserAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private JsonWebtokenAuthUtil jwtToken;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserAuthenticationRequest userAuthenticationRequest)
			throws UserAuthenticationException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userAuthenticationRequest.getUserName(), userAuthenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new UserAuthenticationException("403", e.getMessage());
		}

		final UserDetails userDetails = userDetailService.loadUserByUsername(userAuthenticationRequest.getUserName());
		final String jwToken = jwtToken.generateToken(userDetails);
		return ResponseEntity.ok(new UserAuthenticationResponse(jwToken));
	}

}
