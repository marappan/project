package com.atc.assessment.services.model;

import java.util.Set;

public class UserRequestResponse {
	
	private Set<UserVO> userData;

	public Set<UserVO> getUserData() {
		return userData;
	}

	public void setUserData(Set<UserVO> userData) {
		this.userData = userData;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userData == null) ? 0 : userData.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequestResponse other = (UserRequestResponse) obj;
		if (userData == null) {
			if (other.userData != null)
				return false;
		} else if (!userData.equals(other.userData))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserData [userData=" + userData + "]";
	}
	
	
}
