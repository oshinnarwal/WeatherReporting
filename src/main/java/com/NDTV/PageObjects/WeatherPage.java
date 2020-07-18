package com.NDTV.PageObjects;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.NDTV.RestAPIResources.AutomationConstants;

public class WeatherPage {
	WebDriver driver;
	@FindBy(how = How.ID, using = "searchBox")
	private WebElement searchBox;

	@FindBy(how = How.XPATH, using = "//input[@class=defaultChecked]")
	private WebElement checkBox;

//	@FindBy(how = How.ID, using = '%s'))
//	private WebElement pinCheck;

	@FindBy(how = How.XPATH, using = "//*[@class='leaflet-popup-content']/div//span/b[contains(text(),'Degrees')]")
	private WebElement tempDegree;

	@FindBy(how = How.XPATH, using = "//*[@class='leaflet-popup-content']/div//span/b")
	private List<WebElement> weatherPopUp;

	@FindBy(how = How.XPATH, using = "//span/b[contains(text(),'Humidity')]")
	private WebElement humidity;

	public WeatherPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(AutomationConstants.GLOBAL_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		// public static final Logger LOGGER = Log.getDefaultLogger();
	}

	public void enterIntoSearchBox(String cityName) {
		searchBox.sendKeys(cityName);
	}

	public void validateCheckBoxWithCityName(String cityName) {
		driver.findElement(By.id(cityName)).isDisplayed();
	}

	public void validateIfCityIsPinnedOnMap(String cityName) {
		driver.findElement(By.xpath("//div[@class='cityText' and text()='" + cityName + "']")).isDisplayed();
		driver.findElement(By.xpath("//div[@class='cityText' and text()='" + cityName + "']")).click();
	}

	public void validateTemperatureDetailsOnMap(String cityName) {

		driver.findElement(By.xpath("//*[text()='Bengaluru']/preceding-sibling::div/span[@class='tempRedText']"))
				.isDisplayed();

	}

	public void validateWeatherDetails(String cityName) {
		Assert.assertTrue(driver
				.findElement(By.xpath("//*[@class='leaflet-popup-content']//span[contains(text(),'" + cityName + "')]"))
				.isDisplayed(), "The details of the weather are not showing about the city ");
		for (WebElement el : weatherPopUp) {
			Assert.assertNotNull(el);
			System.out.println(el.getText());
		}
	}

	public String extractTemperatureFromDetails() {

		String temperature = tempDegree.getText();

		String token[] = temperature.split(":");
		return token[1].trim();

	}

	public String extractHumidityFromDetails() {

		String humid = humidity.getText();
		String token[] = humid.split(":");
		return token[1].replace("%", "").trim();

	}

}