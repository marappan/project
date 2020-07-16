package com.atc.assessment.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atc.assessment.exception.ATCProximityGenericException;
import com.atc.assessment.services.FindingNearbyUsersService;
import com.atc.assessment.services.model.UserRequestResponse;

/**
 * @author Marappan Sampath
 * Centralized controller to create and fetch users
 */
@RestController
@RequestMapping(path = "/api/v1")
public class FindingNearbyUsersController {

	@Autowired
	private FindingNearbyUsersService findingNearbyUsersService;

	/**
	 * @param user
	 * @return total number of users created
	 * @throws ATCProximityGenericException
	 */
	@PostMapping(path = "/create/users", consumes = "application/json")
	public ResponseEntity<String> createUsers(@RequestBody UserRequestResponse userRequest) throws ATCProximityGenericException {

		int numberOfUsersCreated = findingNearbyUsersService.createNewUsers(userRequest);
		return new ResponseEntity<String>("Number of users created: " + numberOfUsersCreated, HttpStatus.CREATED);
	}

	
	/**
	 * @param latitude
	 * @param langitude
	 * @return User list
	 * @throws ATCProximityGenericException
	 */
	@GetMapping(path = "/search/nearby", produces = "application/json")
	public ResponseEntity<UserRequestResponse> fetchNearbyUsers(@RequestParam(required = true) String langitude,
			@RequestParam(required = true) String latitude) throws ATCProximityGenericException {

		UserRequestResponse userResponse = findingNearbyUsersService.getGeoUsers(langitude, latitude);
		return new ResponseEntity<UserRequestResponse>(userResponse, HttpStatus.OK);
	}
}
