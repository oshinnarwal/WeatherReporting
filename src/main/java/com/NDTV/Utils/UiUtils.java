package com.NDTV.Utils;

import java.io.File;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
	
	public static void launchBaseUrl(WebDriver driver) throws Exception {
        driver.get(AutomationConstants.URL);
       
        
    }
	
}
