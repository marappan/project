package com.atc.assessment.services.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Marappan Sampath
 * Time being hard coded user name and password
 * TODO - different implementation for production ready code
 */
@Service
public class JWTAuthenticationServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return new User("atc", "demo", new ArrayList<>());
	}
}
