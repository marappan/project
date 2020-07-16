package com.atc.assessment.repository;

import java.util.List;

import com.atc.assessment.repository.entity.UserEntity;

public interface FindingNearbyUsersRepository{

	public int createUsers(List<UserEntity> userList);
	
	public List<UserEntity> getAllMatchingUsers(String longitude, String latitude);
}
