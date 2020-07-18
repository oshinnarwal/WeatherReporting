package com.NDTV.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.NDTV.RestAPIResources.AutomationConstants;

public class BaseCapabilities {

	@Test
	protected WebDriver getDriver(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			String path =AutomationConstants.SYSTEM_PATH;
			String driverpath = path + AutomationConstants.DRIVERPATH;
			System.setProperty("webdriver.chrome.driver", driverpath);
			return new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
			return new FirefoxDriver();
		} else
			throw new IllegalArgumentException("Browser name is invalid");

	}

}
