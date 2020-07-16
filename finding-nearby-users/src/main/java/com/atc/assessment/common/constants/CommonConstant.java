package com.atc.assessment.common.constants;

public interface CommonConstant {
	
	
	/**
	 * Query to compute and find proximity distance for give radius and latitude, longitude of the user
	 * TODO - could be converted as stored procedure, for production read code.
	 */
	String FETCH_MATCHNING_USERS_QUERY = new StringBuilder("SELECT u.id, u.user_name, l.latitude, l.longitude, ")
			  .append("p.distance_unit * DEGREES(ACOS(COS(RADIANS(p.latpoint)) * COS(RADIANS(l.latitude)) * ")
			  .append("COS(RADIANS(p.longpoint) - RADIANS(l.longitude)) + SIN(RADIANS(p.latpoint)) * SIN(RADIANS(l.latitude)))) AS distance_in_km ")
			  .append("FROM user_data As u, location_data AS l ")
			  .append("JOIN (SELECT {0} AS latpoint, {1} AS longpoint, {2} AS radius, 111.045 AS distance_unit ) AS p ON 1=1 ")
			  .append("WHERE u.id=l.user_id and l.latitude ")
			  .append("BETWEEN p.latpoint  - (p.radius / p.distance_unit) ")
			  .append("AND p.latpoint  + (p.radius / p.distance_unit) ")
			  .append("AND l.longitude BETWEEN p.longpoint - (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) ")
			  .append("AND p.longpoint + (p.radius / (p.distance_unit * COS(RADIANS(p.latpoint)))) ")
			  .append("ORDER BY distance_in_km;")
			  .toString();
}
