package com.atc.assessment.common.utils;

/**
 * @author Marappan Sampath Implementing Haversine formula to compute KM
 *         distance/radius between two coordinates of latitude and longitude
 */
public final class ComputeProximityRadiousUtil {

	public final static double AVERAGE_RADIUS_OF_EARTH_KM = 6371;

	public static int calculateDistanceInKilometer(double userLat, double userLng, double othersLat, double othersLng) {

		double latDistance = Math.toRadians(userLat - othersLat);
		double lngDistance = Math.toRadians(userLng - othersLng);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(userLat))
				* Math.cos(Math.toRadians(othersLat)) * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * c));
	}

}
