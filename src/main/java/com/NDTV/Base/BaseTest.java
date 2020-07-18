package com.NDTV.Base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.NDTV.Utils.UiUtils;

public class BaseTest {

	public WebDriver driver;
	@BeforeTest
	@Parameters({"browser"})
	public void setUp(String browser) throws Exception {
		Assert.assertNotNull(browser, "The browser is not specified in the TestNg.xml.");
		SessionData.setBrowser(browser);
		BaseCapabilities base=new BaseCapabilities();
		driver=base.getDriver(browser);
		SessionData.setDriver(driver);
		UiUtils.launchBaseUrl(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void closeDriver() {
	//	driver.quit();
	}
	
}
