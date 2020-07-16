package com.atc.assessment.services;

import com.atc.assessment.services.model.UserRequestResponse;

/**
 * @author Marappan Sampath
 *
 */
public interface FindingNearbyUsersService {

	public UserRequestResponse getGeoUsers(String longitude, String latitude);

	public int createNewUsers(UserRequestResponse useRequest);
}
