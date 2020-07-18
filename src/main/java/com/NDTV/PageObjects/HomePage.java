package com.NDTV.PageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.NDTV.RestAPIResources.AutomationConstants;
import com.NDTV.Utils.Log;

public class HomePage {

	@FindBy(how = How.ID, using = "h_sub_menu")
	private WebElement subMenu;
	
	@FindBy(how = How.LINK_TEXT, using = "WEATHER")
	private WebElement weatherMenu;
	
	public HomePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(AutomationConstants.GLOBAL_IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
	}

	public void selectSubmenuOnHomePage() {
		Log.info("Clicking on the sub menu on home page");
		subMenu.click();
		
	}
	
	public void selectWeatherMenuOnHomePage() {
		Log.info("Clicking on the weather menu");
		weatherMenu.click();
		
	}
}
