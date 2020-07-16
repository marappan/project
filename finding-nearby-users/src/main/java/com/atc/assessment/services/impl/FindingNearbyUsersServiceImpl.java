package com.atc.assessment.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.assessment.builder.ObjectPipelinebuilder;
import com.atc.assessment.repository.FindingNearbyUsersRepository;
import com.atc.assessment.repository.entity.LocationEntity;
import com.atc.assessment.repository.entity.Point;
import com.atc.assessment.repository.entity.UserEntity;
import com.atc.assessment.services.FindingNearbyUsersService;
import com.atc.assessment.services.model.LocationVO;
import com.atc.assessment.services.model.UserRequestResponse;
import com.atc.assessment.services.model.UserVO;;

/**
 * @author Marappan Sampath
 *
 */
@Service
public class FindingNearbyUsersServiceImpl implements FindingNearbyUsersService {

	@Autowired
	private FindingNearbyUsersRepository findingNearbyUsersRepository;
	
	@Override
	public int createNewUsers(UserRequestResponse useRequest) {
		final List<UserEntity> dtoUserList = new ArrayList<>();

		useRequest.getUserData().forEach(user -> {
			List<LocationEntity> locations = new ArrayList<>();
			
			user.getLocations().forEach(location -> {
				LocationEntity tmpLocation = new LocationEntity();
				Point point = new Point();
				point.setX(location.getLatitude());
				point.setY(location.getLongitude());
				tmpLocation.setPoint(point);
				tmpLocation.setUserId(user.getId());
				locations.add(tmpLocation);
			});
			
			final UserEntity userData = new ObjectPipelinebuilder.UserDTOBuilder(user.getId()).withName(user.getName()).withLocations(locations).build();
			dtoUserList.add(userData);
		});
		
		return findingNearbyUsersRepository.createUsers(dtoUserList);
	}

	
	@Override
	public UserRequestResponse getGeoUsers(String longitude, String latitude) {
		
		final Set<UserVO> voUserList = new HashSet<>();
		final UserRequestResponse userResponseData = new UserRequestResponse();
		
		final List<UserEntity> userFectchList = findingNearbyUsersRepository.getAllMatchingUsers(longitude, latitude);
		userFectchList.forEach(user -> {
			List<LocationVO> locations = new ArrayList<>();
			
			user.getLocations().forEach(location -> {
				LocationVO tmpLocation = new LocationVO();
				tmpLocation.setLatitude(location.getPoint().getX());
				tmpLocation.setLongitude(location.getPoint().getY());
				tmpLocation.setUserId(location.getUserId());
				locations.add(tmpLocation);
			});
			
			final UserVO userVO = new ObjectPipelinebuilder.UserVOBuilder(user.getId()).withName(user.getName()).withLocations(locations).build();
			voUserList.add(userVO);
		});
		
		userResponseData.setUserData(voUserList);
		return userResponseData;
	}

}
