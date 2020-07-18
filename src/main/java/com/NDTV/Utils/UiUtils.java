package com.NDTV.Utils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.NDTV.Base.SessionData;
import com.NDTV.RestAPIResources.AutomationConstants;

public class UiUtils {

	public static void waitForDOMStatusWithTimeOut(String status, int timeOut, WebDriver driver) throws Exception{
		long startTime = System.currentTimeMillis()/1000;
		while((System.currentTimeMillis()/1000 - startTime) < timeOut) {
			if(((JavascriptExecutor)driver).executeScript("return document.readyState;").toString().equalsIgnoreCase(status)) {
				return;
			}
			
			
			
		}
	}
	
	public static void launchBaseUrl() throws Exception {
		SessionData.getDriver().get(AutomationConstants.URL);
       
        
    }
	public static void getMaxWindow() throws Exception {
		SessionData.getDriver().manage().window().maximize();
       
        
    }

	public static void applyImplicitWait() throws Exception {
		SessionData.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       
        
    }
	
	public static void waitForElement(WebDriver driver, WebElement element) throws Exception {
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(element));
       
        
    }
}
