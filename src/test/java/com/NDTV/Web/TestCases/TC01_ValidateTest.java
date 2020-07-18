package com.NDTV.Web.TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.NDTV.Base.BaseTest;
import com.NDTV.Base.SessionData;
import com.NDTV.PageObjects.HomePage;
import com.NDTV.PageObjects.WeatherPage;
import com.NDTV.RestAPIResources.AutomationConstants;
import com.NDTV.RestAPIResources.RestRequestResponseExtract;
import com.NDTV.Utils.UiUtils;
import com.NDTV.Utils.WeatherComparator;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
/**
 * 
 * 
 * @author oshinnarwal
 *
 */
public class TC01_ValidateTest extends BaseTest {
	String temp;
	String humidity;
	double temperatureAPI ;
	double humidityAPI ;
	
	@Test
	@Parameters({ "cityName" })
	public void getUIWeatherDetails(String cityName) throws Exception {
		HomePage hPage = new HomePage(driver);
		WeatherPage wPage = new WeatherPage(driver);
		hPage.selectSubmenuOnHomePage();
		hPage.selectWeatherMenuOnHomePage();
		UiUtils.waitForDOMStatusWithTimeOut("complete", 10, driver);
		wPage.enterIntoSearchBox(cityName);
		wPage.validateCheckBoxWithCityName(cityName);
		wPage.validateTemperatureDetailsOnMap(cityName);
		wPage.validateIfCityIsPinnedOnMap(cityName);
		wPage.validateWeatherDetails(cityName);
		temp = wPage.extractTemperatureFromDetails();
		humidity = wPage.extractHumidityFromDetails();
		System.out.println(temp);

	}
	@Test(dependsOnMethods= {"getUIWeatherDetails"})
	public void apiValidateResponse() throws IOException {
		RestRequestResponseExtract req = new RestRequestResponseExtract();
		WeatherComparator compare=new WeatherComparator();
		Response res = req.buildRequest("GET", "WEATHERAPI", "");

		Response resObj = req.extractResponse(res);

		JsonPath jsonPathevaluator = resObj.jsonPath();
		temperatureAPI = jsonPathevaluator.getFloat(AutomationConstants.TEMPRESPONSE);
		humidityAPI = jsonPathevaluator.getFloat(AutomationConstants.HUMPRESPONSE);
		System.out.println(temperatureAPI);

		double tempInCelciusFromAPI = compare.temperatureConvertor(temperatureAPI);
		
		System.out.println(tempInCelciusFromAPI);
		boolean var = compare.varianceTemperature(Double.parseDouble(temp), tempInCelciusFromAPI);
		boolean varHum = compare.varianceTemperature( Double.parseDouble(humidity), humidityAPI);
		
		if (var && varHum) {

		} else {

		}
	}

	
}
