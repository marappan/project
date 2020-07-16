package com.atc.assessment.builder;

import java.util.List;

import com.atc.assessment.repository.entity.LocationEntity;
import com.atc.assessment.repository.entity.UserEntity;
import com.atc.assessment.services.model.LocationVO;
import com.atc.assessment.services.model.UserVO;

/**
 * @author Marappan Sampath
 *
 */
public final class ObjectPipelinebuilder {

	public static final class UserDTOBuilder {
		private Integer id;
		private String name;
		private List<LocationEntity> locations;

		public UserDTOBuilder(Integer id) {
			this.id = id;
		}

		public UserDTOBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public UserDTOBuilder withLocations(List<LocationEntity> locations) {
			this.locations = locations;
			return this;
		}
		
		public UserEntity build() {
			UserEntity userDto = new UserEntity();
			userDto.setId(this.id);
			userDto.setName(this.name);
			userDto.setLocations(locations);
			return userDto;
		}
	}

	public static class UserVOBuilder {

		private Integer id;
		private String name;
		private List<LocationVO> locations;

		public UserVOBuilder(Integer id) {
			this.id = id;
		}

		public UserVOBuilder withName(String name) {
			this.name = name;
			return this;
		}

		public UserVOBuilder withLocations(List<LocationVO> locations) {
			this.locations = locations;
			return this;
		}

		public UserVO build() {
			UserVO userVO = new UserVO();
			userVO.setId(this.id);
			userVO.setName(this.name);
			userVO.setLocations(locations);
			return userVO;
		}

	}

}
