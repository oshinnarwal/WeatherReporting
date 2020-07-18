package com.NDTV.Utils;

public class WeatherComparator {

	public boolean varianceTemperature(double tempOnMap, double tempInCelcius) {
		double variance = Math.abs(tempOnMap - tempInCelcius);
		if (variance <= 3) {
			return true;
		}
		return false;
	}

	public boolean varianceHumidity(double humidOnMap, double humidAPI) {
		double variance = Math.abs(humidOnMap - humidAPI);
		if (variance <= 10) {
			return true;
		}
		return false;
	}

	public double temperatureConvertor(double temperatureAPI) {
		return (double) (temperatureAPI - 273.15);
	}

}
