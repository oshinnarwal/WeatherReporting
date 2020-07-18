package com.NDTV.Web.TestCases;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.NDTV.Base.BaseTest;
import com.NDTV.PageObjects.HomePage;
import com.NDTV.PageObjects.WeatherPage;
import com.NDTV.RestAPIResources.AutomationConstants;
import com.NDTV.RestAPIResources.RestRequestResponseExtract;
import com.NDTV.Utils.Log;
import com.NDTV.Utils.UiUtils;
import com.NDTV.Utils.WeatherComparator;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * 
 * Test case : Validate the weather details from API response and UI and compare
 * them
 * 
 * @author oshinnarwal
 *
 */
public class TC01_ValidateTest extends BaseTest {
	String temperatureOnMap;
	String humidityOnMap;
	double temperatureAPI;
	double humidityAPI;
	double tempInCelciusFromAPI;
	JsonPath jsonPathevaluator;
	

	@Test
	@Parameters({ "cityName" })
	public void getUIWeatherDetails(String cityName) throws Exception {
	
		Log.startTestCase("getUIWeatherDetails");
		HomePage hPage = new HomePage(driver);
		WeatherPage wPage = new WeatherPage(driver);
		/**
		 * Navigating to Weather page on NDTV.com
		 */
		hPage.selectSubmenuOnHomePage();
		hPage.selectWeatherMenuOnHomePage();
		UiUtils.waitForDOMStatusWithTimeOut("complete", 10, driver);
		/**
		 * Validations on map/weather page on NDTV.com
		 */
		wPage.enterIntoSearchBox(cityName);
		wPage.validateCheckBoxWithCityName(cityName);
		wPage.validateTemperatureDetailsOnMap(cityName);
		wPage.validateIfCityIsPinnedOnMap(cityName);
		wPage.validateWeatherDetails(cityName);
		temperatureOnMap = wPage.extractTemperatureFromDetails();
		humidityOnMap = wPage.extractHumidityFromDetails();
		Log.info("temperatureOnMap="+temperatureOnMap);
		Log.debug("humidityOnMap="+humidityOnMap);

	}

	@Test(dependsOnMethods = { "getUIWeatherDetails" })
	public void getApiResponse() throws IOException {
		RestRequestResponseExtract req = new RestRequestResponseExtract();
		WeatherComparator compare = new WeatherComparator();
		/**
		 * Retrieving the response from the API
		 */
		Response res = req.buildRequest("GET", "WEATHERAPI", "");
		Response resObj = req.extractResponse(res);

		jsonPathevaluator = resObj.jsonPath();
		temperatureAPI = jsonPathevaluator.getDouble(AutomationConstants.TEMPRESPONSE);
		humidityAPI = jsonPathevaluator.getDouble(AutomationConstants.HUMPRESPONSE);
		System.out.println(temperatureAPI);

		tempInCelciusFromAPI = compare.temperatureConvertor(temperatureAPI);

		System.out.println(tempInCelciusFromAPI);
		System.out.println(humidityAPI);

	}

	@Test(dependsOnMethods = { "getApiResponse" })
	@Parameters({ "tempVar", "humidVar" })
	public void compareWeatherFromUIandAPI(double tempVar, double humidVar) {
		WeatherComparator compare = new WeatherComparator();
		/**
		 * Comparing the 2 objects temperature and humidity from api and UI
		 */
		boolean var = compare.varianceTemperature(Double.parseDouble(temperatureOnMap), tempInCelciusFromAPI, tempVar);
		boolean varHum = compare.varianceTemperature(Double.parseDouble(humidityOnMap), humidityAPI, humidVar);

		if (var && varHum) {
			Log.info("We have successfully validated the objects from two sources");
			
		} else {
			Log.info("We have successfully validated the objects from two sources but it looks like the temperature or humidity variance is invalid");
			Assert.fail();
		}
	}

}
