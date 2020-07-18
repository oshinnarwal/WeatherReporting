package com.NDTV.Utils;
/**
 * This class validates the weather 
 * @author oshinnarwal
 *
 */
public class WeatherComparator {
	/**
	 * 
	 * @param tempOnMap
	 * @param tempInCelciusAPI
	 * @param tempVar
	 * @return
	 */
	public boolean varianceTemperature(double tempOnMap, double tempInCelciusAPI,double tempVar) {
		double variance = Math.abs(tempOnMap - tempInCelciusAPI);
		Log.info("The difference in temperature is "+variance);
		if (variance <= tempVar) {
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @param humidOnMap
	 * @param humidAPI
	 * @param humidVar
	 * @return
	 */
	public boolean varianceHumidity(double humidOnMap, double humidAPI, double humidVar) {
		double variance = Math.abs(humidOnMap - humidAPI);
		Log.info("The difference in humidity is "+variance);
		if (variance <= humidVar) {
			return true;
		}
		return false;
	}
	/**
	 * converting temperature into celcius from kelvin
	 * @param temperatureAPI
	 * @return
	 */
	public double temperatureConvertor(double temperatureAPI) {
		return (double) (temperatureAPI - 273.15);
	}

}
