package com.NDTV.Base;

import org.openqa.selenium.WebDriver;

public class SessionData {

	private static String browser;
	private static WebDriver driver;
	
	public static String getBrowser() {
		return browser;
	}
	public static void setBrowser(String browser) {
		SessionData.browser = browser;
	}
	public static WebDriver getDriver() {
		return driver;
	}
	public static void setDriver(WebDriver driver) {
		SessionData.driver = driver;
	}
	
	

}
