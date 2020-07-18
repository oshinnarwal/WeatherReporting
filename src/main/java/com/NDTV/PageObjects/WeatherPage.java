package com.NDTV.PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.NDTV.RestAPIResources.AutomationConstants;
import com.NDTV.Utils.Log;

public class WeatherPage {
	WebDriver driver;
	@FindBy(how = How.ID, using = "searchBox")
	private WebElement searchBox;

	@FindBy(how = How.XPATH, using = "//input[@class=defaultChecked]")
	private WebElement checkBox;

	@FindBy(how = How.XPATH, using = "//*[@class='leaflet-popup-content']/div//span/b[contains(text(),'Degrees')]")
	private WebElement tempDegree;

	@FindBy(how = How.XPATH, using = "//div[@class='leaflet-popup-content']")
	private WebElement weatherPopUp;

	@FindBy(how = How.XPATH, using = "//*[@class='leaflet-popup-content']/div//span/b")
	private List<WebElement> weatherPopUpDetails;

	@FindBy(how = How.XPATH, using = "//span/b[contains(text(),'Humidity')]")
	private WebElement humidity;

	public WeatherPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(AutomationConstants.GLOBAL_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);

	}

	public void enterIntoSearchBox(String cityName) {
		Log.info("Search City text box found");
		Assert.assertTrue(searchBox.isDisplayed());
		searchBox.sendKeys(cityName);
	}

	public void validateCheckBoxWithCityName(String cityName) {
		Log.info("Validating checkbox with city name");
		Assert.assertTrue(driver.findElement(By.id(cityName)).isDisplayed(),
				"The checkbox with city name is not present or selectable");
		Log.info("Successfully Validated checkbox with city name");
	}

	public void validateIfCityIsPinnedOnMap(String cityName) {
		Log.info("Validating if selected city name is pinned on the map");
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='cityText' and text()='" + cityName + "']")).isDisplayed(),
				"The city is not displaying on map");

	}

	public void validateTemperatureDetailsOnMap(String cityName) {
		Log.info("Validating the temperature details with the city");
		driver.findElement(By.xpath("//*[text()='" + cityName + "']/preceding-sibling::div/span[@class='tempRedText']"))
				.isDisplayed();

	}

	public void validateWeatherDetails(String cityName) {
		Log.info("Validating the weather details with the city");
		driver.findElement(By.xpath("//div[@class='cityText' and text()='" + cityName + "']")).click();
		Assert.assertTrue(weatherPopUp.isDisplayed(), "The details of the weather are not showing about the city");
		for (WebElement el : weatherPopUpDetails) {
			Assert.assertNotNull(el);
			System.out.println(el.getText());
		}
	}

	public String extractTemperatureFromDetails() {
		Log.info("Extracting the temperature details from popup");
		String temperature = tempDegree.getText();

		String token[] = temperature.split(":");
		return token[1].trim();

	}

	public String extractHumidityFromDetails() {
		Log.info("Extracting the humidity details from popup");
		String humid = humidity.getText();
		String token[] = humid.split(":");
		return token[1].replace("%", "").trim();

	}

}