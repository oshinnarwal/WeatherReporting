package com.NDTV.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class BaseCapabilities {

	@Test
	protected  WebDriver getDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
		//System.setProperty("webdriver.chrome.driver", "//Users//oshinnarwal//Documents//Drivers//chromedriver");
			String path = System.getProperty("user.dir");
			String driverpath = path + "//Drivers//chromedriver"; 
			System.setProperty("webdriver.chrome.driver", driverpath);
			return new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "Drivers//chromedriver.exe");
			return new FirefoxDriver();
		}else 
		throw new IllegalArgumentException("Browser name is invalid");
	
	}

}
